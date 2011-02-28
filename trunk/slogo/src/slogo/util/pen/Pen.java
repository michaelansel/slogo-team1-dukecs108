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
    public void putDown ()
    {
        myState = DOWN;
        
    }

    @Override
    public void putUp ()
    {
        myState = UP;
        
    }
   
}
