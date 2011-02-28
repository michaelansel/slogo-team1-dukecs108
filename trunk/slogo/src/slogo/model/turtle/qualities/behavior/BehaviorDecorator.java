package slogo.model.turtle.qualities.behavior;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import slogo.util.line.Line;



/**
 * Turtle states allow for different non-default personalities in turtles. 
 * Uses the decorator design pattern.
 * @author Julian
 *
 */
public abstract class BehaviorDecorator implements IBehavior
{
	
	public IBehavior myDecoratedBehavior;

	 public BehaviorDecorator()
	 {
		 myDecoratedBehavior = new DefaultBehavior();
	 }
 
 
	 public BehaviorDecorator(IBehavior behavior)
	 {
		 myDecoratedBehavior = behavior;
	 }



    public void setSubBehavior (IBehavior behavior)
    {
        myDecoratedBehavior = behavior;
        
    }


     
    
}



