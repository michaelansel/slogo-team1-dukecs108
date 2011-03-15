package slogo.util.interfaces;

import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collection;
import slogo.util.drawables2D.Line;

public interface IWrap2D extends IWrap
{

    Collection<IWrap2D> wrap2D(Dimension bounds);
    
    Collection<IWrap2D> wrapRight (Line right, Dimension bounds);
    
    Collection<IWrap2D> wrapLeft (Line left, Dimension bounds);
        
    
    Collection<IWrap2D> wrapBottom (Line bottom, Dimension bounds);
    
    Collection<IWrap2D> wrapTop (Line top, Dimension bounds);
    
}
