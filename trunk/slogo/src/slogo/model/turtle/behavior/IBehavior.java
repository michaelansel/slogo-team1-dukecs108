package slogo.model.turtle.behavior;

import slogo.deprecated.Morph;


/**
 * Gives a scaffold for the various effects of a change in behavior to enable
 * different turtle personalities.
 * 
 * @author Julian Genkins
 */

public interface IBehavior
{

    public Morph evaluate (Morph morph);

}
