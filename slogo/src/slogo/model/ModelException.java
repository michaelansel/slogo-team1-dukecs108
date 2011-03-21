package slogo.model;

public class ModelException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String DIFFERENT_QUALITIES = "Trying to combine two different qualities";
    public static final String DOES_NOT_HAVE_QUALITY = "Artist does not contain this quality";
    public static final String ARTIST_DNE = "Artist Does Not Exist";
    public static final String NO_DEFAULT_LOADED = "No default loaded for this quality";

    public ModelException (String message)
    {
        super(message);
    }


    public ModelException (String message, Throwable throwable)
    {
        super(message, throwable);
    }

}