package slogo.model.artists.qualities;

import slogo.model.ModelException;
import slogo.model.artists.qualities.behavior.BehaviorDecorator;
import slogo.model.artists.qualities.behavior.IBehavior;


public class Behavior extends ArtistQuality
{

    
    IBehavior myBehavior;

    public Behavior(IBehavior defaultBehavior){
        super(Behavior.class);
        myBehavior = defaultBehavior;
    }
    
    public Behavior ()
    {
        super();
    }

//
//    @Override
//    public int compareTo (ArtistQuality aq) throws ModelException
//    {
//        return this.getBehavior().compareTo(((Behavior) aq).getBehavior());
//        
//    }

    public IBehavior getBehavior ()
    {
        return myBehavior;
    }


    @Override
    public Object setTo (ArtistQuality aq) throws ModelException
    {
        myBehavior = ((Behavior) aq).getBehavior();
        return null;
        
    }

    @Override
    boolean equals (ArtistQuality aq)throws ModelException
    {
        return this.getBehavior().equals(((Behavior) aq).getBehavior());
    }
 
    
    
}

