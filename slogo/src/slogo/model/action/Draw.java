package slogo.model.action;

import slogo.util.drawables2D.Line;


public class Draw extends BasicAction
{

    private Line myLine;


    public Draw (Line line)
    {
        myLine = line;
    }


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.drawLine(myLine);
    }
}
