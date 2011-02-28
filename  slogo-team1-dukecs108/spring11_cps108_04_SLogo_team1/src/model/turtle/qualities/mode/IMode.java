package model.turtle.qualities.mode;

import java.util.Collection;
import java.util.List;
import model.line.Line;

public interface IMode
{
    Collection<Line> applyMode (Collection<Line> lines);
}
