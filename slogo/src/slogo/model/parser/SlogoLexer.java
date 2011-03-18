/**
 * 
 */
package slogo.model.parser;

import java.util.ArrayList;
import java.util.ResourceBundle;
import util.parser.ITokenRule;
import util.parser.StringLexer;
import util.parser.StringTokenRule;


/**
 * @author Michael Ansel
 */
public class SlogoLexer extends StringLexer
{
    private static ArrayList<ITokenRule> rules;

    static
    {
        String bundleName = "slogo.model.parser.SlogoLexer";
        rules = new ArrayList<ITokenRule>();
        for (String ruleName : ResourceBundle.getBundle(bundleName).keySet())
        {
            StringTokenRule rule = loadRule(bundleName, ruleName);
            rules.add(rule);
            rules.add(rule);
        }
    }


    /**
     * @param input
     */
    public SlogoLexer (String input)
    {
        super(input);
        setTokenRules(rules);
    }

}
