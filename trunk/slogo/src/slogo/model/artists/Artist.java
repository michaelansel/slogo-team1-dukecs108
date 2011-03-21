package slogo.model.artists;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.TreeSet;
import slogo.model.ModelException;
import slogo.model.artists.qualities.Heading;
import slogo.model.artists.qualities.ArtistQuality;
import slogo.model.artists.qualities.Coordinates;

public abstract class Artist extends Observable implements Comparable<Artist>, Cloneable
{
    protected TreeSet<ArtistQuality> myQualities;
    boolean amActive;
    
    public Artist(){
        myQualities = new TreeSet<ArtistQuality>();
        activate();
    }
    
     public void activate ()
    {
         amActive = true;
    }
     
     public void deactivate ()
     {
          amActive = false;
     }
     
     public boolean  isActive(){
         return amActive;
     }

    public TreeSet<ArtistQuality> getQualities ()
    {
        return myQualities;
    }

    public void setMyQualities (TreeSet<ArtistQuality> qualities)
    {
        this.myQualities = qualities;
    }

    public ArtistQuality getQualityByType(Class<? extends ArtistQuality> type) throws ModelException{
        for (ArtistQuality mq: myQualities){
            if(mq.isSameQualityType(type)){
                return mq;
            }
        }
        throw new ModelException("Command invalid for this artist");
    }
    
    
//    abstract Object applyArtisticLiscense (Object execute);
    
    public abstract Shape draw(Object input);

    public boolean carriesQuality(Class<? extends ArtistQuality> type){
        for (ArtistQuality mq: myQualities){
            if(mq.isSameQualityType(type)){
                return true;
            }
        }
        return false;
    }
    
    public boolean carriesExactQuality (ArtistQuality qual)
    {
        for (ArtistQuality me: myQualities){
            if (me.equals(qual))
                return true;
        }
        return false;
    }

    public boolean carriesQualities (Class<? extends ArtistQuality> ... classes )
    {
        for (Class<? extends ArtistQuality> c : classes){
            if (!this.carriesQuality(c))
                return false;
        }
        return true;
    }
    
    @Override
    public int compareTo (Artist o)
    {
        ArrayList<Class<? extends ArtistQuality>> quals = new ArrayList<Class<? extends ArtistQuality>>();
        for (ArtistQuality aq: myQualities){
            quals.add(aq.getClass());
        }
        
        return 0;
    }
}
