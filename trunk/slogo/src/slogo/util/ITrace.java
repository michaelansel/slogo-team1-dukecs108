package slogo.util;

import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Stroke;
import slogo.deprecated.TraceColor;
import slogo.util.line.Line;
import slogo.util.pen.Pen;
import slogo.view.Pixmap;



public interface ITrace
{
    //TODO ADD MORE STROKES
    final public static Stroke LIGHT_STROKE = new BasicStroke(1);
    final public static Stroke LIGHT_DASHED_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 15.0f, new float[] {10.0f,10.0f}, 5.0f);
    final public static Stroke MEDIUM_STROKE = new BasicStroke(3);
    final public static Stroke HEAVY_STROKE = new BasicStroke(7);
    final public static Stroke VERYHEAVY_STROKE = new BasicStroke(11);
    

    
    public void setPen (Pen myPen);

    public Pen getPen ();
    
    
   

}
