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
public class RandomTest extends TestCase
{

    private Arena arena;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
        arena.selectTurtles(arena.addTurtle());
    }


    @Test
    public final void testCommandAsParameter () throws ParserException
    {
        ParserResult result = SlogoParser.parse("random random 5");
        Expression expression = (Expression) result.getList().get(0);
        int val = expression.evaluate(arena);
        assertTrue(0 <= val && val <= 5);
    }


    @Test
    public final void testComputedMaximum () throws ParserException
    {
        ParserResult result = SlogoParser.parse("random 5+10");
        Expression expression = (Expression) result.getList().get(0);
        int val = expression.evaluate(arena);
        assertTrue(0 <= val && val <= 15);
    }


    @Test
    public final void testConstantMaximum () throws ParserException
    {
        ParserResult result = SlogoParser.parse("random 5");
        Expression expression = (Expression) result.getList().get(0);
        int val = expression.evaluate(arena);
        assertTrue(0 <= val && val <= 5);
    }
}
