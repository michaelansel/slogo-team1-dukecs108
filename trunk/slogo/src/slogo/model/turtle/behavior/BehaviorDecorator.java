package slogo.model.turtle.behavior;

import slogo.deprecated.Morph;


/**
 * Turtle states allow for different non-default personalities in turtles. Uses
 * the decorator design pattern.
 * 
 * @author Julian Genkins
 */
public abstract class BehaviorDecorator implements IBehavior
{

    public IBehavior myDecoratedBehavior;


    public BehaviorDecorator ()
    {
        myDecoratedBehavior = new DefaultBehavior();
    }


    public BehaviorDecorator (IBehavior behavior)
    {
        myDecoratedBehavior = behavior;
    }


    public Morph evaluate (Morph morph)
    {
        return myDecoratedBehavior.evaluate(morph);
    }
}
