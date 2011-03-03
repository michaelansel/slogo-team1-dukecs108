package slogo.model.arena.turtle.qualities.mode;

import java.util.Collection;
import slogo.util.Line;

/**
 * @author Julian Genkins
 * 
 */
public class DefaultDrawMode implements IMode {

	@Override
	public Collection<Line> applyMode(Collection<Line> lines) {
		return lines;
	}

}
