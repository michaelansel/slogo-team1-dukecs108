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
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.expression.Variable;
import slogo.model.expression.binary.Arithmetic;
import slogo.model.expression.binary.BinaryExpression;
import slogo.model.expression.command.Command;
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
    protected static GrammarParserFactory parserFactory;

    static
    {
        try
        {
            parserFactory = new GrammarParserFactory(SlogoSyntax);
            // TODO Either ResourceBundle or final,static array
            parserFactory.setHandler("ArithmeticExpression",
                                     Arithmetic.getParserResultHandler());
            parserFactory.setHandler("BooleanExpression",
                                     slogo.model.expression.binary.Boolean.getParserResultHandler());
            parserFactory.setHandler("Constant",
                                     Constant.getParserResultHandler());
            parserFactory.setHandler("Variable",
                                     Variable.getParserResultHandler());
            parserFactory.setHandler("Command",
                                     Command.getParserResultHandler());
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

        return parser.run();
    }
}
