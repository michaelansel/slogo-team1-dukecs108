package slogo.controller;

import java.io.File;
import java.util.ArrayList; 
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import slogo.util.drawtools.Pen2D;
import slogo.view.gui.TurtleGUI;
import util.parser.ParserException;
import util.parser.ParserResult;



/**
 * Middle-man between GUI and Arena
 * @author Dave
 *
 */
public class Controller {	
	private ArrayList<Arena> myArenas = new ArrayList<Arena>();
	//private int ARENA_COUNT = 0;
	
	/**
	 * Creates a new TurtleGUI with a new Arena
	 */
	public Controller(){
		TurtleGUI t = new TurtleGUI(this);
		t.update(addArena(t));

		TurtleGUI t2 = new TurtleGUI(this);
//		t2.update(addArena(t2));
	}

   
   /**
    * Adds a new Arena with an observing TurtleGUI
    * @param t the TurtleGUI you add t to.
    */
   public Arena addArena(TurtleGUI t){
		Arena a = new Arena();
		myArenas.add(a);
		addAsObserver(t, a);
		return a;
   }
   
   /**
    * Adds an existing Arena with an observing TurtleGUI
    * @param t the TurtleGUI you wish to connect
    * @param ArenaID the number of the arena you want to connect to
    */
   public Arena addExistingArena(TurtleGUI t, int ArenaID){
	   if(ArenaID<=myArenas.size()&&ArenaID>0){
		   Arena a=myArenas.get(ArenaID-1);
		   addAsObserver(t, a);
		   return a;
	   }
	   else{
			return null;
	   }
   }
   
	
   /**
    * Adds the view to the arena's list of observers.
    * @param view the View you want to add as an observer
    * @param observer the arena you want to add view to
    */
  public void addAsObserver(Observer view, Observable arena){
   	arena.addObserver(view);
   }
   
	
    /**
     * Parse and evaluate the provided expression. The view is automatically
     * updated via Observer/Observable when the model state changes.
     * 
     * @param expression Expression to parse and evaluate
     * @throws ParserException 
     */
    public void evaluateExpression (String expression, Arena a) throws ParserException{
    	
    	//Parses the text, sets the first node to exp
		ParserResult result = SlogoParser.parse(expression);
		Expression exp = (Expression) result.getList().get(0);
		//Evaluates the expression recursively for Arena a
		exp.evaluate(a);

		//redraws attached GUIs
		a.setChanged("1");
		a.notifyObservers();
    }
    
    /**
     * Get the turtle map
     * @param a - an arena
     * @return the turtle map of arena a
     */
    public Map<Integer, Turtle> getTurtleMap(Arena a){
    	return a.getTurtleMap();
    }
    
    /**
     * Adds a turtle to an arena
     * @param a the arena you want to add a turtle to
     * @param name the name of this turtle
     * @param image the image file of this turtle
     * @param pen the pen of this turtle
     */
    public boolean addTurtle(Arena a, String name, File image, Pen2D pen){
    	boolean ret = a.addTurtle(name, image, pen);
    	
		//redraws attached GUIs
		a.setChanged("1");
		a.notifyObservers();
		return ret;
    }
    
    /**
     * removes a turtle from an arena
     * @param a the arena you want to remove a turtle from
     * @param turtleID the ID of the turtle you want to remove
     */
    public boolean removeTurtle(Arena a, int turtleID){
    	boolean ret = a.removeTurtle(turtleID);
    	

		//redraws attached GUIs
		a.setChanged("1");
		a.notifyObservers();
		return ret;
    }
    
    /**
     * Get ImageList-ready TurtleString/Image map
     * 
     */
    
    
    
    
}
