package model.turtle.qualities.trace.pen;


public interface IPen
{

    public static final boolean UP = false;
    public static final boolean DOWN = true; 
    
    public abstract void putUp ();


    public abstract boolean isUp ();


    public abstract void putDown ();


    public abstract boolean isDown ();


    public abstract boolean getPenState ();

}
