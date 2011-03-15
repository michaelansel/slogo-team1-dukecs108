package slogo.model.arena.turtle.qualities.mode;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import slogo.util.drawables2D.IDraw2D;
import slogo.util.drawables2D.Line;

/**
 * Mirrors turtle path around origin
 * 
 * @author Julian Genkins
 * 
 */
public class MirrorMode extends DrawModeDecorator {

	



    @Override
    public List<IDraw2D> applyMode (List<IDraw2D> list)
    {
        List<IDraw2D> modified = new ArrayList<IDraw2D>();
        for (IDraw2D shape: list){
            modified.add(shape);
            modified.add(shape.flipXY());
        }
        return modified;
    }

}