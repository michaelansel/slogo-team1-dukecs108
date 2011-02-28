package slogo.model.turtle;

import java.awt.Point;
import java.io.File;
import slogo.model.turtle.qualities.behavior.BehaviorDecorator;
import slogo.model.turtle.qualities.behavior.IBehavior;
import slogo.model.turtle.qualities.mode.DrawModeDecorator;
import slogo.model.turtle.qualities.mode.IMode;
import slogo.model.turtle.qualities.positioning.IPosition;

/**
 * interface for methods that control a turtles behavior, Appearance/Image, and Position, 
 * and update via a turtle morph.
 * @author Julian
 *
 */
public interface IMorphable
{

    /**
     * adds new behavior as first element in link list of behaviors
     * @param myBehavior
     */
    void addBehavior (BehaviorDecorator behavior);

    public abstract IBehavior getBehavior ();

    
    /**
     * adds new mode as first element in link list of modes
     * @param mode
     */
    void addMode (DrawModeDecorator mode);

    public abstract IMode getMode ();
    

    public abstract void setImage (File image);


    public abstract File getImage ();


    public abstract void setPosition (IPosition position);


    public abstract IPosition getPosition ();


//    public abstract void update(TurtleMorph morph);
    
    /**
     * takes a point and moves the turtle to that point. Does not draw line between
     * @param target
     */
    public void moveTo(Point target);
    
    public void move (double distance);
    
    public void rotate (double dAngle);


   
        
}