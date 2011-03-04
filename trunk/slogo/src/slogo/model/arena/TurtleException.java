package slogo.model.arena;

public class TurtleException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public TurtleException (String message)
    {
        super(message);
    }


    public TurtleException (String message, Throwable throwable)
    {
        super(message, throwable);
    }

}