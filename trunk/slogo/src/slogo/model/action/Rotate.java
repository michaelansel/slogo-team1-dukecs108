package slogo.model.action;

public class Rotate implements Action
{
    private int myDegrees;


    public Rotate (int degrees)
    {
        myDegrees = degrees;
    }


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.rotate(myDegrees);
    }
}
