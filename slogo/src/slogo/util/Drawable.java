package slogo.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public interface Drawable
{

   

    Graphics2D draw (Graphics2D g,  Dimension dimension);

    
    
}
