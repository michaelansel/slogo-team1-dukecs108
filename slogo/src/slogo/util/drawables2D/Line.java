package slogo.util.drawables2D;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Collection;
import slogo.util.Position;
import slogo.util.drawtools.DrawTool;
import slogo.util.drawtools.DrawTool2D;
import slogo.util.drawtools.Pen2D;





/**
 * Stores the line drawn by a given function execution. Takes advantage
 * of the line2D methods.
 * @author Julian Genkins
 *
 */
public class Line extends Line2D.Double implements Comparable, IDraw2D, IWrap2D {

	private DrawTool2D myTool;
	
	 public Line (DrawTool pen, Position position, double distance)
	 {
	     this(pen, position.getLocation(), distance, position.getHeading());
	 }
	
	public Line(DrawTool pen, Point2D point, double distance, double angle){
        this(pen, point, new Point2D.Double(point.getX()+(distance*Math.cos(Math.toRadians(angle))),
                                              point.getY()+(distance*Math.sin(Math.toRadians(angle)))));
        
    }
	
	public Line(){
	    this(new Pen2D());
	}
	
	public Line (DrawTool pen)
    {
	    this(pen, new Point2D.Double());
    }
	
	public Line (Point2D point){
        this(new Pen2D(), point);
    }
	
	public Line(DrawTool pen, Point2D start){
	    this(pen, start, start);	    
	}
	
	 public Line (Point2D start, Point2D end){
	     this(new Pen2D(), start, end);
	 }
	 
	 public Line (double x1, double y1, double x2, double y2)
	 {
	     this(new Pen2D(), x1,y1,x2,y2);
	        
	 }
	 
	 public Line (DrawTool pen, double x1, double y1, double x2, double y2)
	 {
	     this(pen, new Point2D.Double(x1,y1), new Point2D.Double(x2,y2));
	 }
	 
	 public Line (DrawTool pen, Line line)
	 {
        this(pen, line.getP1(), line.getP2());
	 }
	 
	 public Line(DrawTool pen, Point2D start, Point2D end){
	    super(start, end);
	    myTool = (DrawTool2D) pen;
	  
	 }

    public void setTool (DrawTool2D tool)
    {
        this.myTool = tool;
    }
    
    public DrawTool2D getTool ()
    {
        return myTool;
    }

    @Override
    public Graphics2D draw(Graphics2D g2d, Dimension dimension){
        
        for (Shape line: this.wrap(dimension)){
                myTool.applyTool(g2d);
                g2d.draw(line);
            
        }   
        return g2d;
    }
    
   
    @Override
    public double length(){
        return this.getP1().distance(this.getP2());
    }
    
    @Override
    public double XDistance(){
        return Math.abs(this.getP1().getX()-this.getP2().getX());
    }
    
    @Override
    public double YDistance(){
        return Math.abs(this.getP1().getY()-this.getP2().getY());
    }
    
//    public void animate(long t) throws InterruptedException{
//        for (Line line: this.animatableSet()){
//            line.draw();
//            Thread.sleep(t);
//        }
//    }

//    public Collection<Line> animatableSet(){
//        
//        Collection<Line> lines = new ArrayList<Line>();
//        
//        for( double c = 1; c <= this.length() ; c++){
//            lines.add(new Line(myPen, myP1, c, this.getAngle()));
//        }
//        
//        return lines;
//    }
    
//    private double getAngle ()
//    {
//        if (this.YDistance() >= 0) return Math.acos(this.XDistance()/this.length());
//        else return 180 + Math.acos(this.XDistance()/this.length()); 
//    }

    @Override
    public IDraw2D flipXY ()
    {
        return this.flipX().flipY();
    }

    @Override
    public String toString ()
    {
        return String.format("Line[(%f,%f),(%f,%f))",
                             this.getX1(),
                             this.getY1(),
                             this.getX2(),
                             this.getY2());
    }
    
    @Override
    public int compareTo(Object otherLine) throws ClassCastException {
        if (!(otherLine instanceof Line))
          throw new ClassCastException("You are trying to compare two different objects.");
        if (!(this.getX1()==((Line) otherLine).getX1()))
        	return (int) (this.getX1()-((Line) otherLine).getX1());
        if (!(this.getX2()==((Line) otherLine).getX2()))
        	return (int) (this.getX2()-((Line) otherLine).getX2());
        if (!(this.getY1()==((Line2D) otherLine).getY1()))
        	return (int) (this.getY1()-((Line) otherLine).getY1());
        if (!(this.getY2()==((Line2D) otherLine).getY2()))
        	return (int) (this.getY2()-((Line) otherLine).getY2());
        
        return myTool.compareTo( ((Line) otherLine).getTool());
      }

    
    @Override
    public void shiftXY(double x, double y){
        System.out.println(y);
        System.out.println(this);
        this.setLine(this.getX1()+x, this.getY1()+y, this.getX2()+x, this.getY2()+y);
        
    }
    
    @Override
    public void shiftXY(double both){
        shiftXY(both, both);
    }
    
    @Override
    public void shiftX (double x)
    {
        this.shiftXY(x, 0);
    }
    
    @Override
    public void shiftY (double y)
    {
        this.shiftXY(0, y);
    }

    /* (non-Javadoc)
     * @see slogo.util.IDrawable2D#wrap(java.awt.Dimension)
     */
    @Override
    public ArrayList<Shape> wrap (Dimension limits)
    {
        Line left = new Line(new Point(), new Point(0, (int) limits.getHeight()));
        Line top = new Line(left.getP1(), new Point2D.Double((int) limits.getWidth(), 0));
        Line right = new Line(top.getP2(), new Point((int) limits.getWidth(), (int) limits.getHeight()));
        IWrap2D bottom = new Line(left.getP1(), right.getP2());
        
       ArrayList<Shape> lines = new ArrayList<Shape>();
        
        lines.add(this);
        
        
        return lines;
    }
    
    /* (non-Javadoc)
     * @see slogo.util.drawables2D.IWrap2D#wrapRight(java.awt.Dimension)
     */
    @Override
    public ArrayList<IWrap2D> wrapRight (Dimension limits){
        return null;
        
    }
    
    /* (non-Javadoc)
     * @see slogo.util.drawables2D.IWrap2D#wrapLeft(java.awt.Dimension)
     */
    @Override
    public ArrayList<IWrap2D> wrapLeft (Dimension limits){
        return null;
        
    }
    
    /* (non-Javadoc)
     * @see slogo.util.drawables2D.IWrap2D#wrapBottom(java.awt.Dimension)
     */
    @Override
    public ArrayList<IWrap2D> wrapBottom (Dimension limits){
        return null;
        
    }
    
    /* (non-Javadoc)
     * @see slogo.util.drawables2D.IWrap2D#wrapTop(java.awt.Dimension)
     */
    @Override
    public ArrayList<IWrap2D> wrapTop (Dimension limits){
        return null;
        
    }

    @Override
    public IDraw2D flipX ()
    {
        this.setLine(this.x1, -this.y1, this.x2, -this.y2);
        return this;
    }

    @Override
    public IDraw2D flipY ()
    {
        this.setLine(-this.x1, this.y1, -this.x2, this.y2);
        return this;    }
    
}
