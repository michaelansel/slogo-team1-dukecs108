/**
 * 
 */
package util.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * @author Michael Ansel
 */
public abstract class AbstractParser implements Cloneable
{
    protected class ParserCheckpoint
    {
        public List<Token> myTokens;


        public ParserCheckpoint (List<Token> tokens)
        {
            myTokens = new ArrayList<Token>(tokens);
        }
    }

    protected static final Logger logger = Logger.getLogger(AbstractParser.class.getName());

    private AbstractLexer myLexer;
    private AbstractParserRule myRootRule;
    private Map<String, AbstractParserRule> myRules;
    private List<Token> myTokens;


    public AbstractParser (AbstractLexer lexer)
    {
        myLexer = lexer;
        myTokens = new ArrayList<Token>(lexer.tokenize());
        myRules = new HashMap<String, AbstractParserRule>();
    }
    
    @Override
    public AbstractParser clone()
    {
        AbstractParser parser = null;
        try
        {
            parser = (AbstractParser) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        parser.myLexer = myLexer;
        parser.myTokens = new ArrayList<Token>(myLexer.tokenize());
        parser.myRules = new HashMap<String, AbstractParserRule>(myRules);
        return parser;
    }


    /**
     * @param field
     */
    public void addRule (String ruleName, AbstractParserRule rule)
    {
        myRules.put(ruleName, rule);
    }


    protected Token consumeNextToken ()
    {
        return myTokens.remove(0);
    }


    public AbstractParserRule ExactlyOne (Object object)
    {
        if (object instanceof AbstractParserRule) return (AbstractParserRule) object;
        if (isToken(object))
        {
            final ITokenRule tokenRule = (ITokenRule) object;
            return new AbstractParserRule()
            {
                private ITokenRule myTokenRule = tokenRule;


                @Override
                public ParserResult evaluate () throws ParserException
                {
                    ParserResult result = new ParserResult();
                    Token nextToken = null;
                    if (hasNextToken() &&
                        (nextToken = peekNextToken()).tokenRule == myTokenRule)
                    {
                        result.add(consumeNextToken());
                        logger.finer("ExactlyOne returning: " +
                                           result.toString());
                        return processResult(result);
                    }
                    parseError(String.format("Token Mismatch! Expected: %s Got: %s",
                                             myTokenRule,
                                             nextToken));
                    return null;
                }


                @Override
                public void initializeRule ()
                {}


                @Override
                public void setRule (AbstractParserRule rule)
                {
                    throw new UnsupportedOperationException();
                }


                @Override
                public String toString ()
                {
                    return "ExactlyOneRule(" + myTokenRule.toString() + ")";
                }
            };
        }
        throw new IllegalArgumentException("Must provide either a parser rule or a token rule.");
    }


    public AbstractParserRule FirstOf (final Object ... objects)
    {
        return new AbstractParserRule()
        {
            private List<AbstractParserRule> myRules =
                new ArrayList<AbstractParserRule>();
            {
                for (Object o : objects)
                    myRules.add(ExactlyOne(o));
            }


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserCheckpoint checkpoint = null;
                logger.finer("FirstOf: " + myRules.toString());
                for (AbstractParserRule rule : myRules)
                {
                    checkpoint = storeCheckpoint();
                    logger.finer("--Testing: " + rule.toString());
                    try
                    {
                        ParserResult result = rule.evaluate();
                        logger.finer("FirstOf returning: " +
                                           result.toString());
                        return processResult(result);
                    }
                    catch (ParserException e)
                    {
                        logger.finer("--Caught! Restoring from checkpoint... (" +
                                           e.toString().replaceAll("\n", " | ") +
                                           ")");
                        restoreCheckpoint(checkpoint);
                        logger.finer("Tokens: " + myTokens.toString());
                    }
                }
                parseError("FirstOf never matched!");
                return null;
            }


            @Override
            public void setRule (AbstractParserRule rule)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public String toString ()
            {
                return "FirstOfRule(" + myRules.toString() + ")";
            }
        };
    }


    public AbstractLexer getLexer ()
    {
        return myLexer;
    }


    protected AbstractParserRule getRootRule ()
    {
        if (myRootRule != null) return myRootRule;
        try
        {
            myRootRule =
                (AbstractParserRule) this.getClass()
                                         .getDeclaredField("Root")
                                         .get(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to load root parser rule: " +
                                       e.toString());
        }
        return myRootRule;
    }


    public AbstractParserRule getRule (String ruleName)
    {
        return myRules.get(ruleName);
    }


    public Collection<String> getRuleNames ()
    {
        return myRules.keySet();
    }


