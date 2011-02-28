package model.turtle.qualities.positioning;

import java.awt.Point;

public class Position implements IPosition
{
    private Point myLocation;
    private double myAngle;

//Constructors
    public Position ()
    {
        this(new Point(), DEFAULT_ANGLE);
    }
    
    public Position (int x, int y)
    {
        this(new Point(x,y), DEFAULT_ANGLE);
    }
    
    public Position (Point p)
    {
        this(p, DEFAULT_ANGLE);
    }
    
    public Position (double angle)
    {
        this(new Point(), angle);
    }
    
    public Position (Point point, double angle)
    {
        myLocation = point;
        myAngle = angle;
    }
//end Constructors

    
    /* (non-Javadoc)
     * @see model.turtle.positioning.IPosition#setLocation(java.awt.Point)
     */
    public void setLocation(Point loc)
    {
        myLocation = loc;
    }

    /* (non-Javadoc)
     * @see model.turtle.positioning.IPosition#getLocation()
     */
    public Point getLocation()
    {
        return myLocation;
    }
    
    
    /* (non-Javadoc)
     * @see model.turtle.positioning.IPosition#getX()
     */
    public double getX ()
    {
        return myLocation.x;
    }
    
    /* (non-Javadoc)
     * @see model.turtle.positioning.IPosition#getY()
     */
    public double getY ()
    {
        return myLocation.y;
    }


    /* (non-Javadoc)
     * @see model.turtle.positioning.IPosition#getBearing()
     */
    public double getAngle ()
    {
        return myAngle;
    }

    
    /* (non-Javadoc)
     * @see model.turtle.positioning.IPosition#setBearing(double)
     */
    public void setAngle (double angle)
    {
        this.myAngle = angle;
    }

    @Override
    public void changeAngle (double dAngle)
    {
        setAngle(((myAngle + dAngle) % 360 + 360) % 360);
        
    }

    @Override
    public void changeAngle (Point target)
    {
        double dx = target.getX() - myLocation.getX();
        double dy = target.getY() - myLocation.getY();
        
        if(dx == 0 && dy > 0) setAngle(NORTH);
        else if (dx == 0 && dy < 0) setAngle(SOUTH);
        else if (dx > 0) setAngle(Math.atan(dy/dx));
        else if (dx > 0) setAngle(Math.atan(dy/dx)+180.0);
    }
}