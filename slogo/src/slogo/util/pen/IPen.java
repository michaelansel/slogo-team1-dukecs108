package slogo.util.pen;


/**
 * @author Julian Genkins
 *
 */
public interface IPen
{

    public static final boolean UP = false;
    public static final boolean DOWN = true; 
    
    public abstract boolean putUp ();


    public abstract boolean isUp ();


    public abstract boolean putDown ();


    public abstract boolean isDown ();


    public abstract boolean getPenState ();

}
