package slogo.model.arena.turtle;

import java.util.List;
import slogo.util.drawables2D.Line;
import slogo.util.drawtools.IDrawTool2D;

/**
 * iterface to deal with the turtle's artistic qualities including: line, trace,
 * and how to draw those lines.
 * 
 * @author Julian
 * 
 */
public interface IArtist {

	/**
	 * Sets the pen to the passed in pen
	 * 
	 * @param newColor
	 */
	public abstract void setPen(IDrawTool2D newPen);

	/**
	 * get the current pen
	 * 
	 * @param newColor
	 */
	public abstract IDrawTool2D getPen();

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
