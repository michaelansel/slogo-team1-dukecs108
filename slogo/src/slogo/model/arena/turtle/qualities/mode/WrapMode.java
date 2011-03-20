package slogo.model.arena.turtle.qualities.mode;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import slogo.util.drawables2D.Line;
import slogo.util.interfaces.IDraw2D;
import slogo.util.interfaces.*;
import src.slogo.view.subpanels.ArenaDraw;

public class WrapMode extends DrawModeDecorator
{

    @Override
    public List<IDraw2D> applyMode (List<IDraw2D> list)
    {
        List<IDraw2D> wrapped = new ArrayList<IDraw2D>();
        
        System.out.println(list);
        
        for (IDraw2D drawable: list){
            for(IWrap wrap: ((IWrap) drawable).wrap(ArenaDraw.myDimension)){
                wrapped.add((IDraw2D) wrap);
            }
        }
        
        return wrapped;
    }

    

    
}
