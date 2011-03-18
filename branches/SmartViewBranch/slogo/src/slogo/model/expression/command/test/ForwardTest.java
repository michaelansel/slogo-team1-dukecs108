/**
 * 
 */
package slogo.model.expression.command.test;

import static org.mockito.Mockito.*;
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
public class ForwardTest extends TestCase
{

    private Arena arena;
    private Turtle mockedTurtle;


    @Before
    public void setUp () throws Exception
    {
        mockedTurtle = mock(Turtle.class);
        arena = new Arena();
        arena.setCurrentTurtleID(arena.addTurtle(mockedTurtle));
    }


    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result = SlogoParser.parse("fd fd 50");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(50)).thenReturn(17);
        when(mockedTurtle.move(17)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
    }

    @Test
    public final void testLongName () throws ParserException
    {
        ParserResult result = SlogoParser.parse("forward 50");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(50)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }

    @Test
    public final void testComputedDistance () throws ParserException
    {
        ParserResult result = SlogoParser.parse("fd 5+10");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(15)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }


    @Test
    public final void testConstantDistance () throws ParserException
    {
        ParserResult result = SlogoParser.parse("fd 50");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(50)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }


    @Test
    public final void testNegativeDistance () throws ParserException
    {
        ParserResult result = SlogoParser.parse("fd -50");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(-50)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }

}
