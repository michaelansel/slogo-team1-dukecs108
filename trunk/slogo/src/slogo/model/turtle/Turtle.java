package slogo.model.turtle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import slogo.deprecated.SolidTrace;
import slogo.deprecated.TurtleImage;
import slogo.model.turtle.qualities.behavior.BehaviorDecorator;
import slogo.model.turtle.qualities.behavior.DefaultBehavior;
import slogo.model.turtle.qualities.behavior.IBehavior;
import slogo.model.turtle.qualities.mode.DrawModeDecorator;
import slogo.model.turtle.qualities.mode.IMode;
import slogo.model.turtle.qualities.positioning.IPosition;
import slogo.model.turtle.qualities.positioning.Position;
import slogo.util.Trace.Trace;
import slogo.util.line.Line;
import slogo.util.pen.Pen;


public class Turtle implements IArtist, IMorphable
{
    private static final File DEFAULT_IMAGE = new File("");
    private IBehavior myBehavior;
    private IMode myMode;
    private File myImage;
    private IPosition myPosition;
    private Trace myTrace;
    private List<Line> myLines;
  

//Constructors
    public Turtle ()
    {
        this(new Position());
    }


    public Turtle (IPosition position)
    {
        this(position, new Trace());
    }

    public Turtle (IPosition position, Trace trace)
    {
        this(position, trace, DEFAULT_IMAGE);
    }

    public Turtle (IPosition position, Trace trace, File image)
    {
        this(position, trace, image, new DefaultBehavior());
    }


    public Turtle (IPosition position,
                   Trace trace,
                   File image,
                   IBehavior behavior)
    {
        setPosition(position);
        setTrace(trace);
        setImage(image);
        myTrace.setPen(new Pen());
        myBehavior = behavior;
        myLines = new ArrayList<Line>();
    }
//end Constructors


//Artist
    
    @Override
    public void addAllLines (List<Line> newLines)
    {
        myLines.addAll(newLines);
        
    }


    @Override
    public void addLine (Line newLine)
    {
        myLines.add(newLine);
    }


    @Override
    public List<Line> getLines ()
    {
        return myLines;
    }


    @Override
    public void clearLines ()
    {
        myLines.clear();
        
    }


    @Override
    public void revomeLine (Integer index)
    {
        myLines.remove(index);
        
    }


    @Override
    public void removeLines (List<Line> lines)
    {
        myLines.removeAll(lines);
        
    }


    @Override
    public Trace getTrace ()
    {
        return myTrace;
    }


    @Override
    public void setTrace (Trace newTrace)
    {
        myTrace = newTrace;
    }


    @Override
    public IBehavior getBehavior ()
    {
        return myBehavior;
    }


    @Override
    public File getImage ()
    {
        return myImage;
    }


    @Override
    public IPosition getPosition ()
    {
        return myPosition;
    }


 
    
//Morphable
    
    @Override
    public void move (double distance)
    {
        myLines.add(myBehavior.applyBehavior(new Line( myTrace, myPosition, distance)));
        myPosition.setLocation(myLines.get(myLines.size()-1).getP2());
    }


    @Override
    public void moveTo (Point target)
    {
        myTrace.getPen().putUp();
        myPosition.changeAngle(target);
        move(target.distance(myPosition.getLocation()));
        myTrace.getPen().putDown();
        
    }


    @Override
    public void rotate (double dAngle)
    {
        myPosition.changeAngle(dAngle);
        
    }


    @Override
    public void addBehavior (BehaviorDecorator behavior)
    {
        behavior.setSubBehavior(myBehavior);
        myBehavior = behavior;
    }


    @Override
    public void setImage (File image)
    {
        myImage = image;
    }


    @Override
    public void setPosition (IPosition position)
    {
        myPosition = position;
    }


    

    @Override
    public IMode getMode ()
    {
        
        return myMode;
    }


    @Override
    public void addMode (DrawModeDecorator mode)
    {
        mode.setSubMode(myMode);
        myMode = mode;
        
    }



    @Override
    public Iterable<Line> linesToDraw(int start)
    {
        return myMode.applyMode(myLines.subList(start, myLines.size()-1));
    }

}
