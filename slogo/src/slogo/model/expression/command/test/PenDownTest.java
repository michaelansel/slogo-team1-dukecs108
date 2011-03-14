/**
 * 
 */
package slogo.model.expression.command.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import slogo.util.Pen;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class PenDownTest extends TestCase
{

    private Arena arena;
    private Pen pen;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
        arena.setCurrentTurtleID(arena.addTurtle());
        pen = arena.getCurrentTurtle().getPen();
    }


    @Test
    public final void testWhenPenUp () throws ParserException
    {
        pen.putUp();
        simpleTest();
    }


    @Test
    public final void testWhenPenDown () throws ParserException
    {
        pen.putDown();
        simpleTest();
    }


    private void simpleTest () throws ParserException
    {
        ParserResult result = SlogoParser.parse("pendown");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(Expression.TRUE, expression.evaluate(arena));
        assertTrue(pen.isDown());
    }
}
