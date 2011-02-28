package slogo.model.arena.turtle.qualities.mode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import slogo.util.line.Line;

/**
 * Mirrors turtle path around origin
 * 
 * @author Julian Genkins
 * 
 */
public class MirrorMode extends DrawModeDecorator {

	@Override
	public List<Line> applyMode(Collection<Line> lines) {
		List<Line> mirrored = new ArrayList<Line>();
		for (Line line : lines) {
			mirrored.add(line);
			mirrored.add(line.mirror());
		}

		return (List<Line>) myDecoratedMode.applyMode(mirrored);
	}

}