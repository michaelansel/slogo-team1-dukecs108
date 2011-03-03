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
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class ForwardTest extends TestCase
{

    private Arena arena;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
        arena.setCurrentTurtleID(arena.addTurtle());
    }


    @Test
    public final void testConstantDistance () throws ParserException
    {
        ParserResult result = SlogoParser.parse("fd 50");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(50, expression.evaluate(arena));
    }


    @Test
    public final void testComputedDistance () throws ParserException
    {
        ParserResult result = SlogoParser.parse("fd 5+10");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(15, expression.evaluate(arena));
    }


    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result = SlogoParser.parse("fd fd 50");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(50, expression.evaluate(arena));
    }


    @Test
    public final void testNegativeDistance () throws ParserException
    {
        ParserResult result = SlogoParser.parse("fd -50");
        Expression expression = (Expression) result.getList().get(0);
        assertEquals(50, expression.evaluate(arena));
    }

}
