/**
 * 
 */
package slogo.model.expression.command.test;

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
public class AskTest extends TestCase
{

    private Arena arena;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
    }


    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result =
            SlogoParser.parse("ask [ ask [ 1 2 ] [ fd 17 ] ask [ 3 4 ] [ fd 23 ] ] [ fd 31 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(31, expression.evaluate(arena));
    }


    @Test
    public final void testComputedIDs () throws ParserException
    {
        ParserResult result = SlogoParser.parse("ask [ 5+10 10+15 ] [ fd 31 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(31, expression.evaluate(arena));
    }


    @Test
    public final void testConstantIDs () throws ParserException
    {
        ParserResult result = SlogoParser.parse("ask [ 1 2 ] [ fd 17 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(17, expression.evaluate(arena));
    }


    @Test
    public final void testBehavior () throws ParserException
    {
        Turtle turtleA = mock(Turtle.class);
        Turtle turtleB = mock(Turtle.class);
        Turtle turtleC = mock(Turtle.class);
        int turtleAID = arena.addTurtle(turtleA);
        int turtleBID = arena.addTurtle(turtleB);
        arena.addTurtle(turtleC);

        ParserResult result =
            SlogoParser.parse(String.format("ask [ %d %d ] [ fd 50 ]",
                                            turtleAID,
                                            turtleBID));
        Expression expression = (Expression) result.getList().get(0);
        expression.evaluate(arena);

        verify(turtleA).move(50);
        verify(turtleB).move(50);
        verify(turtleC, never()).move(50);
    }
}
