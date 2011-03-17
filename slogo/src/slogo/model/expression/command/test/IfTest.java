/**
 * 
 */
package slogo.model.expression.command.test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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
public class IfTest extends TestCase
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
    public final void testSingleCommand () throws ParserException
    {
        ParserResult result = SlogoParser.parse("if 5 [ fd 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(5, expression.evaluate(arena));
        verify(mockedTurtle).move(50);
    }


    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result =
            SlogoParser.parse("if if 3 [ fd 5 ] [ fd 50 bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(3, expression.evaluate(arena));
        verify(mockedTurtle).move(5);
        verify(mockedTurtle).move(50);
        verify(mockedTurtle).move(-50);
    }


    @Test
    public final void testComputedConditional () throws ParserException
    {
        ParserResult result = SlogoParser.parse("if 2+3 [ fd 50 bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(5, expression.evaluate(arena));
        verify(mockedTurtle).move(50);
        verify(mockedTurtle).move(-50);
    }


    @Test
    public final void testConstantConditional () throws ParserException
    {
        ParserResult result = SlogoParser.parse("if 5 [ fd 50 bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(5, expression.evaluate(arena));
        verify(mockedTurtle).move(50);
        verify(mockedTurtle).move(-50);
    }


    @Test
    public final void testFalseConditional () throws ParserException
    {
        ParserResult result = SlogoParser.parse("if 5-5 [ fd 50 bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(0, expression.evaluate(arena));
        verify(mockedTurtle, never()).move(anyInt());
    }

}
