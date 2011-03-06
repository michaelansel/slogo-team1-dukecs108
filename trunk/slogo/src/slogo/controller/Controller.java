package slogo.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import slogo.view.TurtleGUI;
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
	 * constructor method
	 */
	public Controller(){
		TurtleGUI t = new TurtleGUI(this);
		t.update(addArena(t));
	}

   
   /**
    * Adds a new Arena with an observing TurtleGUI
    * @param t
    */
   public Arena addArena(TurtleGUI t){
		Arena a = new Arena();
		myArenas.add(a);
		addAsObserver(t, a);
		return a;
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
    	
    	//Formats the string into an expression tree with root "exp"
        ParserResult result = SlogoParser.parse(expression);
        Expression exp = (Expression) result.getList().get(0);
        
    	exp.evaluate(a);
    }
    
    
    /**
     * 
     */
    /*public void addTurtle(Arena a){
    	
    }*/
    
    /**
     * 
     */
    
 
    
    /**
     * grabs the graphic representation of the model as drawn by "ArenaDraw"
     * @param ArenaID the ID of the arena you want
     */
    /*public JPanel getDrawnPanel(int ArenaID){
    	for (Arena a: myArenas){
    		if (a.getID()==ArenaID){
    			return a.getPanel();
    		}
    	}
    	
    	return null;
    }*/
    
    
    /**
     * returns a list of Turtle Objects from the specified Arena
     * @param ArenaID the ID of the arena you want to grab the turtleList of
     */
    /*public Map<Integer, Turtle> getTurtleList(Arena a){
    	return a.getTurtleMap();
    }*/
}
