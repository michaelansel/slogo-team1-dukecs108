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
    

    public ArtistQuality ()
    {
        this.setToDefault();
    }

    public ArtistQuality (ArtistQuality quality)
    {
        this.setTo(quality);
    }
    

    abstract boolean equals(ArtistQuality aq) throws ModelException;
}