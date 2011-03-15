package slogo.model.arena.turtle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point; 
import java.awt.Shape;
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
import slogo.model.arena.turtle.qualities.mode.WrapMode;
import slogo.util.Position;
import slogo.util.drawables2D.Line;

import slogo.util.drawtools.DrawTool;
import slogo.util.drawtools.Pen2D;
import slogo.util.interfaces.ICartesian2D;
import slogo.util.interfaces.IDraw2D;
import slogo.util.interfaces.IMorph;
import slogo.view.subpanels.ArenaDraw;

/**
 * The "turtle" which serves as the model for responding to the user's commands
 * Overloaded with a lot of useful informations
 * @author Julian Genkins
 *
 */
public class Turtle implements IMorph, IDraw2D, ITurtle 
{
    private static final File DEFAULT_IMAGE = new File("src/images/turtlecloud.png"); 
    private static final int ICON_HEIGHT = 20;
    private static final int ICON_WIDTH = 20;
    private String myName;
    private IBehavior myBehavior;
    private IMode myMode;
    private File myImage;
    private Position myPosition;
    private DrawTool myPen;
    private List<IDraw2D> myDrawables;
    private boolean amVisible;


//Constructors
    public Turtle (String name)
    {
        this(name, new Position(ArenaDraw.ORIGIN));
    }

    public Turtle (String name, Position position)
    {
        this(name, position, new Pen2D());
    }
    
    public Turtle (String name, File image)
    {
        this(name, new Position(), image);
    }

    public Turtle (String name, Position position, File file)
    {
        this(name, position, new Pen2D(), file);
    }

    public Turtle (String name, Position position, DrawTool pen)
    {
        this(name, position, pen, DEFAULT_IMAGE);
    }

    public Turtle (String name, Position position, DrawTool pen, File image)
    {
        this(name, position, pen, image, new DefaultBehavior(), new WrapMode());
    }

    public Turtle (String name,
                   Position position,
                   DrawTool pen,
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
        myDrawables = new ArrayList<IDraw2D>();
        amVisible = true;
        myMode = mode;
    }


//end Constructors

//Artist

    


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#rename(java.lang.String)
     */
    @Override
    public void rename (String name)
    {
        myName = name;

    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#getName()
     */
    @Override
    public String getName ()
    {
        return myName;
    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#addAllDrawables(java.util.List)
     */
    @Override
    public void addAllDrawables (List<IDraw2D> drawables)
    {
        myDrawables.addAll(drawables);

    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#addDrawable(slogo.util.interfaces.IDraw2D)
     */
    @Override
    public void addDrawable (IDraw2D drawable)
    {
        myDrawables.add(drawable);
    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#getDrawables()
     */
    @Override
    public List<IDraw2D> getDrawables ()
    {
        return myDrawables;
    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#clearLines()
     */
    @Override
    public void clearLines ()
    {
        myDrawables.clear();
    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#revomeDrawable(java.lang.Integer)
     */
    @Override
    public void revomeDrawable (Integer index)
    {
        myDrawables.remove(index);
    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#removeDrawables(java.util.List)
     */
    @Override
    public void removeDrawables (List<IDraw2D> drawables)
    {
        myDrawables.removeAll(drawables);
    }

    public DrawTool getPen ()
    {
        return myPen;
    }

    public void setPen (DrawTool newPen)
    {
        myPen = newPen;
    }


    public IBehavior getBehavior()
    {
        return myBehavior;
    }

    public File getImage ()
    {
        return myImage;
    }


    public String getImagePath ()
    {
    	return myImage.getPath();
		
    }


    public Position getPosition ()
    {
        return myPosition;
    }


//Morphable

    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.IMorph#move(double)
     */
    @Override
    public int move (double distance)
    {
        return move (new Line(myPen, 
                     myPosition.getLocation(), 
                     new Point2D.Double(myPosition.getX()+(distance*Math.cos(Math.toRadians(myPosition.getHeading()))),
                                                    myPosition.getY()-
                                                            (distance*
                                                                    Math.sin(
                                                                             Math.toRadians(
                                                                                            myPosition.getHeading()))))));
    }

    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.IMorph#move(java.awt.geom.Point2D)
     */
    @Override
    public int move (Point2D target){
        return move (new Line(myPen.clone(), myPosition.getLocation(), target));
    }
    
    
    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.IMorph#move(slogo.util.drawables2D.Line)
     */
    @Override
    public int move (Line line){
        myDrawables.add(myBehavior.applyBehavior(line));
        myPosition.setLocation(((Line)myDrawables.get(myDrawables.size()-1)).getP2());
        return (int) Math.round(((ICartesian2D)myDrawables.get(myDrawables.size()-1)).length());
    }

    


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.IMorph#rotate(double)
     */
    @Override
    public int rotate (double dAngle)
    {
        myPosition.changeHeadingBy(dAngle);

        return (int) Math.abs(dAngle);
    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.IMorph#setHeading(double)
     */
    @Override
    public int setHeading (double heading)
    {

        return this.rotate(heading - myPosition.getHeading());
    }


    public void addBehavior (BehaviorDecorator behavior)
    {
        behavior.setSubBehavior(myBehavior);
        myBehavior = behavior;
    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#setImage(java.io.File)
     */
    @Override
    public void setImage (File image)
    {
        myImage = image;
    }


    public void setPosition (Position position)
    {
        myPosition = position;
    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.IMorph#isVisible()
     */
    @Override
    public boolean isVisible ()
    {
        return amVisible;
    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#resetTurtle(java.awt.geom.Point2D)
     */
    @Override
    public int resetTurtle (Point2D home)
    {
        int retval = (int) Math.round(myPosition.getLocation().distance(home));
        myPen.putUp();
        move(home);
        setHeading(Position.DEFAULT_HEADING);
        clearLines();
        return retval;
    }


    public IMode getMode ()
    {

        return myMode;
    }


    public void addMode (DrawModeDecorator mode)
    {
        mode.setSubMode(myMode);
        myMode = mode;

    }


    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.ITurtle#getLinesToDraw(int)
     */
    @Override
    public List<IDraw2D> getLinesToDraw (int start) //throws TurtleException
    {
//        if (myLines.size()<= start){
//            throw new TurtleException("Nothing to draw");
//        }
        
        
            return myMode.applyMode(myDrawables.subList(start, myDrawables.size()));
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
    
    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.IDraw2D#draw(java.awt.Graphics2D)
     */
    @Override
    public Graphics2D draw (Graphics2D g2d){
        return drawAtPoint(g2d, myPosition.getLocation());
    }

    /* (non-Javadoc)
     * @see slogo.model.arena.turtle.IDraw2D#drawAtPoint(java.awt.Graphics2D, java.awt.geom.Point2D)
     */
    @Override
    public Graphics2D drawAtPoint (Graphics2D g2d, Point2D point){
//      AffineTransform xform = AffineTransform.getRotateInstance(Math.toRadians(myPosition.getHeading()));
//      
//      
//      g.transform(xform);
      
      try
    {
        g2d.drawImage(ImageIO.read(myImage),
                      (int) point.getX()-ICON_HEIGHT/2,
                      (int) point.getY()-ICON_WIDTH/2, 
                      ICON_HEIGHT, 
                      ICON_WIDTH, 
                      null);
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
      return g2d;
    }
    
}
