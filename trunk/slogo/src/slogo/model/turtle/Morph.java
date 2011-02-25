package slogo.model.turtle;

import java.util.List;
import slogo.util.Line;


/**
 * Carries the changes associated with each movement Used to update the turtles
 * current position based on the mighty morphin' qualities.
 * 
 * @author Julian Genkins
 */
public interface Morph
{
    public List<Line> getLines ();


    public IMorphable getMorphable ();


    public PolarMorph toPolar ();


    public RectangularMorph toRectangular ();
}
