/**
 * 
 */
package slogo.model.expression.command.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
public class RepeatTest extends TestCase
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
    public final void testSingleCommand () throws ParserException
    {
        ParserResult result = SlogoParser.parse("repeat 5 [ fd 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(50)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
        verify(mockedTurtle, times(5)).move(50);
    }


    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result =
            SlogoParser.parse("repeat repeat 5 [ fd 5 ] [ fd 50 bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(5)).thenReturn(5);
        when(mockedTurtle.move(50)).thenReturn(17);
        when(mockedTurtle.move(-50)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
        verify(mockedTurtle, times(5)).move(5);
        verify(mockedTurtle, times(5)).move(50);
        verify(mockedTurtle, times(5)).move(-50);
    }


    @Test
    public final void testComputedCount () throws ParserException
    {
        ParserResult result = SlogoParser.parse("repeat 2+3 [ fd 50 bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(50)).thenReturn(17);
        when(mockedTurtle.move(-50)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
        verify(mockedTurtle, times(5)).move(50);
        verify(mockedTurtle, times(5)).move(-50);
    }


    @Test
    public final void testConstantCount () throws ParserException
    {
        ParserResult result = SlogoParser.parse("repeat 5 [ fd 50 bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(50)).thenReturn(17);
        when(mockedTurtle.move(-50)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
        verify(mockedTurtle, times(5)).move(50);
        verify(mockedTurtle, times(5)).move(-50);
    }

}
