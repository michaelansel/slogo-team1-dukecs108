package slogo.model.action;

import slogo.util.Position;
import slogo.util.drawables2D.Line;


public class Draw implements Action
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
