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
public class ClearScreenTest extends TestCase
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
    public final void testShortName () throws ParserException
    {
        simpleTest("cs");
    }


    @Test
    public final void testLongName () throws ParserException
    {
        simpleTest("clearscreen");
    }


    private void simpleTest (String command) throws ParserException
    {
        ParserResult result = SlogoParser.parse(command);
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.resetTurtle(arena.getCenter())).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }
}
