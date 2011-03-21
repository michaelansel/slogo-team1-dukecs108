package slogo.model.action;

import java.io.File;


public class Disguise extends BasicAction
{

    private File myImageFile;


    public Disguise (File imageFile)
    {
        myImageFile = imageFile;
    }


    @Override
    public void draw (DrawRoutines howToDraw)
    {
        howToDraw.disguise(this, myImageFile);
    }
}
