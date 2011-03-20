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
public class SetHeadingTest extends TestCase
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
    public final void testLongName () throws ParserException
    {
        ParserResult result = SlogoParser.parse("setheading 1");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.setHeading(1.0)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }
    
    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result = SlogoParser.parse("seth seth 1");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.setHeading(1.0)).thenReturn(17);
        when(mockedTurtle.setHeading(17.0)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
    }


    @Test
    public final void testComputedCoordinates () throws ParserException
    {
        ParserResult result = SlogoParser.parse("seth 5+10");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.setHeading(15.0)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }


    @Test
    public final void testConstantCoordinates () throws ParserException
    {
        ParserResult result = SlogoParser.parse("seth 50");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.setHeading(50.0)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }


    @Test
    public final void testNegativeCoordinates () throws ParserException
    {
        ParserResult result = SlogoParser.parse("seth -50");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.setHeading(-50.0)).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }

}
