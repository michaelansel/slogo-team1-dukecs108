/**
 * 
 */
package util.parser;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import util.parser.rule.FirstOfRule;
import util.parser.rule.OptionalRule;
import util.parser.rule.SequenceRule;
import util.parser.rule.ZeroOrMoreRule;


/**
 * @author Michael Ansel
 */
public abstract class AbstractParser implements Cloneable
{
    protected static final Logger logger =
        Logger.getLogger(AbstractParser.class.getName());

    private AbstractParserRule myRootRule;
    private Map<String, AbstractParserRule> myRules;
    private TokenManager myTokenManager;


    public AbstractParser (TokenManager tokenManager)
    {
        myTokenManager = tokenManager;
        myRules = new HashMap<String, AbstractParserRule>();
    }


    /**
     * @param field
     */
    public void addRule (String ruleName, AbstractParserRule rule)
    {
        myRules.put(ruleName, rule);
    }


    @Override
    public AbstractParser clone ()
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
        parser.myTokenManager = myTokenManager/* TODO .clone() */;
        parser.myRules = new HashMap<String, AbstractParserRule>(myRules);
        return parser;
    }


    public AbstractParserRule FirstOf (Object ... objects)
        throws ParserException
    {
        return new FirstOfRule(objects);
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


    public AbstractParserRule OneOrMore (final Object ... objects)
    {
        AbstractParserRule seq = Sequence(objects);
        return Sequence(seq, ZeroOrMore(seq));
    }


    public AbstractParserRule Optional (final Object object)
    {
        return new OptionalRule(object);
    }


    public AbstractParserRule Optional (Object ... objects)
    {
        return Optional(Sequence(objects));
    }


    public ParserResult run () throws ParserException
    {
        initializeRules();
        return getRootRule().evaluate(myTokenManager);
    }


    public AbstractParserRule Sequence (final Object ... objects)
    {
        return new SequenceRule(objects);
    }


    public void setTokenManager (TokenManager tokenManager)
    {
        myTokenManager = tokenManager;
    }


    public AbstractParserRule ZeroOrMore (final Object ... objects)
    {
        return new ZeroOrMoreRule(objects);
    }
}
