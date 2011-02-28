package model.line;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import model.turtle.TurtleException;
import model.turtle.qualities.positioning.IPosition;
import model.turtle.qualities.trace.Trace;




/**
 * Stores the line drawn by a given function execution. Takes advantage
 * of the line2D methods.
 * @author Julian
 *
 */
public class Line {

	private Trace myTrace;
	private Line2D myLine;
	
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
        myLine = line;
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



    public Point2D getStartPoint ()
    {
        return myLine.getP1();
    }
    
    
    public Point2D getEndPoint ()
    {
        return myLine.getP2();
    }

    public boolean intersects(Line l){
        return myLine.intersectsLine(l.getLine());
        
    }
    
    public boolean containsPoint(Point p){
        return myLine.contains(p);
    }
    
    public Line2D getLine ()
    {
        return myLine;
    }


    public double endDistance ()
    {
        return this.getEndPoint().distance(this.getStartPoint());
    }


    public void draw(){
        myTrace.getGraphics().draw(myLine);
    }

    public Line mirror ()
    {
        return new Line(myTrace, new Line2D.Double( -myLine.getX1(), 
                                 -myLine.getY1(), 
                                 -myLine.getX2(), 
                                 -myLine.getY2()));
    }

}
