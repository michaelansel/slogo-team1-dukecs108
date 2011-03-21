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
    void drawLine (Action action, Line line);


    void walk (Action action, Point2D from, Point2D to);


    /**
     * Set heading to degrees
     * 
     * @param degrees new heading
     */
    void rotate (Action action, int degrees);


    void disguise (Action action, File imageFile);


    void show (Action action);


    void hide (Action action);
}
