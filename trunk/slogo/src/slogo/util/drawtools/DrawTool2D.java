package slogo.util.drawtools;

import java.awt.Graphics2D;

public abstract class DrawTool2D extends DrawTool 
{

    public Object applyTool(Object toApply){
        return applyGraphics((Graphics2D) toApply);
    }
    
    abstract Graphics2D applyGraphics (Graphics2D g2d);
    
}
