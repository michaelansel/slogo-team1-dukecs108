package slogo.model.artists.qualities.behavior;

/**
 * Turtle states allow for different non-default personalities in turtles. Uses
 * the decorator design pattern.
 * 
 * @author Julian
 * 
 */
public abstract class BehaviorDecorator implements IBehavior {

	public IBehavior myDecoratedBehavior;

	public BehaviorDecorator() {
		myDecoratedBehavior = new DefaultBehavior();
	}

	public BehaviorDecorator(IBehavior behavior) {
		myDecoratedBehavior = behavior;
	}

	public void setSubBehavior(IBehavior behavior) {
		myDecoratedBehavior = behavior;

	}

	@Override
    public int compareTo (IBehavior behavior)
    {
        return this.getDepth()- behavior.getDepth();
        
    }

	@Override
    public int getDepth ()
    {
        return 1 + this.myDecoratedBehavior.getDepth();
    }

}
