package slogo.model.artists.qualities;

import slogo.model.ModelException;
import slogo.model.artists.Artist;
import slogo.model.artists.qualities.defaults.DefaultQualityLibrary;


public abstract class ArtistQuality implements Cloneable, Comparable<ArtistQuality>
{
    
    public ArtistQuality setToDefault(){

        return (ArtistQuality) this.setTo(DefaultQualityLibrary.getDefaultbyClass(this.getClass()));
    }
    
    public abstract Object setTo (ArtistQuality aq) throws ModelException;
   

    public  boolean isSameQualityType(ArtistQuality other){
        
        return isSameQualityType(this.getClass());
        
    }
    
    public boolean isSameQualityType (Class<? extends ArtistQuality> type)
    {
        
        return this.getClass().equals(type);
    }
    
    protected ArtistQuality(Class<? extends ArtistQuality> klass)
    {
        
    }

    public ArtistQuality ()
    {
        this.setToDefault();
    }

    @Override
    public int compareTo (ArtistQuality o)
    {
        if (this.getClass().equals(o))
            return 0;
        return 1;
    }

    public ArtistQuality (ArtistQuality quality)
    {
        this.setTo(quality);
    }
    

    abstract boolean equals(ArtistQuality aq) throws ModelException;
}