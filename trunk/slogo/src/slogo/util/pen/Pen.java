package slogo.util.pen;

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
