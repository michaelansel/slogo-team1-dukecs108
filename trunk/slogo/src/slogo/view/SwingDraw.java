package slogo.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import slogo.model.action.Action;
import slogo.model.action.DrawRoutines;
import slogo.model.arena.turtle.Turtle;
import slogo.util.Position;
import slogo.util.drawables2D.Line;


public class SwingDraw implements DrawRoutines
{
    public class TurtleGhost
    {
        public Position position = new Position();
        public File image = Turtle.DEFAULT_IMAGE;
        public boolean visible = true;
    }

    private Graphics2D myGraphics;
    private Map<Integer, TurtleGhost> myGhosts;


    public SwingDraw (BufferedImage image)
    {
        this(image.createGraphics());
    }


    public SwingDraw (Graphics2D g)
    {
        myGraphics = g;
        myGhosts = new HashMap<Integer, TurtleGhost>();
    }


    @Override
    public void drawLine (Action action, Line line)
    {
        line.draw(myGraphics);
        getGhost(action.getTurtleID()).position.setLocation(line.getP2());
    }


    private TurtleGhost getGhost (int turtleID)
    {
        if (!myGhosts.containsKey(turtleID)) myGhosts.put(turtleID,
                                                          new TurtleGhost());
        return myGhosts.get(turtleID);
    }


    @Override
    public void walk (Action action, Point2D from, Point2D to)
    {
        getGhost(action.getTurtleID()).position.setLocation(to);
    }


    @Override
    public void rotate (Action action, int degrees)
    {
        getGhost(action.getTurtleID()).position.setHeadingTo(degrees);
    }


    @Override
    public void disguise (Action action, File imageFile)
    {
        getGhost(action.getTurtleID()).image = imageFile;
    }


    @Override
    public void show (Action action)
    {
        getGhost(action.getTurtleID()).visible = true;
    }


    @Override
    public void hide (Action action)
    {
        getGhost(action.getTurtleID()).visible = false;
    }
}
