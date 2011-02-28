package model.turtle;
/**
 * Represents an exceptional situation specific to this project.
 * 
 * @author Julian Genkins
 */

@SuppressWarnings("serial")
public class TurtleException extends RuntimeException
{
    // BUGBUG: should be extendible, i.e., get message text from file 
    public static TurtleException NOT_CLONABLE =
        new TurtleException("line not clonable");

    
    /**
     * Create exception with given meesage
     *  
     * @param message explaination of problem
     */
    public TurtleException (String message)
    {
        super(message);
    }
}