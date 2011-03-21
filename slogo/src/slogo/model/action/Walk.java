package slogo.model.action;

import java.awt.geom.Point2D;
import slogo.util.Position;
import slogo.util.drawables2D.Line;


public class Walk implements Action
{
    private Point2D myTo;
    private Point2D myFrom;


    public Walk (Point2D from, Point2D to)
    {
        myFrom = from;
        myTo = to;
    }


    public Walk (Line line)
    {
        this(line.getP1(), line.getP2());
    }


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.walk(myFrom, myTo);
    }
}
