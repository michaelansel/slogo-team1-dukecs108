package slogo.model.arena.turtle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point; 
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import slogo.model.arena.TurtleException;
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
    private static final File DEFAULT_IMAGE = new File("src/images/turtlecloud.png"); //TODO: Write in default filepath
    private static final int ICON_HEIGHT = 20;
    private static final int ICON_WIDTH = 20;
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

    public Turtle (String name, Position position, File file)
    {
        this(name, position, new Pen(), file);
    }
    
    
    public Turtle (String name, Positionable position)
    {
        this(name, position, new Pen());
    }


    public Turtle (String name, Positionable position, Pen pen)
    {
        this(name, position, pen, DEFAULT_IMAGE);
    }


    public Turtle (String name, Positionable position, Pen pen, File image)
    {
        this(name, position, pen, image, new DefaultBehavior(), new DefaultDrawMode());
    }


    public Turtle (String name,
                   Positionable position,
                   Pen pen,
                   File image,
                   IBehavior behavior, 
                   IMode mode)
    {
        rename(name);
        setPosition(position);
        setPen(pen);
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
    public Pen getPen ()
    {
        return myPen;
    }

    @Override
    public void setPen (Pen newPen)
    {
        myPen = newPen;
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
        return move (new Line( myPen.clone(), myPosition, distance));
        
    }

    public int move (Point2D target)
    {
        myPen.putUp();
//        myPosition.setHeadingTo(target);
        return move (new Line(myPosition.getLocation(), target));

    }
    
    public int move (Line line){
        myLines.add(myBehavior.applyBehavior(line));
        myPosition.setLocation(myLines.get(myLines.size()-1).getP2());
        myPen.putDown();
        return (int) Math.round(myLines.get(myLines.size()-1).length());
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
        this.move(new Point());
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


    public List<Line> getLinesToDraw (int start) //throws TurtleException
    {
//        if (myLines.size()<= start){
//            throw new TurtleException("Nothing to draw");
//        }
            return myMode.applyMode(myLines.subList(start, myLines.size()));
    }

    public Turtle clone(){
        
        
        
        return new Turtle(myName, myPosition, myPen, myImage, myBehavior, myMode);
        
    }


//    public BufferedImage drawMyLines (Graphics2D g2d)
//    {
//        return this.drawMyLines(g2d, 0);
//        
//    }
    
//    public BufferedImage drawMyLines (Graphics2D g2d, int startIndex)
//    {
//        for (Line line: this.getLines().subList(startIndex, this.getLines().size()-1)){
//            ArrayList<Line> wrappedLines = line.wrap();
//            for (Line wrap: wrappedLines){
//                myMode.applyMode(wrap, g2d);
//            }
//            line.draw(g2d);
//        }
//        return lines;
//    }
    
    public void draw (Graphics2D g) throws IOException{
        drawAtPoint(g, myPosition.getLocation());
    }

    public void drawAtPoint (Graphics2D g, Point2D point) throws IOException{
//      AffineTransform xform = AffineTransform.getRotateInstance(Math.toRadians(myPosition.getHeading()));
//      
//      
//      g.transform(xform);
      
      g.drawImage(ImageIO.read(myImage),
                  (int) point.getX()-ICON_HEIGHT/2,
                  (int) point.getY()-ICON_WIDTH/2, 
                  ICON_HEIGHT, 
                  ICON_WIDTH, 
                  null);

    }
    
}
