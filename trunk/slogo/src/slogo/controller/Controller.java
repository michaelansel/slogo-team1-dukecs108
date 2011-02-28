package slogo.controller;
import slogo.Main;
import slogo.view.Frame;
import util.parser.*;


/**
 * Middleman between frame and Arena
 * @author Dave
 *
 */
public class Controller {	
	/**
	 * constructor method, leave blank.
	 */
	Controller(){
		
	}
	
    /**
     * Parse and evaluate the provided expression. The view is automatically
     * updated when the model state changes.
     * 
     * @param expression Expression to parse and evaluate
     */
    public void evaluateExpression (String expression){
    	StringLexer l = new StringLexer(expression);
    	Main.myFrame.updatePanels();
    }
    //public void updatePanels (){

    	//main.myFrame.updatePanels();
    //}
	
	
}
