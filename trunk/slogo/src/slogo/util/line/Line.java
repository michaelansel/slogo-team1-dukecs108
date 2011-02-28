package slogo.util.line;

import java.awt.Point;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.util.List;
import slogo.model.turtle.qualities.positioning.IPosition;
import slogo.util.Trace.Trace;





/**
 * Stores the line drawn by a given function execution. Takes advantage
 * of the line2D methods.
 * @author Julian
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
        this(trace, point, new Point((int) (distance*Math.cos(angle)), (int)(distance*Math.sin(angle))));
        
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


    public List<Line2D> split ()
    {
        //TODO write this
        return null;
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


    public void draw(){
        myTrace.getGraphics().draw(this);
    }
    
    public double length(){
        return myP1.distance(myP2);
    }
    
    public void animate(int t){
        for( int c = 0; c < this.length() ; c++){
            
        }
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
