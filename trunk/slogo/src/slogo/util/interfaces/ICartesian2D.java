package slogo.util.interfaces;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import slogo.util.drawables2D.Line;

public interface ICartesian2D extends Shape
{

   


    double length();
    
    double XDistance();
    
    double YDistance();
    
    double findDX();
    
    double findDY();
    
    ICartesian2D flipXY();
    
    ICartesian2D flipX();
    
    ICartesian2D flipY();

    
    void shiftXY (double both);

    void shiftXY (double x, double y);

    void shiftX (double x);

    void shiftY (double y);
    
    void shiftToPoint(Point2D point);

    boolean isOutOfBounds (Dimension bounds);
    
    Collection<ICartesian2D> split (Point2D splitPoint);
    
   
    
    Point2D findIntersect (Line limit);
    
    
    Point2D findIntersect (Point2D start, Point2D end);
    
    
    Point2D findIntersect (double x1, double y1, double x2, double y2);
    
    Collection<Point2D> splitToPoints();
    
}
