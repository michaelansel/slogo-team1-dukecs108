package slogo.controller;

import java.util.ArrayList; 
import java.util.Observable;
import java.util.Observer;

import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
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

//		TurtleGUI t2 = new TurtleGUI(this);
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
    	
    	//Formats the string into an expression tree with root "exp"
        ParserResult result = SlogoParser.parse(expression);
        Expression exp = (Expression) result.getList().get(0);
        
    	exp.evaluate(a);
    }
}
