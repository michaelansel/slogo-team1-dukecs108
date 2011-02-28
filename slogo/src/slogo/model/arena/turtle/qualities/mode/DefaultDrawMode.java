package slogo.model.arena.turtle.qualities.mode;

import java.util.Collection;
import java.util.List;
import slogo.util.line.Line;

public class DefaultDrawMode extends DrawModeDecorator implements IMode
{

    @Override
    public Collection<Line> applyMode (Collection<Line> lines)
    {
        return lines;
    }

}
