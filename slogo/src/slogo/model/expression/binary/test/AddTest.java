package slogo.model.expression.binary.test;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.expression.binary.Add;
import slogo.model.parser.SlogoLexer;
import util.parser.ParserException;


public class AddTest extends TestCase
{

    private Arena arena;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
        arena.setCurrentTurtleID(arena.addTurtle());
    }


    @Test
    public void testCreate () throws ParserException
    {
        List<Object> tokens =
            Arrays.asList(new Object[] {
                    new Constant(5),
                    SlogoLexer.Token.Add.makeToken("+"),
                    new Constant(10) });
        Expression expected = new Add(new Constant(5), new Constant(10));

        TestHelper.testCreate(tokens, expected);
    }


    @Test
    public void testEvaluate () throws ParserException
    {
        Expression expression = new Add(new Constant(5), new Constant(10));
        assertEquals(15, expression.evaluate(arena));
    }

}
