package slogo.util.drawables2D;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface IDraw2D 
{

   

    Graphics2D draw (Graphics2D g,  Dimension dimension);

    double length();
    
    double XDistance();
    
    double YDistance();
    
    IDraw2D flipXY();
    
    IDraw2D flipX();
    
    IDraw2D flipY();

    
    void shiftXY (double both);

    void shiftXY (double x, double y);

    void shiftX (double x);

    void shiftY (double y);
    
}
