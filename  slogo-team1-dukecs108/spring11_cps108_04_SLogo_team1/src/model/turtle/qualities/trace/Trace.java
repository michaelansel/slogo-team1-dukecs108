package model.turtle.qualities.trace;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import view.Pixmap;
import model.line.Line;
import model.turtle.qualities.trace.pen.IPen;
import model.turtle.qualities.trace.pen.Pen;

public class Trace implements ITrace
{

    private Graphics2D myGraphics;
    private Pen myPen;
   
    public Trace(){
        this(LIGHT_STROKE, Color.BLACK);
    }
  
    public Trace (Stroke stroke, Color color)
    {
        myGraphics.setStroke(stroke);
        myGraphics.setColor(color);
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

    public Graphics2D getGraphics ()
    {
        
        return myGraphics;
    }

    
    
}
