/**
 * 
 */
package util.parser;

import java.util.List;


/**
 * @author Michael Ansel
 */
public class MatchFailedException extends ParserException
{
    private static final long serialVersionUID = 1L;


    public MatchFailedException (String message)
    {
        super(message);
    }


    public MatchFailedException (String message, List<Token> tokens)
    {
        super(message, tokens);
    }


    public MatchFailedException (String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
