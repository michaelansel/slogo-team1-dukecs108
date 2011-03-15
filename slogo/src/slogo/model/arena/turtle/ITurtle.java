package slogo.model.arena.turtle;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.List;
import slogo.util.interfaces.ICartesian2D;
import slogo.util.interfaces.IDraw2D;


public interface ITurtle
{

    public abstract void rename (String name);


    public abstract String getName ();


    public abstract void addAllDrawables (List<IDraw2D> drawables);


    public abstract void addDrawable (IDraw2D drawable);


    public abstract List<IDraw2D> getDrawables ();


    public abstract void clearLines ();


    public abstract void revomeDrawable (Integer index);


    public abstract void removeDrawables (List<IDraw2D> drawables);


    public abstract void setImage (File image);


    public abstract int resetTurtle (Point2D home);


    public abstract List<IDraw2D> getLinesToDraw (int start);

}
