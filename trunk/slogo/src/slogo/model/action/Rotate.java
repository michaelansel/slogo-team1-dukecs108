package slogo.model.action;

public class Rotate extends BasicAction
{
    private int myDegrees;


    public Rotate (int degrees)
    {
        myDegrees = degrees;
    }


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.rotate(this, myDegrees);
    }
}
