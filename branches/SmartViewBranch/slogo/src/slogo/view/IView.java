package slogo.view;

import java.util.Observer;
import slogo.model.IModel;

public interface IView extends Observer {
	/**
	 * Parses command expression, sending information to Controller
	 */
	public void computeExpression();

	/**
	 * Contains IModel class, which contains Turtle information
	 */
	public IModel getModel();

	/**
	 * Clears current and creates new Canvas
	 */
	public void newCanvas();

	/**
	 * Opens and displays a pre-saved TurtleMap
	 * 
	 * @param toOpen
	 */
	public void open(String toOpen);

	/**
	 * Saves current TurtleMap to file
	 * 
	 * @param toSave
	 */
	public void save(String toSave);

}
