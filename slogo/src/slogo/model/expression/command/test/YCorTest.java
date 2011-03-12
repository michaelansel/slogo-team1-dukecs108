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
import slogo.model.arena.turtle.qualities.positioning.Position;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class YCorTest extends TestCase
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
    public final void testSimple () throws ParserException
    {
        ParserResult result = SlogoParser.parse("ycor");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.getPosition()).thenReturn(new Position(10, 20));
        assertEquals(20, expression.evaluate(arena));
    }

}
