package slogo.model.parser.test;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.expression.command.Command;
import slogo.model.expression.command.Forward;
import slogo.model.parser.CommandLexer;
import slogo.model.parser.SlogoLexer;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.grammar.GrammarParserFactory;


public class CommandParserTest extends TestCase
{

    private GrammarParserFactory commandParserFactory;


    @Before
    public void setUp () throws Exception
    {
        commandParserFactory =
            new GrammarParserFactory(ResourceBundle.getBundle("slogo.model.parser.SlogoCommandGrammar"));
    }


    /**
     * @throws ParserException
     */
    @Test
    public void testParse () throws ParserException
    {
        List<Object> tokens =
            Arrays.asList(new Object[] {
                    SlogoLexer.Token.CommandName.makeToken("fd"),
                    SlogoLexer.Token.Whitespace.makeToken(" "),
                    new Constant(50) });
        ParserResult result =
            Command.getParserResultHandler()
                   .handleResult(new ParserResult(tokens));
        System.out.println(result);
        Expression expected = new Forward(new Constant(50));
        assertTrue(result.getList().toString(),
                   result.getList().get(0) instanceof Expression);
        Expression actual = (Expression) result.getList().get(0);
        assertEquals(expected.toString(), actual.toString());
    }

}
