/**
 * 
 */
package slogo.model.expression.command.test;

import static org.mockito.Mockito.mock;
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
public class IfElseTest extends TestCase
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
    public final void testSingleCommandTrue () throws ParserException
    {
        ParserResult result = SlogoParser.parse("ifelse 5 [ fd 50 ] [ bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(5, expression.evaluate(arena));
        verify(mockedTurtle).move(50);
    }


    @Test
    public final void testSingleCommandFalse () throws ParserException
    {
        ParserResult result = SlogoParser.parse("ifelse 0 [ fd 50 ] [ bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(0, expression.evaluate(arena));
        verify(mockedTurtle).move(-50);
    }


    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result =
            SlogoParser.parse("ifelse ifelse 3 [ fd 5 ] [ bk 5 ] [ fd 50 bk 50 ] [ fd 23 bk 23 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(3, expression.evaluate(arena));
        verify(mockedTurtle).move(5);
        verify(mockedTurtle).move(50);
        verify(mockedTurtle).move(-50);
    }


    @Test
    public final void testComputedConditional () throws ParserException
    {
        ParserResult result =
            SlogoParser.parse("ifelse 2+3 [ fd 50 bk 50 ] [ fd 23 bk 23 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(5, expression.evaluate(arena));
        verify(mockedTurtle).move(50);
        verify(mockedTurtle).move(-50);
    }


    @Test
    public final void testConstantConditional () throws ParserException
    {
        ParserResult result =
            SlogoParser.parse("ifelse 5 [ fd 50 bk 50 ] [ fd 23 bk 23 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(5, expression.evaluate(arena));
        verify(mockedTurtle).move(50);
        verify(mockedTurtle).move(-50);
    }

}
