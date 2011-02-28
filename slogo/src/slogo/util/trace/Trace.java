package slogo.util.trace;

import java.awt.Color;
import java.awt.Stroke;
import slogo.util.pen.Pen;

public class Trace implements ITrace
{

    private Stroke myStroke;
    private Color myColor;
    private Pen myPen;
   
    public Trace(){
        this(LIGHT_STROKE, Color.BLACK);
    }
  
    public Trace (Stroke stroke, Color color)
    {
        setStroke(stroke);
        setColor(color);
        myPen = new Pen();
    }


    @Override
    public Pen getPen ()
    {
        return myPen;
    }

    @Override
    public void setPen (Pen pen)
    {
        myPen = pen;
    }

    public void setColor (Color myColor)
    {
        this.myColor = myColor;
    }

    public Color getColor ()
    {
        return myColor;
    }

    public void setStroke (Stroke myStroke)
    {
        this.myStroke = myStroke;
    }

    public Stroke getStroke ()
    {
        return myStroke;
    }

 

    
    
}