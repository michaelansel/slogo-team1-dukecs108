package slogo.model.action;

public class Hide extends BasicAction
{

    public Hide ()
    {}


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.hide();
    }
}
