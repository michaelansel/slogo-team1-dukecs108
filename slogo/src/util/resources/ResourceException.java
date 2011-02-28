package util.resources;


/**
 * Represents an exceptional situation specific to this project.
 * 
 * @author Robert C. Duvall
 */
@SuppressWarnings("serial")
public class ResourceException extends RuntimeException
{
    /**
     * Create exception with given message
     *  
     * @param message explanation of problem
     */
    public ResourceException (String message, Object ... values)
    {
        super(String.format(message, values));
    }
}
