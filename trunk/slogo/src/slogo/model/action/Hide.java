package slogo.model.action;

import java.io.File;
import slogo.util.Position;
import slogo.util.drawables2D.Line;


public class Hide implements Action
{



    public Hide ()
    {
    }


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.hide();
    }
}
