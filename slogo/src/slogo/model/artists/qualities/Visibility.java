package slogo.model.artists.qualities;

import slogo.model.ModelException;


public class Visibility extends ArtistQuality 
{

    public static final boolean VISIBLE = true;
    public static final boolean HIDDEN = false;
    boolean amVisible;
    
    public Visibility (Boolean visibility)
    {
        super(Visibility.class);
        amVisible = visibility;
    }


    public Visibility ()
    {
        super();
    }


//    @Override
//    public int compareTo (ArtistQuality o)
//    {
//        if (this.equals(o))
//            return 0;
//        return 1;
//    }


    @Override
    public Object setTo (ArtistQuality aq) throws ModelException
    {
        amVisible = ((Visibility)aq).getVisibility();
        return null;
    }

    private boolean getVisibility ()
    {
        return this.isVisible();
    }

    public boolean isVisible ()
    {
        return amVisible;
    }

    @Override
    boolean equals (ArtistQuality aq) throws ModelException
    {
        return (amVisible && ((Visibility)aq).getVisibility()) || 
                (!amVisible && !((Visibility)aq).getVisibility());
    }



}
