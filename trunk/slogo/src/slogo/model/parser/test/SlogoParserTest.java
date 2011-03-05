/**
 * 
 */
package slogo.model.parser.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.expression.binary.Add;
import slogo.model.expression.binary.BinaryExpression;
import slogo.model.expression.command.Forward;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class SlogoParserTest extends TestCase
{
    private Expression actual;
    private Expression expected;
    private ParserResult result;


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
    public final void testParseNestedCommand () throws ParserException
    {
        result = SlogoParser.parse("fd fd 50");
        expected = new Forward(new Forward(new Constant(50)));
        actual = (Expression) result.getList().get(0);
        System.out.println(result);
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
    public final void testParseComplexExpression () throws ParserException
    {
        result = SlogoParser.parse("fd ( -70+((-70 +-70) + 270) ))");
        expected =
            new Forward(new Add(new Constant(-70),
                                new Add(new Add(new Constant(-70),
                                                new Constant(-70)),
                                        new Constant(270))));
        actual = (Expression) result.getList().get(0);
        System.out.println(result);
        assertEquals(expected.toString(), actual.toString());
    }

}
