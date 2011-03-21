package slogo.model.artists.qualities;

import slogo.model.ModelException;

public class Heading extends ArtistQuality
{
    
    double myHeading;

    public Heading (double head)
    {
        myHeading = head;
    }

    public Heading ()
    {
        super();
    }

    @Override
    public int compareTo (ArtistQuality o) throws ModelException
    {
        return Double.compare(myHeading, ((Heading) o).getHeading());
    }

    public double getHeading ()
    {
        return myHeading;
    }


    @Override
    public Object setTo (ArtistQuality aq) throws ModelException
    {
        myHeading = ((Heading) aq).getHeading();
        return null;
    }

    @Override
    boolean equals (ArtistQuality aq) throws ModelException
    {
        return myHeading == ((Heading) aq).getHeading();
    }

    
    
  



    
}
