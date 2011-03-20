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
import slogo.util.drawtools.DrawTool;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class PenDownTest extends TestCase
{

    private Arena arena;
    private DrawTool pen;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
        arena.selectTurtles(arena.addTurtle());
        pen = arena.getSelectedTurtles().get(0).getPen();
    }


    @Test
    public final void testWhenPenUp () throws ParserException
    {
        pen.putUp();
        simpleTest("pd");
    }


    @Test
    public final void testWhenPenDown () throws ParserException
    {
        pen.putDown();
        simpleTest("pd");
    }


    @Test
    public final void testLongName () throws ParserException
    {
        simpleTest("pendown");
    }


    private void simpleTest (String command) throws ParserException
    {
        ParserResult result = SlogoParser.parse(command);
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(Expression.TRUE, expression.evaluate(arena));
        assertTrue(pen.isDown());
    }
}
