package slogo.model.arena;

import slogo.controller.Controller; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo.controller.Controller;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;

/**
 * An extended version of the Arena class fit to be run with the MVC
 * Program structure. This version REQUIRES a controller and ID to be
 * specified (by the controller) on creation. Should support multiple
 * views linking to one Arena, although this is not yet tested.
 * 
 * @author Dave
 *
 */ 


public class IDArena extends Arena implements Cloneable
{
	protected int myArenaID;
	protected Controller myController;

    /**
     * Create a new Arena with a default Turtle (Jim), containing a pointer
     * its controller which it will listen to as well as return prompts such
     * as "changed" to alert the controller that it's time to do something.
     * 
     * @param id is used by the controller to access this Arena in specific.
     * Calls to each Arena will need to be thusly specified, while calls to
     * all linked views will be handled by the controller.
     */
    public IDArena (Controller c, int id)
    {
        this(c, id, new Turtle("Turtle Jim"));
    }


    /**
     * Create a new Arena with the given Turtle
     * 
     * @param turtle First Turtle in the new Arena
     */
    public IDArena (Controller c, int id, Turtle turtle)
    {
    	super(turtle);
    	myController = c;
    	myArenaID = id;
    }


    /**
     * Create a deep clone of the current Arena
     */
    public IDArena clone ()
    {
        IDArena newArena;
        newArena = (IDArena) super.clone();
        newArena.myController = myController;
        //TODO: Figure out what to do with ArenaID when a clone is made.
        //TODO: Do we want to keep this one's ID? Or do we want to grab the
        //TODO: next available from controller?
        newArena.myArenaID = myArenaID;

        return newArena;
    }
    
    /**
     * Returns the ID of this Arena Object, as determined by the
     * original constructor call from the Controller. Each Arena
     * ID over the lifespan of a Controller will be unique. Each
     * opened file will create a new ID, so that we don't have
     * repeats. It's analogus to a temporary address of the Arena.
     */
    public int getID(){
    	return myArenaID;
    }

}

