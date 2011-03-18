package slogo.util.interfaces;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.IOException;


public interface IDraw2D
{

    Graphics2D draw (Graphics2D g);


    Graphics2D drawAtPoint (Graphics2D g, Point2D point);
        

}
