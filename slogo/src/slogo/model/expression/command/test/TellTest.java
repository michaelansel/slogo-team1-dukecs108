/**
 * 
 */
package slogo.model.expression.command.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;
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
public class TellTest extends TestCase
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
            SlogoParser.parse("tell [ tell [ 1 2 ] tell [ 3 4 ] ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(4, expression.evaluate(arena));
        List<Turtle> expected =
            Arrays.asList(new Turtle[] { arena.getTurtle(2), arena.getTurtle(4) });
        assertEquals(expected, arena.getSelectedTurtles());
    }


    @Test
    public final void testComputedIDs () throws ParserException
    {
        ParserResult result = SlogoParser.parse("tell [ 5+10 10+15 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(25, expression.evaluate(arena));
        List<Turtle> expected =
            Arrays.asList(new Turtle[] {
                    arena.getTurtle(15),
                    arena.getTurtle(25) });
        assertEquals(expected, arena.getSelectedTurtles());
    }


    @Test
    public final void testConstantIDs () throws ParserException
    {
        ParserResult result = SlogoParser.parse("tell [ 1 2 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(2, expression.evaluate(arena));
        List<Turtle> expected =
            Arrays.asList(new Turtle[] { arena.getTurtle(1), arena.getTurtle(2) });
        assertEquals(expected, arena.getSelectedTurtles());
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
            SlogoParser.parse(String.format("tell [ %d %d ]",
                                            turtleAID,
                                            turtleBID));
        Expression expression = (Expression) result.getList().get(0);
        expression.evaluate(arena);

        result = SlogoParser.parse("fd 50");
        expression = (Expression) result.getList().get(0);
        expression.evaluate(arena);

        verify(turtleA).move(50);
        verify(turtleB).move(50);
        verify(turtleC, never()).move(50);
    }
}
