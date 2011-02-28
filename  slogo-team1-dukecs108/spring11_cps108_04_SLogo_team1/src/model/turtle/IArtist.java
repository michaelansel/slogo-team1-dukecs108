package model.turtle;

import java.awt.Color;
import java.awt.Point;
import java.util.Collection;
import java.util.List;

import model.line.Line;
import model.turtle.qualities.trace.Trace;

/**
 * iterface to deal with the turtle's artistic qualities including:
 * line, trace, and how to draw those lines. 
 * @author Julian
 *
 */
//TODO: Involved with pixmap interaction?
public interface IArtist {
	
	/**
	 * Sets the Trace of the pen to the passed in trace
	 * @param newColor
	 */
	public abstract void setTrace(Trace newTrace);
	
	/**
     * Sets the Trace of the pen to the passed in trace
     * @param newColor
     */
    public abstract Trace getTrace();
		
	
	/**
	 * return's the turtle's list of lines
	 * @return
	 */
	public abstract List<Line> getLines();
	
	
	/**
	 * adds a new line to the turtle's set of lines
	 * @param newLine
	 */
	public abstract void addLine(Line newLine);
	
	/**
     * adds a list of lines to the turtle's set of lines
     * @param newLine
     */
    public abstract void addAllLines(List<Line> newLines);
	
	/**
     * removes a new line to the turtle's set of lines
     * @param newLine
     */
    public abstract void revomeLine(Integer index);
	
    
    /**
     * removes all lines the turtle holds
     * @param newLine
     */
    public abstract void clearLines();
    
    
    /**
     * removes a specific set of lines the turtle holds
     * @param newLine
     */
    public abstract void removeLines(List<Line> lines);

    /**
     * returns a set of lines to draw, may not be the same as the set of lines held by the turtle
     * @param start
     * @return
     */
    Iterable<Line> linesToDraw (int start);
    
    
   
    
    
}
