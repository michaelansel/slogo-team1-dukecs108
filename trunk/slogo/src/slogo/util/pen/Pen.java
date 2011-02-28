package slogo.util.pen;

/**
 * Two states for pen, up and down. Controls whether or not a line will be drawn
 * @author Julian
 *
 */
public class Pen implements IPen
{

    private boolean myState;
    
    public Pen(){
        myState = DOWN;
    }
    
    
    @Override
    public boolean getPenState ()
    {
        return myState;
    }

    @Override
    public boolean isDown ()
    {
        return myState;
    }

    @Override
    public boolean isUp ()
    {
        return !myState;
    }

    @Override
    public boolean putDown ()
    {
        myState = DOWN;
        
        return true;
    }

    @Override
    public boolean putUp ()
    {
        myState = UP;
        return false;
        
    }
   
}
