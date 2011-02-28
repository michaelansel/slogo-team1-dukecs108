package model.turtle.qualities.behavior;

import java.util.Collection;
import model.line.Line;
import model.turtle.deprecated.TurtleMorph;
import model.turtle.qualities.positioning.IPosition;
import model.turtle.qualities.trace.Trace;


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
