package slogo.model.action;

import java.io.File;
import slogo.util.Position;
import slogo.util.drawables2D.Line;


public class Disguise implements Action
{

    private File myImageFile;


    public Disguise (File imageFile)
    {
        myImageFile = imageFile;
    }


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.disguise(myImageFile);
    }
}
