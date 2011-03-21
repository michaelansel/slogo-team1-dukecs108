/**
 * 
 */
package slogo.model.arena.test;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;


/**
 * @author Michael Ansel
 */
public class ArenaTurtleTest extends TestCase
{

    private Arena arena;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {
        arena = new Arena();
    }


    /**
     * Test method for {@link slogo.model.arena.Arena#addTurtle()}.
     */
    @Test
    public final void testAddFirstTurtle ()
    {
        arena.removeTurtle(arena.getTurtleMap()
                                .keySet()
                                .toArray(new Integer[] {})[0]);
        assertTrue(arena.getTurtleMap().isEmpty());
        int id = arena.addTurtle();
        // The first Turtle should be automatically selected
        List<Turtle> expected =
            Arrays.asList(new Turtle[] { arena.getTurtle(id) });
        assertEquals(expected, arena.getSelectedTurtles());
    }


    /**
     * Test method for {@link slogo.model.arena.Arena#addTurtle()}.
     */
    @Test
    public final void testAddMoreTurtles ()
    {
        arena.removeTurtle(arena.getTurtleMap()
                                .keySet()
                                .toArray(new Integer[] {})[0]);
        assertTrue(arena.getTurtleMap().isEmpty());
        int firstID = arena.addTurtle();

        int nextID = arena.addTurtle();
        // Adding an additional Turtle should not change the selection list
        List<Turtle> expected =
            Arrays.asList(new Turtle[] { arena.getTurtle(firstID) });
        assertEquals(expected, arena.getSelectedTurtles());
        assertNotNull(arena.getTurtle(nextID));
    }


    /**
     * Test method for
     * {@link slogo.model.arena.Arena#addTurtle(slogo.model.arena.turtle.Turtle)}
     * .
     */
    @Test
    public final void testAddTurtleObject ()
    {
        arena.removeTurtle(arena.getTurtleMap()
                                .keySet()
                                .toArray(new Integer[] {})[0]);
        assertTrue(arena.getTurtleMap().isEmpty());

        Turtle firstTurtle = new Turtle();
        int firstID = arena.addTurtle(firstTurtle);

        Turtle nextTurtle = new Turtle();
        int nextID = arena.addTurtle(nextTurtle);

        List<Turtle> expected = Arrays.asList(new Turtle[] { firstTurtle });
        assertEquals(expected, arena.getSelectedTurtles());

        assertSame(firstTurtle, arena.getTurtle(firstID));
        assertSame(nextTurtle, arena.getTurtle(nextID));
    }


    /**
     * Test method for {@link slogo.model.arena.Arena#Arena()}.
     */
    @Test
    public final void testArena ()
    {
        arena = new Arena();
        assertEquals(1, arena.getSelectedTurtles().size());
    }


    /**
     * Test method for
     * {@link slogo.model.arena.Arena#Arena(slogo.model.arena.turtle.Turtle)}.
     */
    @Test
    public final void testArenaTurtle ()
    {
        Turtle turtle = new Turtle();
        arena = new Arena(turtle);
        List<Turtle> expected = Arrays.asList(new Turtle[] { turtle });
        assertEquals(expected, arena.getSelectedTurtles());
    }


    @Test
    public final void testSelectTurtles ()
    {
        Turtle turtleA = arena.getSelectedTurtles().get(0);
        Turtle turtleB = arena.getTurtle(arena.addTurtle());
        int turtleCID = arena.addTurtle();

        arena.selectTurtles(turtleA, turtleB);
        List<Turtle> expected =
            Arrays.asList(new Turtle[] { turtleA, turtleB });
        assertEquals(expected, arena.getSelectedTurtles());

        arena.selectTurtles(turtleCID);
        expected = Arrays.asList(new Turtle[] { arena.getTurtle(turtleCID) });
        assertEquals(expected, arena.getSelectedTurtles());
    }


    /**
     * Test method for {@link slogo.model.arena.Arena#getTurtle(int)}.
     */
    @Test
    public final void testGetTurtle ()
    {
        int turtleID = (Integer) arena.getTurtleMap().keySet().toArray()[0];
        assertEquals(arena.getSelectedTurtles().get(0),
                     arena.getTurtle(turtleID));
    }


    /**
     * Test method for {@link slogo.model.arena.Arena#removeTurtle(int)}.
     */
    @Test
    public final void testRemoveTurtle ()
    {
        int turtleID = (Integer) arena.getTurtleMap().keySet().toArray()[0];
        assertTrue(arena.removeTurtle(turtleID));
        assertFalse(arena.removeTurtle(turtleID));
    }

}
