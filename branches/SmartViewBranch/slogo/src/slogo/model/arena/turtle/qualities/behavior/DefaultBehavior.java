package slogo.model.arena.turtle.qualities.behavior;

import slogo.util.drawables2D.Line;

/**
 * applies the default (no) behavior to line
 * 
 * @author Julian Genkins
 * 
 */
public class DefaultBehavior implements IBehavior {

	@Override
	public Line applyBehavior(Line line) {
		return line;
	}

}
