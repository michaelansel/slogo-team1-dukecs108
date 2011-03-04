package slogo.model.arena.turtle;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import slogo.model.arena.turtle.qualities.behavior.BehaviorDecorator;
import slogo.model.arena.turtle.qualities.behavior.IBehavior;
import slogo.model.arena.turtle.qualities.mode.DrawModeDecorator;
import slogo.model.arena.turtle.qualities.mode.IMode;
import slogo.model.arena.turtle.qualities.positioning.Positionable;
import slogo.util.Pen;

/**
 * interface for methods that control a turtles behavior, Appearance/Image, and
 * Position, and update via a turtle morph.
 * 
 * @author Julian
 * 
 */
public interface IMorphable {

	/**
	 * adds new behavior as first element in link list of behaviors
	 * 
	 * @param myBehavior
	 */
	void addBehavior(BehaviorDecorator behavior);

	public abstract IBehavior getBehavior();

	/**
	 * adds new mode as first element in link list of modes
	 * 
	 * @param mode
	 */
	void addMode(DrawModeDecorator mode);

	public abstract IMode getMode();

	public abstract void setImage(File image);

	public abstract File getImage();

	public abstract void setPosition(Positionable position);

	public abstract Positionable getPosition();

	// public abstract void update(TurtleMorph morph);

	/**
	 * takes a point and moves the turtle to that point. Does not draw line
	 * between
	 * 
	 * @param target
	 */
	public int move(Point2D target);

	public int move(double distance);

	public int rotate(double dAngle);


}
