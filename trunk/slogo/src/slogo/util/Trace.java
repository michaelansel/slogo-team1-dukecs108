package slogo.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import slogo.util.line.Line;
import slogo.util.pen.Pen;
import slogo.view.Pixmap;

public class Trace implements ITrace
{

    private Graphics2D myGraphics;
    private Pen myPen;
   
    public Trace(Graphics g){
        this(g, LIGHT_STROKE, Color.BLACK);
    }
  
    public Trace (Graphics g, Stroke stroke, Color color)
    {
        myGraphics = (Graphics2D) g;
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
