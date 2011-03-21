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
public class HideTurtleTest extends TestCase
{

    private Arena arena;
    private Turtle turtle;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
        turtle = arena.getSelectedTurtles().get(0);
    }


    @Test
    public final void testWhenShowing () throws ParserException
    {
        turtle.showTurtle();
        simpleTest("ht");
    }


    @Test
    public final void testWhenHidden () throws ParserException
    {
        turtle.hideTurtle();
        simpleTest("ht");
    }


    @Test
    public final void testLongName () throws ParserException
    {
        simpleTest("hideturtle");
    }


    private void simpleTest (String command) throws ParserException
    {
        ParserResult result = SlogoParser.parse(command);
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(Expression.FALSE, expression.evaluate(arena));
        assertFalse(turtle.isVisible());
    }
}
