package slogo.util.drawables2D;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import slogo.util.Position;
import slogo.util.drawtools.DrawTool;
import slogo.util.drawtools.DrawTool2D;
import slogo.util.drawtools.Pen2D;
import slogo.util.interfaces.ICartesian2D;
import slogo.util.interfaces.IDraw2D;
import slogo.util.interfaces.IWrap;
import slogo.util.interfaces.IWrap2D;





/**
 * Stores the line drawn by a given function execution. Takes advantage
 * of the line2D methods.
 * @author Julian Genkins
 *
 */
public class Line extends Line2D.Double implements Comparable, ICartesian2D, IDraw2D, IWrap2D {

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
    public Graphics2D draw(Graphics2D g2d){
        
       
            
        return drawAtPoint(g2d, this.getP1());
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
    public ICartesian2D flipXY ()
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

    
    @Override
    public ICartesian2D flipX ()
    {
        this.setLine(this.x1, -this.y1, this.x2, -this.y2);
        return this;
    }

    @Override
    public ICartesian2D flipY ()
    {
        this.setLine(-this.x1, this.y1, -this.x2, this.y2);
        return this;    }

    @Override
    public Graphics2D drawAtPoint (Graphics2D g2d, Point2D point)
    {
        this.shiftToPoint(point);
        myTool.applyTool(g2d);
        g2d.draw(this);
        return g2d;
    }

    @Override
    public void shiftToPoint (Point2D point)
    {
        this.shiftXY(point.getX()-this.getX1(), point.getY()-this.getY1());
        
    }

    
    
    
    @Override
    public Collection<IWrap> wrap (Dimension bounds)
    {
        Collection<IWrap> wrapped = new ArrayList<IWrap>();
        for (IWrap2D wrap: this.wrap2D(bounds)){
            wrapped.add(wrap);
        }
        return wrapped;
    }
    
    @Override
    public Collection<IWrap2D> wrap2D(Dimension bounds){
        Line left = new Line(new Point(), new Point(0, (int) bounds.getHeight()));
        Line top = new Line(left.getP1(), new Point2D.Double((int) bounds.getWidth(), 0));
        Line right = new Line(top.getP2(), new Point((int) bounds.getWidth(), (int) bounds.getHeight()));
        Line bottom = new Line(left.getP1(), right.getP2());
        
        ArrayList<IWrap2D> lines = new ArrayList<IWrap2D>();
        
        System.out.println(left);
        System.out.println(this);
        System.out.println(this.intersectsLine(left));
        if (this.intersectsLine(left)){
            lines.addAll(this.wrapLeft(left, bounds));
        }
        else if (this.intersectsLine(top)){
            
        }
        else if (this.intersectsLine(right)){
            
        }
        else if (this.intersectsLine(bottom)){
    
        }
        else
            lines.add(this);
        
        return lines;
    }
    
    
    @Override
    public Collection<IWrap2D> wrapLeft (Line limit,
                                          Dimension bounds)
    {
        Collection<ICartesian2D> split = this.split(this.findIntersect( limit));
        Collection<IWrap2D> wrapped = new ArrayList<IWrap2D>();
        for(ICartesian2D line: split){
            if (line.isOutOfBounds(bounds)){
                line.shiftX(bounds.getWidth());
                wrapped.addAll(((IWrap2D) line).wrap2D(bounds));
            }
            else{
                wrapped.add((IWrap2D) line);
            }
        }
        
        return wrapped;
    }

    @Override
    public boolean isOutOfBounds (Dimension bounds)
    {
        
        return (this.getX1() < 0 || this.getX1() > bounds.getWidth()) ||
               (this.getY1() < 0 || this.getY1() > bounds.getHeight()) ||
               (this.getX2() < 0 || this.getX2() > bounds.getWidth()) ||
               (this.getY2() < 0 || this.getY2() > bounds.getHeight());
    }

    @Override
    public Collection<ICartesian2D> split (Point2D splitPoint)
    {
        Collection<ICartesian2D> split = new ArrayList<ICartesian2D>();
        split.add(new Line(this.getP1(), splitPoint));
        split.add(new Line(splitPoint, this.getP2()));
        return split;
    }

    @Override
    public Point2D findIntersect (Line limit)
    {
        return findIntersect(limit.x1, limit.y1, limit.x2, limit.y2);
    }
    
    @Override
    public Point2D findIntersect (Point2D start, Point2D end){
        return findIntersect(start.getX(), start.getY(), end.getX(), end.getY());
    }
    
    @Override
    public Point2D findIntersect (double x1, double y1, double x2, double y2){
        
        double denom = ((this.x1 - this.x2) * (y2 - y1)) - ((this.y2 - this.y1) * (x2 - x1));

        //  AB & CD are parallel 
        if (denom == 0)
                return null;

        double numer = ((this.y1 - y1) * (x2 - x1)) - ((this.x1 - x1) * (y2 - y1));

        double r = numer / denom;

        double numer2 = ((this.y1 - y1) * (this.x2 - this.x1)) - ((this.x1 - x1) * (this.y2 - this.y1));

        double s = numer2 / denom;

        if ((r < 0 || r > 1) || (s < 0 || s > 1))
                    return null;

        // Find intersection point
        Point2D result = new Point2D.Double(this.x1 + (r * (this.x2 - this.x1)), this.y1 + (r * (this.y2 - this.y1)));
            

        return result;
       
    }
    

    @Override
    public Collection<IWrap2D> wrapRight (Line right, Dimension bounds)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<IWrap2D> wrapBottom (Line bottom, Dimension bounds)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<IWrap2D> wrapTop (Line top, Dimension bounds)
    {
        // TODO Auto-generated method stub
        return null;
    }

   
    
}
