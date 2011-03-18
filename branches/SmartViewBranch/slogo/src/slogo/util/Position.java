package slogo.util;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * @author Julian Genkins
 * 
 */
public class Position {

    public final static double NORTH = 90.0;
    public final static double SOUTH = 270.0;
    public final static double EAST = 0.0;
    public final static double WEST = 180.0;
    public final static double DEFAULT_HEADING = NORTH;
	
    private Point2D myLocation;
	private double myHeading;

	// Constructors
	public Position() {
		this(new Point2D.Double(), DEFAULT_HEADING);
	}

	public Position(double x, double y) {
		this(new Point2D.Double(x, y), DEFAULT_HEADING);
	}

	public Position(Point2D p) {
		this(p, DEFAULT_HEADING);
	}

	public Position(double angle) {
		this(new Point(), angle);
	}

	public Position(Point2D point, double angle) {
		myLocation = point;
		myHeading = angle;
	}

	// end Constructors

	
	public void setLocation(Point2D loc) {
		myLocation = (Point2D) loc.clone();
	}

	
	public Point2D getLocation() {
		return myLocation;
	}

	public double getX() {
		return myLocation.getX();
	}
	
	
	public double getY() {
		return myLocation.getY();
	}


	public void setHeadingTo(double angle) {
		this.myHeading = angle;
	}

	public void changeHeadingBy(double dAngle) {
	    setHeadingTo(((myHeading + dAngle % 360) + 360) % 360);

	}

	
	public double getAngleToward(Point2D target) {
		double dx = target.getX() - myLocation.getX();
		double dy = target.getY() - myLocation.getY();

		if (dx == 0 && dy > 0)
			return myHeading - NORTH;
		else if (dx == 0 && dy < 0)
			return myHeading - SOUTH;
		else if (dx > 0)
		    return myHeading - Math.toDegrees(Math.atan(dy / dx));
		else if (dx < 0)
		    return myHeading - Math.toDegrees(Math.atan(dy / dx)) + 180.0;
		else return 0.0;
	}

    
    public double getHeading ()
    {
        return myHeading;
    }

}