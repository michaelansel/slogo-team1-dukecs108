package slogo.model.arena.turtle.qualities.behavior;

import java.util.Random;
import slogo.util.drawables2D.Line;

/**
 * uses a random walk to generate a "drunken" path in place of the line
 * 
 * @author Julian Genkins
 * 
 */
public class DrunkenBehavior extends BehaviorDecorator {

	@Override
	public Line applyBehavior(Line line) {

		inebriate(line);

		return myDecoratedBehavior.applyBehavior(line);
	}

	private void inebriate(Line line) {
		Random randomGenerator = new Random();

	}

}
