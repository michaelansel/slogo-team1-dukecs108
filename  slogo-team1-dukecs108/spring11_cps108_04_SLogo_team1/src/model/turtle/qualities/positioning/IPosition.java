package model.turtle.qualities.positioning;

import java.awt.Point;

public interface IPosition
{
  
    
    public final static double NORTH = 90.0;
    public final static double SOUTH = 270.0;
    public final static double EAST = 0.0;
    public final static double WEST = 180.0;
    public final static double DEFAULT_ANGLE = NORTH;
    
    public abstract void setLocation (Point loc);

    public abstract Point getLocation ();

    public abstract double getX ();

    public abstract double getY ();

    public abstract double getAngle ();

    public abstract void setAngle (double angle);

    public abstract void changeAngle (double dAngle);

    
    /**
     * points turtle toward the target point
     * @param target
     */
    public abstract void changeAngle (Point target);
    

}
