/**
 * 
 */
package slogo.model.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import slogo.model.expression.Expression;
import util.parser.AbstractLexer;
import util.parser.ITokenRule;
import util.parser.SimpleTokenRule;
import util.parser.StringLexer;
import util.parser.StringTokenRule;
import util.parser.Token;


/**
 * @author Michael Ansel
 */
public class CommandLexer extends AbstractLexer
{
    private static ArrayList<ITokenRule> rules;
    private static ArrayList<ITokenRule> stringRules;

    static
    {
        String bundleName = "slogo.model.parser.SlogoCommandLexer";
        rules = new ArrayList<ITokenRule>();
        stringRules = new ArrayList<ITokenRule>();
        for (String ruleName : ResourceBundle.getBundle(bundleName).keySet())
        {
            StringTokenRule rule = loadRule(bundleName, ruleName);
            rules.add(rule);
            stringRules.add(rule);
        }
        rules.add(new SimpleTokenRule("Expression"));
    }


    public CommandLexer (List<Object> objects)
    {
        super(objects);
        setTokenRules(rules);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getInput ()
    {
        return (List<Object>) super.getInput();
    }


    /**
     * @see util.parser.AbstractLexer#tokenize()
     */
    @Override
    public List<Token> tokenize ()
    {
        List<Token> tokens = new ArrayList<Token>();
        for (Object o : getInput())
            if (o instanceof Token) tokens.add(translateToken((Token) o));
            else if (o instanceof Expression) tokens.add(getTokenRuleByName("Expression").makeToken(o));
            else throw new RuntimeException("Unrecognizable input: " +
                                            o.toString());
        return tokens;
    }


    private Token translateToken (final Token token)
    {
        StringLexer lexer = new StringLexer(token.value.toString())
        {
            {
                setTokenRules(CommandLexer.stringRules);
            }
        };
        return lexer.tokenize().get(0);
    }

}
