package model.line;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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
public class Line implements Iterable<Point> {

	private Trace myTrace;
	private List<Point> myPoints;
	
	 public Line (Trace trace, IPosition position, double distance)
	    {
	     this(trace, position.getLocation(), distance, position.getAngle());
	    }
	
	public Line(Trace trace, Point start, double distance, double angle){
        this(trace, start, new Point((int) (distance*Math.cos(angle)), (int)(distance*Math.sin(angle))));
        
    }
	
	public Line(Trace trace, Point start){
	    this(trace, start, start);
	    
	}
	
	public Line(Trace trace, Point start, Point end){
	    myTrace = trace;
	    myPoints = createPointList(start, end);
	  
	}
	
		
	
	
   

    public void setTrace (Trace myTrace)
    {
        this.myTrace = myTrace;
    }
    
    public Trace getTrace ()
    {
        return myTrace;
    }

      
    
    /**
     * iterates through all points in the line.
     */
    @Override
    public Iterator<Point> iterator ()
    {
        return myPoints.iterator();
    }


    public List<Point> createPointList (Point start, Point end)
    {
        
        return createPointList(start, end.getX()-start.getX(), end.getY()-start.getY());
    }

    public List<Point> createPointList (Point start, double dx, double dy)
    {
        
        //TODO: Make this work
        List<Point> points = new ArrayList<Point>();
        points.add(start);
        int xStart = (int) start.getX();
        int yStart = (int) start.getY();
        
        if ((dx+dy) == 0){
            //do nothing
        }
        else if( dx == 0){
            for (int i = 0; i < dy; i++)
                points.add(new Point(xStart, yStart+i));
        }
        else if(dy == 0){
            for (int i = 0; i < dx; i++)
                points.add(new Point(xStart+i, yStart));
        }
        else if (dx == dy)
        {
            for (int i = 0; i < dx; i++)
                points.add(new Point(xStart+i, yStart+i));
        }
        else if (dx > dy){
            
            for (int i = 0; i < dy; i++)
                points.addAll(createPointList(new Point((int) (xStart+(i*dx/dy)), yStart+i), (dx/dy)-1, 0));
            
        }
        else if (dy < dx){
            for (int i = 0; i < dy; i++)
                points.addAll(createPointList(new Point( xStart+i, (int) (yStart+(i*dy/dx))), (dy/dx)-1, 0));
        }
        
        return points;
    }

    public Point getStartPoint ()
    {
        return myPoints.get(0);
    }
    
    
    public Point getEndPoint ()
    {
        return myPoints.get(myPoints.size()-1);
    }

    public boolean intersects(Line l){
        return !this.pointsOfIntersection(l).isEmpty();
        
    }
    
    public boolean containsPoint(Point p){
        return myPoints.contains(p);
    }
    
    public Collection<Point> pointsOfIntersection(Line l){
        Collection<Point> points = new ArrayList<Point>();
        points.addAll(myPoints);
        points.retainAll(l.getPoints());
        return points;
    }
    
    
    
    
    public Collection<Point> getPoints ()
    {
        return myPoints;
    }

    public Line createMirror () throws CloneNotSupportedException
    {
     
        Line temp = (Line) this.clone();
        for (Point p: temp){
            p.setLocation(-p.getX(), -p.getY());
        }
        return temp;
    
        
    }

    public double endDistance ()
    {
        return this.getEndPoint().distance(this.getStartPoint());
    }



}
