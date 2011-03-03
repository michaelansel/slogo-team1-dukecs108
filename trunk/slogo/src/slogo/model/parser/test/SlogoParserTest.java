/**
 * 
 */
package slogo.model.parser.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.expression.command.Forward;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class SlogoParserTest extends TestCase
{
    private ParserResult result;
    private Expression expected;
    private Expression actual;


    @Before
    public void setUp () throws Exception
    {}


    @Test
    public final void testParseConstant () throws ParserException
    {

        result = SlogoParser.parse("50");
        expected = new Constant(50);
        actual = (Expression) result.getList().get(0);
        assertEquals(expected.toString(), actual.toString());
    }


    @Test
    public final void testParseSimpleCommand () throws ParserException
    {
        result = SlogoParser.parse("fd 50");
        expected = new Forward(new Constant(50));
        actual = (Expression) result.getList().get(0);
        System.out.println(result);
        assertEquals(expected.toString(), actual.toString());
    }


    @Test
    public final void testParseNestedCommand () throws ParserException
    {
        result = SlogoParser.parse("fd fd 50");
        expected = new Forward(new Forward(new Constant(50)));
        actual = (Expression) result.getList().get(0);
        System.out.println(result);
        assertEquals(expected.toString(), actual.toString());
    }

}
