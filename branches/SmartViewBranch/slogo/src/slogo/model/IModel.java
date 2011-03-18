package slogo.model; 

import java.util.List;
import java.util.Map;
import slogo.model.expression.Expression;
import slogo.util.interfaces.IMorph;

public interface IModel {
	/**
	 * Animates individual turtles as called by update()
	 * 
	 * @param toAnimate
	 */
	public void animate(IMorph toAnimate);

	/**
	 * @return a list of previous Expressions
	 */
	public List<String> getExpressionHistory();

	/**
	 * Find or create Turtle with given ID
	 * 
	 * @param id
	 *            Specify which Turtle to retrieve
	 * @return Turtle with given id
	 */
	public IMorph getTurtleById(int id);

	/**
	 * @return Map of variables as Strings mapped to corresponding Expressions
	 */
	public Map<String, Expression> getVariables();

	/**
	 * Updates canvas to display all Turtle movements made since past update()
	 * call
	 */
	public void update();
}
