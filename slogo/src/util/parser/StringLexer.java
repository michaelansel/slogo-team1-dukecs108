/**
 * 
 */
package util.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;


/**
 * @author Michael Ansel
 */
public class StringLexer extends AbstractLexer
{

    public StringLexer (String input)
    {
        super(input);
    }


    @Override
    protected String getInput ()
    {
        return (String) super.getInput();
    }


    public List<Token> tokenize ()
    {
        if (getTokenRules() == null) throw new RuntimeException("TokenRules never initialized!");
        StringBuilder remainder = new StringBuilder(getInput());
        List<Token> results = new ArrayList<Token>();
        while (remainder.length() > 0)
        {
            List<Token> tokens = new ArrayList<Token>();
            for (ITokenRule rule : getTokenRules())
            {
                Matcher matcher =
                    ((StringTokenRule) rule).getPattern()
                                            .matcher(remainder.toString());
                //                System.out.println("Testing \""+remainder.toString()+"\" against pattern: "+matcher.pattern().pattern());
                if (!matcher.matches()) continue;
                tokens.add(rule.makeToken(matcher.group(1)));
            }

            if (tokens.size() == 0) throw new RuntimeException("No matching tokens found!\nRemainder: " +
                                                               remainder.toString());

            Token bestMatch = tokens.get(0);
            for (Token match : tokens)
                if (((String) match.value).length() > ((String) bestMatch.value).length()) bestMatch =
                    match;

            results.add(bestMatch);
            remainder.delete(0, ((String) bestMatch.value).length());
        }
        return results;
    }
}
