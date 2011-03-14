/**
 * 
 */
package slogo.model.expression.command.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class TellTest extends TestCase
{

    private Arena mockedArena;


    @Before
    public void setUp () throws Exception
    {
        mockedArena = mock(Arena.class);
    }


    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result =
            SlogoParser.parse("tell [ tell [ 1 2 ] tell [ 3 4 ] ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(4, expression.evaluate(mockedArena));
        // TODO Set list of current Turtle IDs instead of only a single one
        verify(mockedArena).setCurrentTurtleID(2);
        verify(mockedArena, times(2)).setCurrentTurtleID(4);
    }


    @Test
    public final void testComputedIDs () throws ParserException
    {
        ParserResult result = SlogoParser.parse("tell [ 5+10 10+15 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(25, expression.evaluate(mockedArena));
        // TODO Set list of current Turtle IDs instead of only a single one
        verify(mockedArena).setCurrentTurtleID(25);
    }


    @Test
    public final void testConstantIDs () throws ParserException
    {
        ParserResult result = SlogoParser.parse("tell [ 1 2 ]");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(2, expression.evaluate(mockedArena));
        // TODO Set list of current Turtle IDs instead of only a single one
        verify(mockedArena).setCurrentTurtleID(2);
    }
}
