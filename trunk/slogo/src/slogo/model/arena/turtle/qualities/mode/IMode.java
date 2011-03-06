package slogo.model.arena.turtle.qualities.mode;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.List;
import slogo.util.Line;

/**
 * An additional set of algorithms designed to not effect the trail of the
 * turtle, but to add some fun variation to how his trail is printed
 * 
 * @author Julian Genkins
 * 
 */
public interface IMode {

    List<Line> applyMode (List<Line> subList);
}