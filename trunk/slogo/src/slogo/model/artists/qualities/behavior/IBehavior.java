package slogo.model.artists.qualities.behavior;

import slogo.util.drawables2D.Line;


/**
 * Gives a scaffold for the various effects of a change in behavior to enable
 * different turtle personalities.
 * 
 * @author Julian
 */

public interface IBehavior extends Comparable<IBehavior> {

	Line applyBehavior(Line line);

    int getDepth ();

    int compareTo (IBehavior behavior);


}
