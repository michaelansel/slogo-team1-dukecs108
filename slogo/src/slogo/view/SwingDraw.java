package slogo.view;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JPanel;
import slogo.model.action.DrawRoutines;
import slogo.util.drawables2D.Line;


public class SwingDraw implements DrawRoutines
{
    private JPanel myPanel;


    public SwingDraw (JPanel panel, BufferedImage image)
    {
        myPanel = panel;
    }


    @Override
    public void drawLine (Line line)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void walk (Point2D from, Point2D to)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void rotate (int degrees)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void disguise (File imageFile)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void show ()
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void hide ()
    {
        // TODO Auto-generated method stub

    }
}
