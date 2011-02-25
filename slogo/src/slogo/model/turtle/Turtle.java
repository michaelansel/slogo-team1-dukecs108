package slogo.model.turtle;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import slogo.util.Line;
import slogo.util.Pen;


/**
 * @author Julian Genkins
 */
public abstract class Turtle implements IMorphable, IArtist
{
    private int myHeading;

    private List<Line> myLines;
    private Pen myPen;

    private Point2D myPosition;

    private TurtleState myState;
//    private TurtleImage myImage;

    private boolean penDownState;


    public Turtle ()
    {
        this(0, 0, 0);
    }


    public Turtle (int x, int y, int heading)
    {
        this(new Point(x, y), heading);
    }


    public Turtle (Point position, int heading)
    {
        myPosition = position;
        myHeading = heading;
        myLines = new ArrayList<Line>();
        myPen = new Pen();
    }


    public boolean isPenDown ()
    {
        return penDownState;
    }


    public int move (int distance)
    {
        Line line = new Line(myPosition, myHeading, distance);
        myPosition = line.getP2();
        if (isPenDown()) addLine(line);
        return (int) distance;
    }


    public int moveTo (int x, int y)
    {
        return moveTo(new Point(x, y));
    }


    public int moveTo (Point destination)
    {
        double distance = myPosition.distance(destination);
        myPosition = destination;
        return (int) distance;
    }


    public int rotate (int degrees)
    {
        myHeading += degrees;
        return degrees;
    }


    public int rotateTo (int angle)
    {
        int dAngle = (myHeading + angle + 360) % 360;
        myHeading = angle;
        return dAngle;
    }
}
