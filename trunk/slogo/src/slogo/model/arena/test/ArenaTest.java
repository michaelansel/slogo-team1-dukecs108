/**
 * 
 */
package slogo.model.arena.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public class ArenaTest extends TestCase
{

    private Arena arena;


    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
    }


    /**
     * Test for {@link Arena#clone()}
     */
    @Test
    public final void testClone ()
    {
        Arena clone = arena.clone();
        assertNotSame(arena, clone);
        assertEquals(arena.getSelectedTurtles(), clone.getSelectedTurtles());
        assertEquals(arena.getTurtleMap().values(), clone.getTurtleMap().values());
        // TODO further verification of Arena.clone
    }


    /**
     * Test for {@link Arena#getCenter()}
     */
    @Test
    public final void testGetCenter ()
    {
        fail("Poorly defined behavior"); // TODO Define Arena.getCenter() behavior
    }


    /**
     * Test for {@link Arena#addHistoryEntry(String)} and
     * {@link Arena#getHistoryEntry(int)}
     */
    @Test
    public final void testHistory ()
    {
        String entry = "my expression";
        int idx = arena.addHistoryEntry(entry);
        assertEquals(entry, arena.getHistoryEntry(idx));
    }


    /**
     * Test for {@link Arena#getUserCommand(String)} and
     * {@link Arena#addUserCommand(String, Expression)}
     */
    @Test
    public final void testUserCommandDefine ()
    {
        Expression value = new Constant(17);
        assertNull(arena.getUserCommand("mycmd"));
        arena.addUserCommand("mycmd", value);
        assertEquals(value, arena.getUserCommand("mycmd"));
    }


    /**
     * Test for {@link Arena#getUserCommand(String)} and
     * {@link Arena#addUserCommand(String, Expression)}
     */
    @Test
    public final void testUserCommandRedefine ()
    {
        Expression oldValue = new Constant(17);
        Expression newValue = new Constant(23);
        arena.addUserCommand("mycmd", oldValue);
        assertNotNull(arena.getUserCommand("mycmd"));
        assertTrue(arena.getUserCommand("mycmd") != newValue);

        arena.setVariable("mycmd", newValue);
        assertEquals(newValue, arena.getVariable("mycmd"));
    }


    /**
     * Test for {@link Arena#getVariable(String)} and
     * {@link Arena#setVariable(String, Expression)}
     */
    @Test
    public final void testVariableDefine ()
    {
        Expression value = new Constant(17);
        assertNull(arena.getVariable("myvar"));
        arena.setVariable("myvar", value);
        assertEquals(value, arena.getVariable("myvar"));
    }


    /**
     * Test for {@link Arena#getVariable(String)} and
     * {@link Arena#setVariable(String, Expression)}
     */
    @Test
    public final void testVariableRedefine ()
    {
        Expression oldValue = new Constant(17);
        Expression newValue = new Constant(23);
        arena.setVariable("myvar", oldValue);
        assertNotNull(arena.getVariable("myvar"));
        assertTrue(arena.getVariable("mycmd") != newValue);

        arena.setVariable("myvar", newValue);
        assertEquals(newValue, arena.getVariable("myvar"));
    }

}
