package slogo.model.arena.turtle.qualities.mode;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import slogo.util.drawables2D.Line;
import slogo.util.interfaces.ICartesian;

/**
 * Mirrors turtle path around origin
 * 
 * @author Julian Genkins
 * 
 */
public class MirrorMode extends DrawModeDecorator {

	



    @Override
    public List<ICartesian> applyMode (List<ICartesian> list)
    {
        List<ICartesian> modified = new ArrayList<ICartesian>();
        for (ICartesian shape: list){
            modified.add(shape);
            modified.add(shape.flipXY());
        }
        return modified;
    }

}