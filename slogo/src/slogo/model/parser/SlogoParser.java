/**
 * 
 */
package slogo.model.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import slogo.ParserTimer;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.expression.Variable;
import slogo.model.expression.binary.Arithmetic;
import util.parser.AbstractLexer;
import util.parser.AbstractParser;
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class SlogoParser
{
    private static final Logger logger =
        Logger.getLogger(AbstractParser.class.getName());

    protected static SlogoParserFactory parserFactory;

    protected static final ResourceBundle SlogoCommandClasses =
        ResourceBundle.getBundle("slogo.model.parser.SlogoCommandClasses");
    protected static final ResourceBundle SlogoSyntax =
        ResourceBundle.getBundle("slogo.model.parser.SlogoGrammar");

    static
    {
        try
        {
            parserFactory = new SlogoParserFactory(SlogoSyntax);
            // TODO Either ResourceBundle or final,static array
            parserFactory.setHandler("ArithmeticExpression",
                                     Arithmetic.getParserResultHandler());
            parserFactory.setHandler("Assignment",
                                     Variable.getParserResultHandler());
            parserFactory.setHandler("BooleanExpression",
                                     slogo.model.expression.binary.Boolean.getParserResultHandler());
            parserFactory.setHandler("Constant",
                                     Constant.getParserResultHandler());
            parserFactory.setHandler("Variable",
                                     Variable.getParserResultHandler());
            parserFactory.setHandler("IgnoreWhitespace", new IResultHandler()
            {
                @Override
                public ParserResult handleResult (ParserResult result)
                    throws ParserException
                {
                    // Don't return any matched whitespace tokens
                    return new ParserResult();
                }
            });
            parserFactory.setHandler("SubExpression", new IResultHandler()
            {

                @Override
                public ParserResult handleResult (ParserResult result)
                    throws ParserException
                {
                    for (Object o : result.getList())
                        if (o instanceof Expression) return new ParserResult(o);
                    throw new ParserException("Invalid ParserResult: " +
                                              result.getList().toString());
                }
            });
            parserFactory.setHandler("CommandGroup", new IResultHandler()
            {

                @Override
                public ParserResult handleResult (ParserResult result)
                    throws ParserException
                {
                    // <BeginParameterGroup>,CommandList,<EndParameterGroup>
                    return new ParserResult(result.getList().get(1));
                }

            });
            IResultHandler groupHandler = new IResultHandler()
            {
                @Override
                public ParserResult handleResult (ParserResult result)
                    throws ParserException
                {
                    List<Object> list = new ArrayList<Object>(result.getList());
                    list.remove(0); // BeginParameterGroup
                    list.remove(list.size() - 1); // EndParameterGroup
                    List<Expression> expressions = new ArrayList<Expression>();
                    while (!list.isEmpty())
                        expressions.add((Expression) list.remove(0));
                    return new ParserResult(expressions);
                }
            };
            parserFactory.setHandler("ExpressionGroup", groupHandler);
            parserFactory.setHandler("VariableGroup", groupHandler);
            parserFactory.setHandler("CommandList", new IResultHandler()
            {
                @Override
                public ParserResult handleResult (ParserResult result)
                    throws ParserException
                {
                    if (result.getList().size() == 1) return result;

                    final List<Expression> expressions =
                        new ArrayList<Expression>();
                    for (Object o : result.getList())
                        if (o instanceof Expression) expressions.add((Expression) o);
                    return new ParserResult(new Expression()
                    {
                        private List<Expression> myExpressions = expressions;


                        @Override
                        protected Collection<Expression> getExpressions ()
                        {
                            return myExpressions;
                        }


                        @Override
                        public int evaluate (Arena arena, Turtle turtle)
                        {
                            int retval = 0;
                            for (Expression e : myExpressions)
                                retval = e.evaluate(arena, turtle);
                            return retval;
                        }


                        @Override
                        public String toString ()
                        {
                            return myExpressions.toString();
                        }
                    });
                }
            });

            // TODO Handle user-defined commands (recompile grammar on each new command?)
            // ****Use a map from (Token)CommandName.value to AbstractParserRule****
            for (String ruleName : SlogoCommandClasses.keySet())
            {
                final String ruleClass =
                    SlogoCommandClasses.getString(ruleName);
                logger.log(Level.FINE,
                           "Adding Handler for {0}: {1}",
                           new Object[] { ruleName, ruleClass });
                parserFactory.setHandler(ruleName, new IResultHandler()
                {

                    @Override
                    public ParserResult handleResult (ParserResult result)
                        throws ParserException
                    {
                        logger.log(Level.FINER,
                                   "Handling Command result: {0} {1}",
                                   new Object[] { ruleClass, result.getList() });
                        try
                        {
                            return new ParserResult(Class.forName(ruleClass)
                                                         .getConstructor(ParserResult.class)
                                                         .newInstance(result));
                        }
                        catch (InstantiationException e)
                        {
                            e.printStackTrace();
                            System.err.println("Result: " +
                                               result.getList().toString());
                            throw new ParserException(e.toString());
                        }
                        catch (IllegalAccessException e)
                        {
                            e.printStackTrace();
                            System.err.println("Result: " +
                                               result.getList().toString());
                            throw new ParserException(e.toString());
                        }
                        catch (InvocationTargetException e)
                        {
                            e.printStackTrace();
                            System.err.println("Result: " +
                                               result.getList().toString());
                            throw new ParserException(e.toString());
                        }
                        catch (NoSuchMethodException e)
                        {
                            e.printStackTrace();
                            System.err.println("Result: " +
                                               result.getList().toString());
                            throw new ParserException(e.toString());
                        }
                        catch (ClassNotFoundException e)
                        {
                            e.printStackTrace();
                            System.err.println("Result: " +
                                               result.getList().toString());
                            throw new ParserException(e.toString());
                        }
                    }
                });
            }
        }
        catch (ParserException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to load grammar: " +
                                       e.toString());
        }
    }


    public static ParserResult parse (String input) throws ParserException
    {
        Date start = new Date();
        AbstractLexer lexer = new SlogoLexer(input);
        AbstractParser parser =
            parserFactory.create(lexer.tokenize(), lexer.getTokenRuleMap());

        ParserTimer.createFromFactory +=
            (new Date().getTime() - start.getTime());

        start = new Date();
        ParserResult retval = parser.run();
        ParserTimer.run += (new Date().getTime() - start.getTime());

        return retval;
    }


    public static void addUserCommand (String commandName,
                                       List<Variable> myVariableList)
    {
        parserFactory.addUserDefinedCommand(commandName,
                                            myVariableList);
    }
}
