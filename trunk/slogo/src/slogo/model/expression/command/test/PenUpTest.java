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
public class PenUpTest extends TestCase
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
        simpleTest("pu");
    }


    @Test
    public final void testWhenPenDown () throws ParserException
    {
        pen.putDown();
        simpleTest("pu");
    }


    @Test
    public final void testLongName () throws ParserException
    {
        simpleTest("penup");
    }


    private void simpleTest (String command) throws ParserException
    {
        ParserResult result = SlogoParser.parse(command);
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(Expression.FALSE, expression.evaluate(arena));
        assertTrue(pen.isUp());
    }
}
