package slogo.model.arena.turtle.qualities.mode;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.List;
import slogo.util.drawables2D.Line;
import slogo.util.interfaces.ICartesian;

/**
 * @author Julian Genkins
 * 
 */
public class DefaultDrawMode implements IMode {


    @Override
    public List<ICartesian> applyMode (List<ICartesian> subList)
    {
        return subList;
    }

    


}
