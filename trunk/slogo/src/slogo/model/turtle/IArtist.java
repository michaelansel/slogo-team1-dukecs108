package slogo.model.turtle;

import java.util.List;
import slogo.util.Line;
import slogo.util.Pen;


/**
 * @author Julian Genkins
 */
public interface IArtist
{

    /**
     * adds a new line to the turtle's set of lines
     * 
     * @param newLine
     */
    public void addLine (Line newLine);


    /**
     * return's the turtle's list of lines
     * 
     * @return
     */
    public List<Line> getLines ();


    /**
     * returns the pen
     */
    public Pen getPen ();


    /**
     * returns the current status of the pen
     */
    public boolean getPenStatus ();


    /**
     * puts pen down (sets pen to true)
     */
    public void putPenDown ();


    /**
     * puts pen up (sets pen to false)
     */
    public void putUpPen ();


    /**
     * @param pen new pen
     */
    public void setPen (Pen pen);

}
