/**
 * 
 */
package slogo.model.expression.command.test;

import static org.mockito.Mockito.*;
import java.awt.geom.Point2D;
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
public class SetXYTest extends TestCase
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
    public final void testLongName () throws ParserException
    {
        ParserResult result = SlogoParser.parse("setxy 1 2");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(new Point2D.Double(1.0, 2.0))).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }
    
    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        // TODO Parser should not require parameters to be grouped if they are unambiguous
        ParserResult result = SlogoParser.parse("setxy (setxy 1 2) (setxy 3 4)");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(new Point2D.Double(1.0, 2.0))).thenReturn(17);
        when(mockedTurtle.move(new Point2D.Double(3.0, 4.0))).thenReturn(23);
        when(mockedTurtle.move(new Point2D.Double(17.0, 23.0))).thenReturn(31);
        assertEquals(31, expression.evaluate(arena));
    }


    @Test
    public final void testComputedCoordinates () throws ParserException
    {
        ParserResult result = SlogoParser.parse("setxy 5+10 11+7");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(new Point2D.Double(15.0, 18.0))).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }


    @Test
    public final void testConstantCoordinates () throws ParserException
    {
        ParserResult result = SlogoParser.parse("setxy 50 90");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(new Point2D.Double(50.0, 90.0))).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }


    @Test
    public final void testNegativeCoordinates () throws ParserException
    {
        // TODO Parser confuses 2 negative parameters with a single "negative plus positive" parameter
        ParserResult result = SlogoParser.parse("setxy -50 (-90)");
        Expression expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(new Point2D.Double(-50.0, -90.0))).thenReturn(17);
        assertEquals(17, expression.evaluate(arena));
    }

}
