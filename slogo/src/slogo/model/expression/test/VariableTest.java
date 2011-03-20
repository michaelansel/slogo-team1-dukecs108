package slogo.model.expression.test;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.expression.Variable;
import slogo.model.parser.SlogoLexer;
import util.parser.ParserException;
import util.parser.ParserResult;


public class VariableTest extends TestCase
{

    private Arena arena;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
    }


    private void testCreate (List<Object> tokens, Expression expected)
        throws ParserException
    {
        ParserResult result =
            Variable.getParserResultHandler()
                    .handleResult(new ParserResult(tokens));
        assertTrue(result.getList().toString(),
                   result.getList().get(0) instanceof Expression);
        Expression actual = (Expression) result.getList().get(0);
        assertEquals(expected.toString(), actual.toString());
    }


    @Test
    public final void testCreateAssignment () throws ParserException
    {
        List<Object> tokens =
            Arrays.asList(new Object[] {
                    new SlogoLexer("").getTokenRuleByName("Variable")
                                      .makeToken(":myvar"),
                    new SlogoLexer("").getTokenRuleByName("AssignmentOperator")
                                      .makeToken("="),
                    new Constant(10) });
        Expression expected = new Variable(":myvar", new Constant(10));

        testCreate(tokens, expected);
    }


    @Test
    public final void testCreateLookup () throws ParserException
    {
        List<Object> tokens =
            Arrays.asList(new Object[] { new SlogoLexer("").getTokenRuleByName("Variable")
                                                           .makeToken(":myvar") });
        Expression expected = new Variable(":myvar");

        testCreate(tokens, expected);
    }


    @Test
    public final void testEvaluateAssignment ()
    {
        Expression expression = new Variable(":myvar", new Constant(5));
        assertEquals(0, expression.evaluate(arena));
        assertEquals(5, arena.getVariable(":myvar").evaluate(arena));
    }


    @Test
    public final void testEvaluateLookup ()
    {
        Expression expression = new Variable(":myvar");
        arena.setVariable(":myvar", new Constant(15));
        assertEquals(15, expression.evaluate(arena));
    }

}
