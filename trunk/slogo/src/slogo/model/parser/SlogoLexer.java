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
        }
        // If all other rules fail to match, try this
        rules.add(new StringTokenRule("[a-zA-Z]+[?]?", "CommandName"));
    }


    /**
     * @param input
     */
    public SlogoLexer (String input)
    {
        super(input);
        setTokenRules(rules);
    }


    public static void addUserCommand (String commandName)
    {
        ArrayList<ITokenRule> newRules = new ArrayList<ITokenRule>();
        for (ITokenRule rule : rules)
        {
            if (rule.getName().equals(commandName)) return; // token rule already exists
            if (rule.getName().equals("CommandName"))
            {
                newRules.add(new StringTokenRule(commandName, commandName));
            }
            newRules.add(rule);
        }
        rules = newRules;
    }

}
