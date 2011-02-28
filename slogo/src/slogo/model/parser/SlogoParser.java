/**
 * 
 */
package slogo.model.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.Token;
import util.parser.grammar.GrammarParserFactory;


/**
 * @author Michael Ansel
 */
public class SlogoParser
{
    protected static final ResourceBundle SlogoSyntax =
        ResourceBundle.getBundle("slogo.model.parser.SlogoGrammar");
    protected static final ResourceBundle SlogoCommandSyntax =
        ResourceBundle.getBundle("slogo.model.parser.SlogoCommandGrammar");
    protected static final ResourceBundle SlogoCommandClasses =
        ResourceBundle.getBundle("slogo.model.parser.SlogoCommandClasses");
    protected static GrammarParserFactory commandParserFactory;
    protected static GrammarParserFactory parserFactory;

    static
    {
        try
        {
            parserFactory = new GrammarParserFactory(SlogoSyntax);
            commandParserFactory = new GrammarParserFactory(SlogoCommandSyntax);
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
        AbstractParser parser = parserFactory.create(new SlogoLexer(input));
        parser.getRule("Command").setHandler(new IResultHandler()
        {

            @Override
            public ParserResult handleResult (ParserResult result)
                throws ParserException
            {
                CommandLexer lexer = new CommandLexer(result.getList());
                System.out.println("Untranslated Tokens: " + result.getList());
                System.out.println("Translated Tokens: " +
                                   lexer.tokenize().toString());
                for (int i = 0; i < 20; i++)
                    System.out.println();
                AbstractParser commandParser =
                    commandParserFactory.create(lexer);
                Matcher m =
                    Pattern.compile("FirstOf[(](.*)[)]")
                           .matcher(SlogoCommandSyntax.getString("Command"));
                m.matches();
                String[] ruleNames = m.group(1).split("\\s*,\\s*");
                for (final String ruleName : ruleNames)
                {
                    System.out.println("Adding Handler to Command: " + ruleName);
                    commandParser.getRule(ruleName)
                                 .setHandler(new IResultHandler()
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
                                             throw new ParserException(e.toString());
                                         }
                                     }
                                 });
                }

                result = commandParser.run();
                return result;
            }

        });
        parser.getRule("Expression").setHandler(new IResultHandler()
        {

            @Override
            public ParserResult handleResult (final ParserResult result)
                throws ParserException
            {
                return new ParserResult(new Expression()
                {
                    @Override
                    public String toString ()
                    {
                        return "FakeExpression<" + result.toString() + ">";
                    }


                    @Override
                    public int evaluate (Arena arena)
                    {
                        return 0;
                    }
                });
            }

        });
        return parser.run();
    }
}
