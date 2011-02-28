package slogo.model.arena.turtle.qualities.mode;

import java.util.Collection;
import slogo.util.line.Line;

public interface IMode
{
    Collection<Line> applyMode (Collection<Line> lines);
}
