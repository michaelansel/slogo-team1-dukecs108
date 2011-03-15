package slogo.util.interfaces;

import java.awt.geom.Point2D;
import slogo.util.drawables2D.Line;


public interface IMorph
{

    public abstract int move (double distance);


    public abstract int move (Point2D target);


    public abstract int move (Line line);


    public abstract int rotate (double dAngle);


    public abstract int setHeading (double heading);


    public abstract boolean isVisible ();

}
