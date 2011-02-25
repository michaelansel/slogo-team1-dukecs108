/**
 * 
 */
package util.parser.grammar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;


/**
 * @author mra13
 */
public class AnonymousRule extends AbstractParserRule
{
    private List<String> myParameters;
    private Map<String, AbstractParserRule> myRules;
    private GrammarParseTreeNode myTemplate;


    public AnonymousRule (List<String> parameters, GrammarParseTreeNode template)
    {
        myParameters = parameters;
        myTemplate = template;
    }


    @Override
    public void initializeRule ()
    {

    }


    public void setRules (Map<String, AbstractParserRule> rules)
    {
        myRules = rules;
    }


    public AbstractParserRule toParserRule (AbstractParser parser,
                                            List<GrammarParseTreeNode> parameters)
    {
        if (myParameters.size() != parameters.size()) throw new RuntimeException("Parameter mismatch! Expected: " +
                                                                                 myParameters.size() +
                                                                                 " Got: " +
                                                                                 parameters.size());
        Map<String, AbstractParserRule> rules =
            new HashMap<String, AbstractParserRule>(myRules);
        for (int i = 0; i < myParameters.size(); i++)
        {
            rules.put(myParameters.get(i),
                      parameters.get(i).toParserRule(parser));
        }
        myTemplate.setRules(rules);
        return myTemplate.toParserRule(parser);
    }
}
