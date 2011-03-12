/**
 * 
 */
package slogo.model.expression.command;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import slogo.ParserTimer;
import slogo.model.expression.Expression;
import slogo.model.parser.CommandLexer;
import util.parser.AbstractParser;
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.Token;
import util.parser.grammar.GrammarParserFactory;


/**
 * @author Michael Ansel
 */
public abstract class Command extends Expression
{
    private static final class ParseResultHandler implements IResultHandler
    {
        protected static GrammarParserFactory commandParserFactory;

        protected static final ResourceBundle SlogoCommandClasses =
            ResourceBundle.getBundle("slogo.model.parser.SlogoCommandClasses");
        protected static final ResourceBundle SlogoCommandSyntax =
            ResourceBundle.getBundle("slogo.model.parser.SlogoCommandGrammar");

        static
        {
            try
            {
                commandParserFactory =
                    new GrammarParserFactory(SlogoCommandSyntax);
                /*
                 * TODO OMG!!! There MUST be a better way to do this! However,
                 * it will be an isolated change, and is therefore deferred for
                 * later. Basic idea: Add an IResultHandler to all the standard
                 * commands. Can probably use keySet() of SlogoCommandClasses
                 * ResourceBundle.
                 */
                // TODO Rebuild to handle user-defined commands (dynamically constructed grammar?)
                // ****Use a map from (Token)CommandName.value to AbstractParserRule****
                Matcher m =
                    Pattern.compile("FirstOf[(](.*)[)]")
                           .matcher(SlogoCommandSyntax.getString("Command"));
                m.matches();
                String[] ruleNames = m.group(1).split("\\s*,\\s*");
                for (final String ruleName : ruleNames)
                {
                    if (logger.isLoggable(Level.FINER)) logger.finer("Adding Handler to \"Command\" Rule: " +
                                                                     ruleName);
                    commandParserFactory.setHandler(ruleName,
                                                    new IResultHandler()
                                                    {

                                                        @Override
                                                        public ParserResult handleResult (ParserResult result)
                                                            throws ParserException
                                                        {
                                                            try
                                                            {
                                                                return new ParserResult(Class.forName(SlogoCommandClasses.getString(ruleName))
                                                                                             .getConstructor(ParserResult.class)
                                                                                             .newInstance(result));
                                                            }
                                                            catch (Exception e)
                                                            {
                                                                e.printStackTrace();
                                                                System.err.println("Result: "+result.getList().toString());
                                                                throw new ParserException(e.toString());
                                                            }
                                                        }
                                                    });
                }
                commandParserFactory.setHandler("NumericExpression",
                                                new IResultHandler()
                                                {

                                                    @Override
                                                    public ParserResult handleResult (ParserResult result)
                                                        throws ParserException
                                                    {
                                                        Object expression =
                                                            ((Token) result.getList()
                                                                           .get(0)).value;
                                                        if (expression instanceof Expression) return new ParserResult(expression);
                                                        throw new RuntimeException("Invalid NumericExpression: " +
                                                                                   expression.toString());
                                                    }

                                                });
                commandParserFactory.setHandler("IgnoreWhitespace",
                                                new IResultHandler()
                                                {

                                                    @Override
                                                    public ParserResult handleResult (ParserResult result)
                                                        throws ParserException
                                                    {
                                                        return new ParserResult();
                                                    }

                                                });

            }
            catch (ParserException e)
            {
                e.printStackTrace();
                throw new RuntimeException("Failed to load grammar: " +
                                           e.toString());
            }
        }


        /**
         * @see util.parser.IResultHandler#handleResult(util.parser.ParserResult)
         * @see slogo.model.expression.Expression#getParserResultHandler()
         */
        @Override
        public ParserResult handleResult (ParserResult result)
            throws ParserException
        {
            CommandLexer lexer = new CommandLexer(result.getList());
//            System.out.println("Untranslated Tokens: " + result.getList());
//            System.out.println("Translated Tokens: " +
//                               lexer.tokenize().toString());
//            for (int i = 0; i < 20; i++)
//                System.out.println();

            Date start = new Date();
            AbstractParser commandParser =
                commandParserFactory.create(lexer.tokenize(),
                                            lexer.getTokenRuleMap());
            ParserTimer.createCommandFromFactory +=
                (new Date().getTime() - start.getTime());
            start = new Date();
            result = commandParser.run();
            ParserTimer.runCommand += (new Date().getTime() - start.getTime());
            return result;
        }

    }

    protected static final Logger logger =
        Logger.getLogger(Command.class.getName());

    private static IResultHandler myParseResultHandlerInstance;


    /**
     * Return the singleton for processing ParserResults into Expression trees
     * 
     * @return IParseResultHandler singleton
     */
    public static IResultHandler getParserResultHandler ()
    {
        if (myParseResultHandlerInstance == null)
        {
            myParseResultHandlerInstance = new ParseResultHandler();
        }
        return myParseResultHandlerInstance;
    }
}
