package slogo.model.arena.turtle;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.List;
import slogo.util.interfaces.ICartesian2D;
import slogo.util.interfaces.IDraw2D;


public interface ITurtle
{

    void rename (String name);


    String getName ();


    void addAllDrawables (List<IDraw2D> drawables);


    void addDrawable (IDraw2D drawable);


    List<IDraw2D> getAllDrawables ();


    void clearDrawables ();


    void revomeDrawable (Integer index);


    void removeDrawables (List<IDraw2D> drawables);


    void setImage (File image);


    int resetTurtle (Point2D home);


    List<IDraw2D> getDrawablesToDraw ();
    
    boolean hideTurtle();
    
    boolean showTurtle();

    boolean setVisibility (boolean b);

}
