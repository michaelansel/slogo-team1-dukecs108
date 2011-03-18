package slogo.model.arena.turtle.qualities.behavior;

import slogo.util.drawables2D.Line;

/**
 * Gives a scaffold for the various effects of a change in behavior to enable
 * different turtle personalities.
 * 
 * @author Julian
 */

public interface IBehavior {

	Line applyBehavior(Line line);

}
