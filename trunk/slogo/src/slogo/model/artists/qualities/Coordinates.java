package slogo.model.artists.qualities;

import java.awt.geom.Point2D;
import slogo.model.ModelException;
import slogo.util.drawables2D.Line;

public class Coordinates extends ArtistQuality
{

    Point2D myLocation;
    
    public Coordinates(double x, double y){
        this(new Point2D.Double(x,y));
    }
    
    
    public Coordinates(Point2D point){
        myLocation = point;
    }
    
    
    
    public Coordinates ()
    {
        super();
    }


    @Override
    public int compareTo (ArtistQuality o)
    {
        if (Double.compare(myLocation.getX(), ((Coordinates) o).myLocation.getX()) == 0)
            return Double.compare(myLocation.getY(), ((Coordinates) o).myLocation.getY());
        return  Double.compare(myLocation.getX(), ((Coordinates) o).myLocation.getX());
    }

  




    @Override
    public Object setTo (ArtistQuality aq) throws ModelException
    {
        Line pl = new Line(myLocation, ((Coordinates) aq).getLocation());
        myLocation = ((Coordinates) aq).getLocation();
        return pl;
    }



    public Point2D  getLocation()
    {
        return myLocation;
    }


    @Override
    boolean equals (ArtistQuality aq) throws ModelException
    {
        
        
        return (Math.abs(Double.compare(myLocation.getX(),
                                        ((Coordinates) aq).myLocation.getX())) +
               Math.abs(Double.compare(myLocation.getY(),
                                       ((Coordinates) aq).myLocation.getY()))) 
                                       == 0;
    }


    public double getX ()
    {
        return myLocation.getX();
    }

    public double getY ()
    {
        return myLocation.getY();
    }


    public void setLocation (Point2D p2)
    {
        myLocation = p2;        
    }


}
