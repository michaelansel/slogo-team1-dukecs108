package slogo.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import slogo.view.TurtleGUI;
import slogo.view.panel.ArenaPanel;
import util.parser.ParserException;
import util.parser.ParserResult;



/**
 * Middle-man between GUI and Arena
 * @author Dave
 *
 */
public class Controller {	
//	private ArrayList<Arena> myArenas = new ArrayList<Arena>();
    private ArrayList<ArenaPanel> myArenaPanels = new ArrayList<ArenaPanel>();
    private TurtleGUI myGUI;
	//private int ARENA_COUNT = 0;
	
	/**
	 * Creates a new TurtleGUI with a new Arena
	 */
	public Controller(){
		myGUI = new TurtleGUI(this);
		myGUI.drawAndRepaint(myGUI.addArenaPanel(new Arena()));
	}

   
//   /**
//    * Adds a new Arena with an observing TurtleGUI
//    * @param t
//    */
//   public ArenaPanel addArenaPanel(TurtleGUI t){
//		ArenaPanel a = ;
//		myArenaPanels.add( a);
//		addAsObserver(t, a.getArena());
//		return a;
//   }
   
	
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
