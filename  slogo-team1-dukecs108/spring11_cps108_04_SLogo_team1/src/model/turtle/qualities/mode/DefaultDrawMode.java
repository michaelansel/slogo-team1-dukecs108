package model.turtle.qualities.mode;

import java.util.Collection;
import java.util.List;
import model.line.Line;

public class DefaultDrawMode extends DrawModeDecorator implements IMode
{

    @Override
    public Collection<Line> applyMode (Collection<Line> lines)
    {
        return lines;
    }

}
