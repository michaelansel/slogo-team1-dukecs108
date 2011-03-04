package slogo.model.arena.turtle;

import java.util.List;
import slogo.util.Line;
import slogo.util.Pen;

/**
 * iterface to deal with the turtle's artistic qualities including: line, trace,
 * and how to draw those lines.
 * 
 * @author Julian
 * 
 */
// TODO: Involved with pixmap interaction?
public interface IArtist {

	/**
	 * Sets the Trace of the pen to the passed in trace
	 * 
	 * @param newColor
	 */
	public abstract void setPen(Pen newPen);

	/**
	 * Sets the Trace of the pen to the passed in trace
	 * 
	 * @param newColor
	 */
	public abstract Pen getPen();

	/**
	 * return's the turtle's list of lines
	 * 
	 * @return
	 */
	public abstract List<Line> getLines();

	/**
	 * adds a new line to the turtle's set of lines
	 * 
	 * @param newLine
	 */
	public abstract void addLine(Line newLine);

	/**
	 * adds a list of lines to the turtle's set of lines
	 * 
	 * @param newLine
	 */
	public abstract void addAllLines(List<Line> newLines);

	/**
	 * removes a new line to the turtle's set of lines
	 * 
	 * @param newLine
	 */
	public abstract void revomeLine(Integer index);

	/**
	 * removes all lines the turtle holds
	 * 
	 * @param newLine
	 */
	public abstract void clearLines();

	/**
	 * removes a specific set of lines the turtle holds
	 * 
	 * @param newLine
	 */
	public abstract void removeLines(List<Line> lines);



}
