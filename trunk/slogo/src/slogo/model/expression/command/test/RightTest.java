/**
 * 
 */
package slogo.model.expression.command.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class RightTest extends TestCase
{

    private Arena arena;
    private Turtle mockedTurtle;


    @Before
    public void setUp () throws Exception
    {
        mockedTurtle = mock(Turtle.class);
        arena = new Arena();
        arena.selectTurtles(arena.addTurtle(mockedTurtle));
    }

    @Test
    public final void testLongName () throws ParserException
    {
        ParserResult result = SlogoParser.parse("right 90");
        Expression expression = (Expression) result.getList().get(0);
        // TODO This seems counter-intuitive: right should be positive
        when(mockedTurtle.rotate(-90)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }

    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result = SlogoParser.parse("rt rt 90");
        Expression expression = (Expression) result.getList().get(0);
        // TODO This seems counter-intuitive: right should be positive
        when(mockedTurtle.rotate(-90)).thenReturn(17);
        when(mockedTurtle.rotate(-17)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
    }


    @Test
    public final void testComputedDistance () throws ParserException
    {
        ParserResult result = SlogoParser.parse("rt 30+60");
        Expression expression = (Expression) result.getList().get(0);
        // TODO This seems counter-intuitive: right should be positive
        when(mockedTurtle.rotate(-90)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }


    @Test
    public final void testConstantDistance () throws ParserException
    {
        ParserResult result = SlogoParser.parse("rt 90");
        Expression expression = (Expression) result.getList().get(0);
        // TODO This seems counter-intuitive: right should be positive
        when(mockedTurtle.rotate(-90)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }


    @Test
    public final void testNegativeDistance () throws ParserException
    {
        ParserResult result = SlogoParser.parse("rt -90");
        Expression expression = (Expression) result.getList().get(0);
        // TODO This seems counter-intuitive: right should be positive
        when(mockedTurtle.rotate(90)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }

}
