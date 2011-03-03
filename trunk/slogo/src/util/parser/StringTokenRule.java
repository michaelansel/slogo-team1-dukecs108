/**
 * 
 */
package util.parser;

import java.util.regex.Matcher;
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


    @Override
    public Token makeToken (Object value)
    {
        if (value instanceof CharSequence)
        {
            Matcher matcher = myPattern.matcher((CharSequence) value);
            if (!matcher.matches() || matcher.groupCount() < 1) return null; // value didn't match pattern
            return new Token(this, matcher.group(1));
        }
        return null; // Only matches Strings
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


    @Override
    public boolean matches (Object value)
    {
        if (value instanceof CharSequence) return myPattern.matcher((CharSequence) value)
                                                           .matches();
        return false; // Only matches Strings
    }

}
