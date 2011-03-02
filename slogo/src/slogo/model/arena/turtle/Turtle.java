package slogo.model.arena.turtle;

import java.awt.Point; 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import slogo.model.arena.turtle.qualities.behavior.BehaviorDecorator;
import slogo.model.arena.turtle.qualities.behavior.DefaultBehavior;
import slogo.model.arena.turtle.qualities.behavior.IBehavior;
import slogo.model.arena.turtle.qualities.mode.DefaultDrawMode;
import slogo.model.arena.turtle.qualities.mode.DrawModeDecorator;
import slogo.model.arena.turtle.qualities.mode.IMode;
import slogo.model.arena.turtle.qualities.positioning.Positionable;
import slogo.model.arena.turtle.qualities.positioning.Position;
import slogo.util.Line;
import slogo.util.Pen;

/**
 * The "turtle" which serves as the model for responding to the user's commands
 * Overloaded with a lot of useful informations
 * @author Julian Genkins
 *
 */
public class Turtle implements IArtist, IMorphable
{
    private static final File DEFAULT_IMAGE = new File("src/image/default.jpg"); //TODO: Write in default filepath
    private String myName;
    private IBehavior myBehavior;
    private IMode myMode;
    private File myImage;
    private Positionable myPosition;
    private Pen myPen;
    private List<Line> myLines;
    private boolean amVisible;


//Constructors
    public Turtle (String name)
    {
        this(name, new Position());
    }


    public Turtle (String name, File image)
    {

        this(name, new Position(), new Pen(), image);
    }


    public Turtle (String name, Positionable position)
    {
        this(name, position, new Pen());
    }


    public Turtle (String name, Positionable position, Pen trace)
    {
        this(name, position, trace, DEFAULT_IMAGE);
    }


    public Turtle (String name, Positionable position, Pen trace, File image)
    {
        this(name, position, trace, image, new DefaultBehavior(), new DefaultDrawMode());
    }


    public Turtle (String name,
                   Positionable position,
                   Pen trace,
                   File image,
                   IBehavior behavior, 
                   IMode mode)
    {
        rename(name);
        setPosition(position);
        setTrace(trace);
        setImage(image);
        myPen.putDown();
        myBehavior = behavior;
        myLines = new ArrayList<Line>();
        amVisible = true;
        myMode = mode;
    }


//end Constructors

//Artist

    public void rename (String name)
    {
        myName = name;

    }


    public String getName ()
    {
        return myName;
    }


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
    public Pen getTrace ()
    {
        return myPen;
    }


    @Override
    public void setTrace (Pen newTrace)
    {
        myPen = newTrace;
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


    public String getImagePath ()
    {
    	return myImage.getPath();
		
    }


    @Override
    public Positionable getPosition ()
    {
        return myPosition;
    }


//Morphable

    @Override
    public int move (double distance)
    {
        myLines.add(myBehavior.applyBehavior(new Line( myPen, myPosition, distance)));
        myPosition.setLocation(myLines.get(myLines.size()-1).getP2());
        myPen.putDown();
        return (int) Math.round(myLines.get(myLines.size()-1).length());
    }


    @Override
    public int moveTo (Point target)
    {
        myPen.putUp();
        myPosition.setHeadingTo(target);
        return move(target.distance(myPosition.getLocation()));

    }


    @Override
    public int rotate (double dAngle)
    {
        myPosition.changeHeadingBy(dAngle);

        return (int) Math.abs(dAngle);
    }


    public int setHeading (double heading)
    {

        return this.rotate(heading - myPosition.getHeading());
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
    public void setPosition (Positionable position)
    {
        myPosition = position;
    }


    public boolean isVisible ()
    {
        return amVisible;
    }


    public int resetTurtle ()
    {
        this.moveTo(new Point());
        this.myPosition.changeHeadingBy(Positionable.DEFAULT_HEADING);
        int d = (int) myLines.get(myLines.size() - 1).length();
        myLines.clear();
        return d;
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
    public Iterable<Line> linesToDraw (int start)
    {
        return myMode.applyMode(myLines.subList(start, myLines.size() - 1));
    }

    public Turtle clone(){
        
        
        
        return new Turtle(myName, myPosition, myPen, myImage, myBehavior, myMode);
        
    }
    
}
