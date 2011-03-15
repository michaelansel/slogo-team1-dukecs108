package slogo.util.drawtools;

import java.awt.Color;

public abstract class DrawTool implements Comparable<DrawTool>
{
    private boolean amDown;
    Color myColor;
    
    public boolean isUp ()
    {
        return !amDown;
    }
    
   
    public boolean isDown ()
    {
        return amDown;
    }

    
    public boolean putDown ()
    {
        amDown = true;
        return amDown;
    }

  
    public boolean putUp ()
    {
        amDown = false;
        return amDown;
    }

    public void setState(boolean state){
        amDown = state;
    }
    
    public void setColor (Color myColor)
    {
        this.myColor = myColor;
    }

    public Color getColor ()
    {
        return myColor;
    }
    
    public abstract Object applyTool(Object toApply);
    
    public abstract DrawTool clone();
    
}
