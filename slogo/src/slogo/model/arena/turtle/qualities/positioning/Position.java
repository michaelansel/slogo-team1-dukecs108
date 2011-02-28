package slogo.model.arena.turtle.qualities.positioning;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * @author Julian Genkins
 * 
 */
public class Position implements IPosition {
	private Point2D myLocation;
	private double myHeading;

	// Constructors
	public Position() {
		this(new Point(), DEFAULT_ANGLE);
	}

	public Position(int x, int y) {
		this(new Point(x, y), DEFAULT_ANGLE);
	}

	public Position(Point p) {
		this(p, DEFAULT_ANGLE);
	}

	public Position(double angle) {
		this(new Point(), angle);
	}

	public Position(Point point, double angle) {
		myLocation = point;
		myHeading = angle;
	}

	// end Constructors

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.turtle.positioning.IPosition#setLocation(java.awt.Point)
	 */
	public void setLocation(Point2D loc) {
		myLocation = loc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.turtle.positioning.IPosition#getLocation()
	 */
	public Point2D getLocation() {
		return myLocation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.turtle.positioning.IPosition#getX()
	 */
	public double getX() {
		return myLocation.getX();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.turtle.positioning.IPosition#getY()
	 */
	public double getY() {
		return myLocation.getY();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.turtle.positioning.IPosition#getBearing()
	 */
	public double getAngle() {
		return myHeading;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.turtle.positioning.IPosition#setBearing(double)
	 */
	public void setAngle(double angle) {
		this.myHeading = angle;
	}

	@Override
	public void changeAngle(double dAngle) {
		setAngle(((myHeading + dAngle) % 360 + 360) % 360);

	}

	@Override
	public void changeAngle(Point target) {
		double dx = target.getX() - myLocation.getX();
		double dy = target.getY() - myLocation.getY();

		if (dx == 0 && dy > 0)
			setAngle(NORTH);
		else if (dx == 0 && dy < 0)
			setAngle(SOUTH);
		else if (dx > 0)
			setAngle(Math.atan(dy / dx));
		else if (dx > 0)
			setAngle(Math.atan(dy / dx) + 180.0);
	}
}