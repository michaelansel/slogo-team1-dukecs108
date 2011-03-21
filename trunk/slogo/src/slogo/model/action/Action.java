package slogo.model.action;

/**
 * @author Michael Ansel
 */
public interface Action
{
    public void draw (DrawRoutines howToDraw);


    public void setTurtleID (int turtleID);


    public int getTurtleID ();
}
