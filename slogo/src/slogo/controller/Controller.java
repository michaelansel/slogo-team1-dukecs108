package slogo.controller;

import java.io.File; 
import java.util.ArrayList;

import javax.swing.JPanel;

import slogo.model.arena.IDArena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.arena.turtle.qualities.positioning.Position;
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
	private ArrayList<IDArena> myArenas=new ArrayList<IDArena>();
	private ArrayList<TurtleGUI> myTurtleGUIs=new ArrayList<TurtleGUI>();
	public int ARENA_COUNT=0;
	public int GUI_COUNT=0;
	
	/**
	 * constructor method
	 */
	public Controller(){

        Turtle jim = new Turtle("Turtle Jim");
        File pic = new File("src/image/turtlecloud.png");
        jim.setImage(pic);
        jim.setPosition(new Position(180, 165));
		IDArena a = new IDArena(this, ARENA_COUNT, jim);
		
		
		ARENA_COUNT++;
		myTurtleGUIs.add(new TurtleGUI(GUI_COUNT));
		GUI_COUNT++;
		myTurtleGUIs.get(0).addArena(myArenas.get(0).getID());
	}
	
    /**
     * Parse and evaluate the provided expression. The view is automatically
     * updated when the model state changes.
     * 
     * Compatible with multiple views and multiple Arenas. Any combo will update
     * correctly, going through this 'controller' (ie by using SLogo main function
     * to access myController).
     * 
     * @param expression Expression to parse and evaluate
     * @throws ParserException 
     */
    public void evaluateExpression (String expression, int ArenaID) throws ParserException{
    	IDArena target = null;
        ParserResult result = SlogoParser.parse(expression);
        Expression exp = (Expression) result.getList().get(0);
        
    	for (IDArena a: myArenas){
    		if (a.getID()==ArenaID){
    			target=a;
    		}
    	}
    	exp.evaluate(target);
    	target.updatePanel();
    	
    	for (TurtleGUI t: myTurtleGUIs){
    		if (t.hasArena(ArenaID))
    			t.updateArena(ArenaID);
    	}
    }
    
    /**
     * grabs the graphic representation of the model as drawn by "ArenaDraw"
     * @param ArenaID the ID of the arena you want
     */
    public JPanel getDrawnPanel(int ArenaID){
    	for (IDArena a: myArenas){
    		if (a.getID()==ArenaID){
    			return a.getPanel();
    		}
    	}
    	
    	return null;
    }
    
    
    /**
     * returns a list of Turtle Objects from the specified Arena
     * @param ArenaID the ID of the arena you want to grab the turtleList of
     */
    public ArrayList<Turtle> getTurtleList(int ArenaID){
    	ArrayList<Turtle> t = new ArrayList<Turtle>();
    	for (IDArena a: myArenas){
    		if (a.getID()==ArenaID){
    			return a.getTurtleList();
    		}
    	}
    	
    	return t;
    }
}
