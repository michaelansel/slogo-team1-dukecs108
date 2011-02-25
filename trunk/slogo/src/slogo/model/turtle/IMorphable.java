package slogo.model.turtle;

import java.awt.Point;


/**
 * Contains various methods involved in updating a turtle's qualities. Makes a
 * turtle movable.
 * 
 * @author Julian Genkins
 */
public interface IMorphable
{

    public double getHeading ();


    public Point getPosition ();


    public void move (double distance);


    public int moveTo (Point target);


    public void rotate (double angle);


    public void setImage (TurtleImage newImage);


    public void update (Morph morph);

}
