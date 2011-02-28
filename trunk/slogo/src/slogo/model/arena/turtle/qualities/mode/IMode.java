package slogo.model.arena.turtle.qualities.mode;

import java.util.Collection;
import slogo.util.line.Line;



/**
 * An additional set of algorithms designed to not effect the trail of the turtle,
 *  but to add some fun variation to how his trail is printed
 * @author Julian Genkins
 *
 */
public interface IMode
{
    Collection<Line> applyMode (Collection<Line> lines);
}
