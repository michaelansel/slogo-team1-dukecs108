/**
 * 
 */
package slogo.model.parser;

import java.util.Date;
import java.util.ResourceBundle;
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.expression.Variable;
import slogo.model.expression.binary.Arithmetic;
import slogo.model.expression.command.Command;
import slogo.ParserTimer;
import util.parser.AbstractParser;
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.grammar.GrammarParserFactory;


/**
 * @author Michael Ansel
 */
public class SlogoParser
{
    protected static GrammarParserFactory parserFactory;
    protected static final ResourceBundle SlogoSyntax =
        ResourceBundle.getBundle("slogo.model.parser.SlogoGrammar");

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
        AbstractParser parser = parserFactory.create(new SlogoLexer(input));
        
        ParserTimer.createFromFactory +=
            (new Date().getTime() - start.getTime());

        start = new Date();
        ParserResult retval = parser.run();
        ParserTimer.run += (new Date().getTime() - start.getTime());

        return retval;
    }
}
