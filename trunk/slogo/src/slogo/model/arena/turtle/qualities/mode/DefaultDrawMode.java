package slogo.model.arena.turtle.qualities.mode;

import java.util.Collection;
import slogo.util.line.Line;


/**
 * @author Julian Genkins
 *
 */
public class DefaultDrawMode extends DrawModeDecorator implements IMode
{

    @Override
    public Collection<Line> applyMode (Collection<Line> lines)
    {
        return lines;
    }

}
