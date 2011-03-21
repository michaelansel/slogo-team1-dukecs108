package slogo.model.artists.qualities;

import java.awt.Point;
import java.awt.geom.Point2D;
import slogo.model.ModelException;

public class Position extends ArtistQuality 
{

    Coordinates myPosition;
    Heading myHeading;
    public Position (Coordinates position, Heading heading)
    {
        myPosition = position;
        myHeading = heading;
    }

    public Position (Coordinates position)
    {
        myPosition = position;
        myHeading.setToDefault();
    }
    
    public Position (Heading heading)
    {
        myPosition.setToDefault();
        myHeading = heading;
    }

    public Position ()
    {
        myPosition.setToDefault();
        myHeading.setToDefault();
    }



    public Position (Point point, int i)
    {
        this(new Coordinates(point), new Heading(i));
    }

    @Override
    public int compareTo (ArtistQuality o)
    {
        
        return myPosition.compareTo(((Position)o).getPosition());
    }


    public Coordinates getPosition ()
    {
        return myPosition;
    }
    
    public Heading getHeading ()
    {
        return myHeading;
    }




    @Override
    public Object setTo (ArtistQuality aq) throws ModelException
    {
        
        myHeading.setTo(((Position) aq).getHeading());
        return myPosition.setTo(((Position) aq).getPosition());
    }


    @Override
    boolean equals (ArtistQuality aq) throws ModelException
    {
        // TODO Auto-generated method stub
        return false;
    }

    public Point2D getLocation ()
    {
        return myPosition.getLocation();
    }

    public void changeHeadingBy (double dAngle)
    {

        this.getHeading().setTo(new Heading(this.getHeading().getHeading()+dAngle));
        
    }



}
