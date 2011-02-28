package slogo.util.line;

import java.awt.Graphics2D;
import java.awt.Point;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.util.ArrayList;
import java.util.Collection;
import slogo.model.arena.turtle.qualities.positioning.IPosition;
import slogo.util.trace.Trace;





/**
 * Stores the line drawn by a given function execution. Takes advantage
 * of the line2D methods.
 * @author Julian Genkins
 *
 */
public class Line extends Line2D{

	private Trace myTrace;
	private Point2D myP1;
	private Point2D myP2;
	
	 public Line (Trace trace, IPosition position, double distance)
	    {
	     this(trace, position.getLocation(), distance, position.getAngle());
	    }
	
	public Line(Trace trace, Point2D point, double distance, double angle){
        this(trace, point, new Point2D.Double((distance*Math.cos(angle)), (distance*Math.sin(angle))));
        
    }
	
	public Line(Trace trace, Point start){
	    this(trace, start, start);
	    
	}
	
	public Line(Trace trace, Point2D start, Point2D end){
	    this(trace, new Line2D.Double(start, end));
	  
	}
	

    public Line (Trace trace, Line2D line)
    {
        myTrace = trace;
        myP1 = line.getP1();
        myP2 = line.getP2();
    }

    public void setTrace (Trace myTrace)
    {
        this.myTrace = myTrace;
    }
    
    public Trace getTrace ()
    {
        return myTrace;
    }


    public Point2D getP1 ()
    {
        return myP1;
    }
    
    
    public Point2D getP2 ()
    {
        return myP2;
    }

    public double endDistance ()
    {
        return this.getP2().distance(this.getP1());
    }


    public void draw(Graphics2D g){
        g.setStroke(myTrace.getStroke());
        g.setColor(myTrace.getColor());
        g.draw(this);
    }
    
    public double length(){
        return myP1.distance(myP2);
    }
    
    public double XDistance(){
        return Math.abs(myP2.getX()-myP1.getX());
    }
    
    public double YDistance(){
        return Math.abs(myP2.getY()-myP1.getY());
    }
    
//    public void animate(long t) throws InterruptedException{
//        for (Line line: this.animatableSet()){
//            line.draw();
//            Thread.sleep(t);
//        }
//    }

    public Collection<Line> animatableSet(){
        
        Collection<Line> lines = new ArrayList<Line>();
        
        for( double c = 1; c <= this.length() ; c++){
            lines.add(new Line(myTrace, myP1, c, this.getAngle()));
        }
        
        return lines;
    }
    
    private double getAngle ()
    {
        if (this.YDistance() >= 0) return Math.acos(this.XDistance()/this.length());
        else return 180 + Math.acos(this.XDistance()/this.length()); 
    }

    public Line mirror ()
    {
        return new Line(myTrace, new Line2D.Double( -this.getX1(), 
                                 -this.getY1(), 
                                 -this.getX2(), 
                                 -this.getY2()));
    }

 

    @Override
    public double getX1 ()
    {
        
        return myP1.getX();
    }

    @Override
    public double getX2 ()
    {
        return myP2.getX();
    }

    @Override
    public double getY1 ()
    {
        return myP1.getY();
    }

    @Override
    public double getY2 ()
    {
        return myP2.getY();
    }

    @Override
    public void setLine (double x1, double y1, double x2, double y2)
    {
        myP1 = new Point2D.Double(x1, y1);     
        myP2 = new Point2D.Double(x2, y2);     
    }

    @Override
    public Rectangle2D getBounds2D ()
    {
        //TODO Huh?
        return null;
    }
    
}
