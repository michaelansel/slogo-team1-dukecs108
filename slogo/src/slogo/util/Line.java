package slogo.util;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import slogo.model.turtle.Morph;


/**
 * Stores the line drawn by a given function execution. Takes advantage of the
 * line2D methods.
 * 
 * @author Julian Genkins
 */
public class Line extends Line2D.Double
{
    private static final long serialVersionUID = 1L;

    private Pen myPen;


    public Line (Morph morph)
    {
        this(morph.getMorphable().getPosition(),
             morph.getMorphable().getHeading() + morph.toPolar().dAngle,
             morph.toPolar().dRadius);
    }


    public Line (Point position, double d, double dRadius)
    {
        this(position, d, dRadius, new Pen());
    }


    public Line (Point2D myPosition, double angle, double distance, Pen pen)
    {
        super(myPosition.getX(),
              myPosition.getY(),
              myPosition.getX() + Math.cos(angle) * distance,
              myPosition.getY() + Math.sin(angle) * distance);
        myPen = pen;
    }


    public Line (Point2D myPosition, int d, int dRadius)
    {
        this(myPosition, d, dRadius, new Pen());
    }
}
