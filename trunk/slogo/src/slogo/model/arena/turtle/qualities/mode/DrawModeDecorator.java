package slogo.model.arena.turtle.qualities.mode;

/**
 * A mode to be applied to the way that a turtle is drawn
 * 
 * @author Julian Genkins
 * 
 */
public abstract class DrawModeDecorator implements IMode {
	public IMode myDecoratedMode;

	public DrawModeDecorator() {
		myDecoratedMode = new DefaultDrawMode();
	}

	public DrawModeDecorator(IMode mode) {
		myDecoratedMode = mode;
	}

	public void setSubMode(IMode mode) {
		myDecoratedMode = mode;

	}
}
