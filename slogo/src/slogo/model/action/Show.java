package slogo.model.action;

import java.io.File;
import slogo.util.Position;
import slogo.util.drawables2D.Line;


public class Show implements Action
{



    public Show ()
    {
    }


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.show();
    }
}
