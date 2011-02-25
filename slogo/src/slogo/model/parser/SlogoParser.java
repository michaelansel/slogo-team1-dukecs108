/**
 * 
 */
package slogo.model.parser;

import java.util.ResourceBundle;
import slogo.model.expression.Expression;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.grammar.GrammarParserFactory;


/**
 * @author Michael Ansel
 */
public class SlogoParser
{
    protected static GrammarParserFactory commandParserFactory;
    public static GrammarParserFactory parserFactory;

    static
    {
        try
        {
            parserFactory =
                new GrammarParserFactory(ResourceBundle.getBundle("slogo.model.parser.SlogoGrammar"));
            commandParserFactory =
                new GrammarParserFactory(ResourceBundle.getBundle("slogo.model.parser.SlogoCommandGrammar"));
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
                AbstractParser commandParser =
                    commandParserFactory.create(new CommandLexer(result.getList()));
                commandParser.addRule("Expression", new AbstractParserRule()
                {});
                return commandParser.run();
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
                });
            }

        });
        return parser.run();
    }
}
