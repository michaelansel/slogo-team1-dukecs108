/**
 * 
 */
package util.parser;

/**
 * @author Michael Ansel
 */
public class SimpleTokenRule implements ITokenRule
{

    private String myRuleName;


    public SimpleTokenRule ()
    {
        myRuleName = "SimpleTokenRule";
    }


    public SimpleTokenRule (String ruleName)
    {
        myRuleName = ruleName;
    }


    /**
     * @see util.parser.ITokenRule#getName()
     */
    @Override
    public String getName ()
    {
        return myRuleName;
    }


    /**
     * @see util.parser.ITokenRule#makeToken(java.lang.Object)
     */
    @Override
    public Token makeToken (Object value)
    {
        return new Token(this, value);
    }

}
