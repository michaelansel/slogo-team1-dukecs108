package slogo.model.artists.qualities.behavior;

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

    @Override
    public int getDepth ()
    {
        return 1;
    }

    @Override
    public int compareTo (IBehavior behavior)
    {
        return this.getDepth()-behavior.getDepth();
    }

}
