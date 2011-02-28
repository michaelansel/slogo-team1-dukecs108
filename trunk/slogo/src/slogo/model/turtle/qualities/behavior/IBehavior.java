package slogo.model.turtle.qualities.behavior;

import java.util.Collection;
import slogo.deprecated.TurtleMorph;
import slogo.model.turtle.qualities.positioning.IPosition;
import slogo.util.Trace.Trace;
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
