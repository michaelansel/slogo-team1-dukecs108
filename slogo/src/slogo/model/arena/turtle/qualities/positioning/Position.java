package slogo.model.arena.turtle.qualities.positioning;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * @author Julian Genkins
 * 
 */
public class Position implements Positionable {
	private Point2D myLocation;
	private double myHeading;

	// Constructors
	public Position() {
		this(new Point(), DEFAULT_HEADING);
	}

	public Position(int x, int y) {
		this(new Point(x, y), DEFAULT_HEADING);
	}

	public Position(Point p) {
		this(p, DEFAULT_HEADING);
	}

	public Position(double angle) {
		this(new Point(), angle);
	}

	public Position(Point point, double angle) {
		myLocation = point;
		myHeading = angle;
	}

	// end Constructors

	
	public void setLocation(Point2D loc) {
		myLocation = loc;
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


	@Override
	public void setHeadingTo(double angle) {
		this.myHeading = angle;
	}

	@Override
	public void changeHeadingBy(double dAngle) {
		changeHeadingBy(((myHeading + dAngle) % 360 + 360) % 360);

	}

	@Override
	public void setHeadingTo(Point2D target) {
		double dx = target.getX() - myLocation.getX();
		double dy = target.getY() - myLocation.getY();

		if (dx == 0 && dy > 0)
			changeHeadingBy(NORTH);
		else if (dx == 0 && dy < 0)
			changeHeadingBy(SOUTH);
		else if (dx > 0)
			changeHeadingBy(Math.atan(dy / dx));
		else if (dx > 0)
			changeHeadingBy(Math.atan(dy / dx) + 180.0);
	}

    
    @Override
    public double getHeading ()
    {
        return myHeading;
    }

}