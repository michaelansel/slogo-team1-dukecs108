package slogo.model.action;

public abstract class BasicAction implements Action
{
    private int myTurtleID;


    @Override
    public void setTurtleID (int turtleID)
    {
        myTurtleID = turtleID;
    }


    @Override
    public int getTurtleID ()
    {
        return myTurtleID;
    }
}
