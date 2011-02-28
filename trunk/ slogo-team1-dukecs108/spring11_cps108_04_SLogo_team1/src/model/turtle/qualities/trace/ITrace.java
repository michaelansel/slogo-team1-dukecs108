package model.turtle.qualities.trace;

import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Stroke;
import view.Pixmap;
import model.line.Line;
import model.turtle.deprecated.TraceColor;
import model.turtle.qualities.trace.pen.Pen;



public interface ITrace
{
    //TODO ADD MORE STROKES
    final public static Stroke LIGHT_STROKE = new BasicStroke(1);
    final public static Stroke LIGHT_DASHED_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 15.0f, new float[] {10.0f,10.0f}, 5.0f);
    final public static Stroke MEDIUM_STROKE = new BasicStroke(3);
    final public static Stroke HEAVY_STROKE = new BasicStroke(7);
    final public static Stroke VERYHEAVY_STROKE = new BasicStroke(11);
    

    public abstract void setStrokeSize (int strokeSize);


    public abstract int getThickness ();


    public abstract void setStrokeColor (TraceColor color);


    public abstract TraceColor getColor ();
    
    public void setPen (Pen myPen);

    public Pen getPen ();
    
    
   

}
