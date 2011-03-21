package slogo.view;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import slogo.model.action.DrawRoutines;
import slogo.util.drawables2D.Line;


public class SwingDraw implements DrawRoutines
{
    private BufferedImage myImage;


    public SwingDraw (BufferedImage image)
    {
        myImage = image;
    }


    @Override
    public void drawLine (Line line)
    {
        line.draw(myImage.createGraphics());
    }


    @Override
    public void walk (Point2D from, Point2D to)
    {
        // TODO SwingDraw.walk
    }


    @Override
    public void rotate (int degrees)
    {
        // TODO SwingDraw.rotate
    }


    @Override
    public void disguise (File imageFile)
    {
        // TODO SwingDraw.disguise
    }


    @Override
    public void show ()
    {
        // TODO SwingDraw.show
    }


    @Override
    public void hide ()
    {
        // TODO SwingDraw.hide
    }
}
