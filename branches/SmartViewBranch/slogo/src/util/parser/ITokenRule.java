/**
 * 
 */
package util.parser;

/**
 * @author Michael Ansel
 */
public interface ITokenRule
{
    /**
     * Gets a human-readable name for this ITokenRule
     * 
     * @return name of TokenRule
     */
    String getName ();


    /**
     * Create a new Token with given value that matches this ITokenRule.
     * 
     * @param value New Token value
     * @return new Token
     */
    Token makeToken (Object value);


    /**
     * Test value against ITokenRule and return match status
     * 
     * @param value value to test against ITokenRule
     * @return true if value matches ITokenRule
     */
    boolean matches (Object value);
}
