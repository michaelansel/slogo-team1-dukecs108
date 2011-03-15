package slogo.model.arena.turtle.qualities.mode;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import slogo.util.drawables2D.Line;
import slogo.util.interfaces.ICartesian;
import slogo.util.interfaces.IWrap2D;

public class WrapMode extends DrawModeDecorator
{

    @Override
    public List<ICartesian> applyMode (List<ICartesian> list)
    {
        //Implement wrapping function
        return list;
    }

    /* (non-Javadoc)
     * @see slogo.util.IDrawable2D#wrap(java.awt.Dimension)
     */
    @Override
    public ArrayList<Shape> wrap (Dimension limits)
    {
        Line left = new Line(new Point(), new Point(0, (int) limits.getHeight()));
        Line top = new Line(left.getP1(), new Point2D.Double((int) limits.getWidth(), 0));
        Line right = new Line(top.getP2(), new Point((int) limits.getWidth(), (int) limits.getHeight()));
        IWrap2D bottom = new Line(left.getP1(), right.getP2());
        
       ArrayList<Shape> lines = new ArrayList<Shape>();
        
        lines.add(this);
        
        
        return lines;
    }
    
    /* (non-Javadoc)
     * @see slogo.util.drawables2D.IWrap2D#wrapRight(java.awt.Dimension)
     */
    @Override
    public ArrayList<IWrap2D> wrapRight (Dimension limits){
        return null;
        
    }
    
    /* (non-Javadoc)
     * @see slogo.util.drawables2D.IWrap2D#wrapLeft(java.awt.Dimension)
     */
    @Override
    public ArrayList<IWrap2D> wrapLeft (Dimension limits){
        return null;
        
    }
    
    /* (non-Javadoc)
     * @see slogo.util.drawables2D.IWrap2D#wrapBottom(java.awt.Dimension)
     */
    @Override
    public ArrayList<IWrap2D> wrapBottom (Dimension limits){
        return null;
        
    }
    
    /* (non-Javadoc)
     * @see slogo.util.drawables2D.IWrap2D#wrapTop(java.awt.Dimension)
     */
    @Override
    public ArrayList<IWrap2D> wrapTop (Dimension limits){
        return null;
        
    }

    
}
