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
import slogo.util.Position;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class XCorTest extends TestCase
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
    public final void testSimple () throws ParserException
    {
        ParserResult result = SlogoParser.parse("xcor");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.getPosition()).thenReturn(new Position(10, 20));
        assertEquals(10, expression.evaluate(arena));
    }

}