    protected boolean hasNextToken ()
    {
        return (myTokens.size() > 0);
    }


    protected void initializeRules ()
    {
        try
        {
            for (Field field : this.getClass().getDeclaredFields())
            {
                if (AbstractParserRule.class.isAssignableFrom(field.getType()))
                {
                    field.getType()
                         .getMethod("initializeRule")
                         .invoke(field.get(this));
                    field.getType()
                         .getMethod("setRuleName", String.class)
                         .invoke(field.get(this), field.getName());
                    addRule(field.getName(),
                            (AbstractParserRule) field.get(this));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to initialize parser rules: " +
                                       e.toString());
        }
    }


    protected boolean isToken (Object myObject)
    {
        return (myObject instanceof ITokenRule) &&
               myLexer.getTokenRules().contains((ITokenRule) myObject);
    }


    public AbstractParserRule OneOrMore (final Object ... objects)
    {
        AbstractParserRule seq = Sequence(objects);
        return Sequence(seq, ZeroOrMore(seq));
    }


    public AbstractParserRule Optional (final Object object)
    {
        return new AbstractParserRule()
        {
            private AbstractParserRule myRule = ExactlyOne(object);


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserCheckpoint checkpoint = null;
                ParserResult result = new ParserResult();
                checkpoint = storeCheckpoint();
                try
                {
                    result.merge(myRule.evaluate());
                }
                catch (ParserException e)
                {
                    restoreCheckpoint(checkpoint);
                }
                logger.finer("Optional returning: " + result.toString());
                return processResult(result);
            }


            @Override
            public void setRule (AbstractParserRule rule)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public String toString ()
            {
                return "OptionalRule(" + myRule.getClass().toString() + ")";
            }
        };
    }


    public AbstractParserRule Optional (Object ... objects)
    {
        return Optional(Sequence(objects));
    }


    protected void parseError () throws ParserException
    {
        parseError("");
    }


    protected void parseError (String message) throws ParserException
    {
        // TODO Externalize strings, ParserException, print debug info
        if (!message.isEmpty()) message += "\n";
        throw new ParserException(message + "Remaining Tokens: " +
                                  myTokens.toString());
    }


    protected Token peekNextToken ()
    {
        return myTokens.get(0);
    }


    protected void restoreCheckpoint (ParserCheckpoint checkpoint)
    {
        myTokens = checkpoint.myTokens;
    }


    public ParserResult run () throws ParserException
    {
        initializeRules();
        return getRootRule().evaluate();
    }


    public AbstractParserRule Sequence (final Object ... objects)
    {
        return new AbstractParserRule()
        {
            private List<AbstractParserRule> myRules =
                new ArrayList<AbstractParserRule>();
            {
                for (Object o : objects)
                    myRules.add(ExactlyOne(o));
            }


            public ParserResult evaluate () throws ParserException
            {
                ParserResult result = new ParserResult();
                logger.finer(myRules.toString());
                for (AbstractParserRule rule : myRules)
                {
                    try
                    {
                        result.merge(rule.evaluate());
                    }
                    catch (ParserException e)
                    {
                        throw new ParserException(String.format("Sequence rule failed while parsing %s.\nRemaining Tokens: %s",
                                                                rule.toString(),
                                                                myTokens.toString()),
                                                  e);
                    }
                }
                logger.finer("Sequence returning: " + result.toString());
                return processResult(result);
            }


            @Override
            public void setRule (AbstractParserRule rule)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public String toString ()
            {
                return "SequenceRule";
            }
        };
    }


    public void setLexer (AbstractLexer lexer)
    {
        myLexer = lexer;
        myTokens = new ArrayList<Token>(myLexer.tokenize());
    }

    protected ParserCheckpoint storeCheckpoint ()
    {
        return new ParserCheckpoint(myTokens);
    }


    public AbstractParserRule ZeroOrMore (final Object ... objects)
    {
        return new AbstractParserRule()
        {
            private AbstractParserRule mySequence = Sequence(objects);


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserCheckpoint checkpoint = null;
                ParserResult result = new ParserResult();
                while (true)
                {
                    checkpoint = storeCheckpoint();
                    try
                    {
                        result.merge(mySequence.evaluate());
                    }
                    catch (ParserException e)
                    {
                        restoreCheckpoint(checkpoint);
                        break;
                    }
                }
                logger.finer("ZeroOrMore returning: " + result.toString());
                return processResult(result);
            }


            @Override
            public void setRule (AbstractParserRule rule)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public String toString ()
            {
                return "ZeroOrMoreRule";
            }
        };
    }
}
