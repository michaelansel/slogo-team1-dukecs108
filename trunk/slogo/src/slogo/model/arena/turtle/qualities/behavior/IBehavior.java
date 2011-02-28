package slogo.model.arena.turtle.qualities.behavior;

import java.util.Collection;
import slogo.deprecated.TurtleMorph;
import slogo.model.arena.turtle.qualities.positioning.IPosition;
import slogo.util.line.Line;


/**
 * Gives a scaffold for the various effects of a change in behavior to enable
 * different turtle personalities.
 * 
 * @author Julian
 */

public interface IBehavior
{


    Line applyBehavior (Line line);

}
