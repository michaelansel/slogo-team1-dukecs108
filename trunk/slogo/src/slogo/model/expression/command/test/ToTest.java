/**
 * 
 */
package slogo.model.expression.command.test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class ToTest extends TestCase
{

    private Arena arena;
    private Turtle mockedTurtle;


    @Before
    public void setUp () throws Exception
    {
        mockedTurtle = mock(Turtle.class);
        arena = new Arena();
        arena.selectTurtles(arena.addTurtle(mockedTurtle));
    }


    @Test
    public final void testDefineWithSingleCommand () throws ParserException
    {
        ParserResult result = SlogoParser.parse("to walk [ fd 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        // TODO what is "to" supposed to return?
        assertEquals(0, expression.evaluate(arena));
        // command should not be run on definition
        verify(mockedTurtle, never()).move(50);
    }
    
    @Test
    public final void testRedefineUserCommand () throws ParserException
    {
        ParserResult result = SlogoParser.parse("to ski [ fd 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        // TODO what is "to" supposed to return?
        assertEquals(0, expression.evaluate(arena));
        // command should not be run on definition
        verify(mockedTurtle, never()).move(50);

        result = SlogoParser.parse("to ski [ bk 50 ]");
        expression = (Expression) result.getList().get(0);
        // TODO what is "to" supposed to return?
        assertEquals(0, expression.evaluate(arena));
        // command should not be run on definition
        verify(mockedTurtle, never()).move(-50);
        
        result = SlogoParser.parse("ski");
        expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(-50)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
    }
    
    @Test
    public final void testRecursiveUserCommand () throws ParserException
    {
        // TODO don't require pre-definition of the command
        ParserResult result = SlogoParser.parse("to hop [ :state ] [ fd 13 ]");
        Expression expression = (Expression) result.getList().get(0);
        expression.evaluate(arena);
        
        result = SlogoParser.parse("to hop [ :state ] [ ifelse :state [ hop 0 ] [ fd :state ]  ]");
        expression = (Expression) result.getList().get(0);
        assertEquals(0, expression.evaluate(arena));
        
        result = SlogoParser.parse("hop 1");
        expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(0)).thenReturn(23);
        assertEquals(1, expression.evaluate(arena));
        verify(mockedTurtle).move(0);
        verify(mockedTurtle,never()).move(13);
    }
    
// Not a valid command
//    @Test
//    public final void testRedefineBuiltinCommand () throws ParserException
//    {
//        ParserResult result = SlogoParser.parse("to forward [ :distance ] [ bk :distance ]");
//        Expression expression = (Expression) result.getList().get(0);
//        // TODO what is "to" supposed to return?
//        assertEquals(0, expression.evaluate(arena));
//        // command should not be run on definition
//        verify(mockedTurtle, never()).move(anyInt());
//        
//        // built-in command should override user command
//        when(mockedTurtle.move(50)).thenReturn(17);
//        when(mockedTurtle.move(-50)).thenReturn(23);
//        assertEquals(17, expression.evaluate(arena));
//    }


    @Test
    public final void testDefineWithMultipleCommands () throws ParserException
    {
        ParserResult result = SlogoParser.parse("to run [ fd 50 bk -50 ]");
        Expression expression = (Expression) result.getList().get(0);
        // TODO what is "to" supposed to return?
        assertEquals(0, expression.evaluate(arena));
        // command should not be run on definition
        verify(mockedTurtle, never()).move(50);
        verify(mockedTurtle, never()).move(-50);
    }


    @Test
    public final void testExecuteUserDefinedCommand () throws ParserException
    {
        ParserResult result = SlogoParser.parse("to drive [ fd 50 bk 50 ]");
        Expression expression = (Expression) result.getList().get(0);
        expression.evaluate(arena);

        result = SlogoParser.parse("drive");
        expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(50)).thenReturn(17);
        when(mockedTurtle.move(-50)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
        verify(mockedTurtle).move(50);
        verify(mockedTurtle).move(-50);
    }


    @Test
    public final void testDefineWithParameters () throws ParserException
    {
        ParserResult result =
            SlogoParser.parse("to fly [ :distance ] [ fd :distance bk :distance ]");
        Expression expression = (Expression) result.getList().get(0);
        // TODO what is "to" supposed to return?
        assertEquals(0, expression.evaluate(arena));
        // command should not be run on definition
        verify(mockedTurtle, never()).move(anyInt());
        verify(mockedTurtle, never()).move(anyInt());
    }


    @Test
    public final void testExecuteUserDefinedCommandWithParameters ()
        throws ParserException
    {
        ParserResult result =
            SlogoParser.parse("to swim [ :distance ] [ fd :distance bk :distance ]");
        Expression expression = (Expression) result.getList().get(0);
        expression.evaluate(arena);

        result = SlogoParser.parse("swim 50");
        expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(50)).thenReturn(17);
        when(mockedTurtle.move(-50)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
    }


    @Test
    public final void testGlobalVariableConflict () throws ParserException
    {
        arena.setVariable(":distance", new Constant(99));

        ParserResult result =
            SlogoParser.parse("to stumble [ :distance ] [ fd :distance bk :distance ]");
        Expression expression = (Expression) result.getList().get(0);
        expression.evaluate(arena);

        result = SlogoParser.parse("stumble 50");
        expression = (Expression) result.getList().get(0);
        when(mockedTurtle.move(50)).thenReturn(17);
        when(mockedTurtle.move(-50)).thenReturn(23);
        assertEquals(23, expression.evaluate(arena));
        // global variable value should be preserved
        assertEquals(99, arena.getVariable(":distance").evaluate(arena));
    }
}
