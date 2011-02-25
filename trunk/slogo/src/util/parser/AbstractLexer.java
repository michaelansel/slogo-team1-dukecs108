/**
 * 
 */
package util.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * @author Michael Ansel
 */
public abstract class AbstractLexer
{
    public static ITokenRule[] getRulesArray (Class<?> klass)
    {
        ITokenRule[] rulesArray;
        String ruleBundleName = klass.getName().split("[$]")[0];
        try
        {
            ArrayList<ITokenRule> rules = new ArrayList<ITokenRule>();
            for (Field f : klass.getFields())
            {
                if (f.getType() == ITokenRule.class)
                {
                    ITokenRule rule = loadRule(ruleBundleName, f.getName());
                    f.set(null, rule);
                    rules.add(rule);
                }
            }
            rulesArray = new ITokenRule[rules.size()];
            rules.toArray(rulesArray);
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rulesArray;
    }


    protected static StringTokenRule loadRule (String bundleName,
                                               String fieldName)
    {
        String pattern =
            ResourceBundle.getBundle(bundleName).getString(fieldName);
        if (pattern.length() == 1) return new StringTokenRule(pattern.charAt(0),
                                                              fieldName);
        return new StringTokenRule(pattern, fieldName);
    }

    private Object myInput;

    private Map<String, ITokenRule> myTokenMap;

    private Collection<ITokenRule> myTokenRules;


    public AbstractLexer (Object input)
    {
        myInput = input;
    }


    protected Object getInput ()
    {
        return myInput;
    }


    public ITokenRule getTokenRuleByName (String tokenName)
    {
        for (ITokenRule tokenRule : getTokenRules())
            if (tokenRule.getName().equals(tokenName)) return tokenRule;
        return null;
    }


    public Collection<ITokenRule> getTokenRules ()
    {
        return myTokenRules;
    }


    protected void setTokenRules (Collection<ITokenRule> tokenRules)
    {
        myTokenRules = tokenRules;
    }


    protected void setTokenRules (ITokenRule[] tokenRules)
    {
        setTokenRules(Arrays.asList(tokenRules));
    }


    /**
     * Translate the provided Object into a List of Tokens
     * 
     * @return list of generated Tokens
     */
    public abstract List<Token> tokenize ();
}
