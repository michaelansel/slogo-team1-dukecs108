package slogo.model.artists.qualities;

import slogo.model.ModelException;


public class Name extends ArtistQuality 
{

    String myName;
    
    
    public Name(String name){
        super(Name.class);
        myName = name;
    }
    
    public Name ()
    {
        super();
    }

//    @Override
//    public int compareTo (ArtistQuality o)
//    {
//        return myName.compareTo(((Name) o).getName());
//    }


    public String getName ()
    {
        return myName;
    }



    @Override
    public Object setTo (ArtistQuality aq)
    {
        return this.setTo(((Name) aq).getName());
    }

    

    private Object setTo (String name)
    {
        myName = name;
        return null;
        
    }

    @Override
    boolean equals (ArtistQuality aq) throws ModelException
    {
        return myName.equals(((Name) aq).getName());
    }

}
