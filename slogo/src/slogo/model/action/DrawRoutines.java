package slogo.model.action;

import java.awt.geom.Point2D;
import java.io.File;
import slogo.util.Position;
import slogo.util.drawables2D.Line;


/**
 * @author Michael Ansel
 */
public interface DrawRoutines
{
    void drawLine (Line line);


    void walk (Point2D from, Point2D to);


    /**
     * Set heading to degrees
     * 
     * @param degrees new heading
     */
    void rotate (int degrees);


    void disguise (File imageFile);


    void show ();


    void hide ();
}
