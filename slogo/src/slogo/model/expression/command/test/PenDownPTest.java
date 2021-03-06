/**
 * 
 */
package slogo.model.expression.command.test;

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
public class PenDownPTest extends TestCase
{

    private Arena arena;
    private Turtle turtle;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
        arena.selectTurtles(arena.addTurtle());
        turtle = arena.getSelectedTurtles().get(0);
    }


    @Test
    public final void testPMode () throws ParserException
    {
        // when pen down
        turtle.getPen().putDown();
        simpleTest("pendownp", Expression.TRUE);
        // when pen up
        turtle.getPen().putUp();
        simpleTest("pendownp", Expression.FALSE);
    }


    @Test
    public final void testQMode () throws ParserException
    {
        // when pen down
        turtle.getPen().putDown();
        simpleTest("pendown?", Expression.TRUE);
        // when pen up
        turtle.getPen().putUp();
        simpleTest("pendown?", Expression.FALSE);
    }


    private void simpleTest (String command, int expected)
        throws ParserException
    {
        ParserResult result = SlogoParser.parse(command);
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(expected, expression.evaluate(arena));
    }

}
