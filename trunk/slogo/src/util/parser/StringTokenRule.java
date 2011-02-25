/**
 * 
 */
package util.parser;

import java.util.regex.Pattern;


/**
 * @author Michael Ansel
 */
public class StringTokenRule implements ITokenRule
{

    private Pattern myPattern;
    private String myRegex;
    private String myRuleName;


    public StringTokenRule (char c)
    {
        this(c, null);
    }


    public StringTokenRule (char c, String ruleName)
    {
        this(String.valueOf(c), ruleName);
    }


    public StringTokenRule (String regex)
    {
        this(regex, null);
    }


    public StringTokenRule (String regex, String ruleName)
    {
        myRegex = regex;
        if (myRegex.length() == 1) myPattern =
            Pattern.compile(String.format("\\A(%s).*\\z",
                                          Pattern.quote(myRegex)));
        else myPattern =
            Pattern.compile(String.format("\\A(%s).*\\z", myRegex));
        myRuleName = ruleName;
    }


    @Override
    public String getName ()
    {
        return myRuleName != null ? myRuleName : "?";
    }


    public Pattern getPattern ()
    {
        return myPattern;
    }


    @Override
    public Token makeToken (Object value)
    {
        if (value instanceof String) return new Token(this, value);
        throw new IllegalArgumentException("Only accepts Strings as token values.");
    }


    @Override
    public String toString ()
    {
        return String.format("StringTokenRule<%s>(%s)",
                             (myRuleName != null) ? myRuleName : "?",
                             (myRegex.length() > 1) ? myRegex
                                                   : String.format("[%s]",
                                                                   myRegex));
    }

}
