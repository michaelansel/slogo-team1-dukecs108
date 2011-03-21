package slogo.model.action;

public class Show extends BasicAction
{

    public Show ()
    {}


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.show();
    }
}
