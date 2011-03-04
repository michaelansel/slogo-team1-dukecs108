package slogo.model.arena.turtle.qualities.mode;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.List;
import slogo.util.Line;

/**
 * @author Julian Genkins
 * 
 */
public class DefaultDrawMode implements IMode {


    @Override
    public List<Line> applyMode (List<Line> subList)
    {
        return subList;
    }


}
