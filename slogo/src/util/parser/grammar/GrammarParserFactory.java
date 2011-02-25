/**
 * 
 */
package util.parser.grammar;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import util.parser.AbstractLexer;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class GrammarParserFactory
{

    private Map<String, AnonymousRule> myAnonymousRules;
    private Map<String, GrammarParseTreeNode> myParseTrees;
    private ResourceBundle mySyntax;


    public GrammarParserFactory (ResourceBundle syntax) throws ParserException
    {
        mySyntax = syntax;
        myAnonymousRules = new HashMap<String, AnonymousRule>();
        myParseTrees = buildParseTrees(syntax);
    }


    private Map<String, GrammarParseTreeNode> buildParseTrees (ResourceBundle syntax)
        throws ParserException
    {
        Map<String, GrammarParseTreeNode> parseTrees =
            new HashMap<String, GrammarParseTreeNode>();
        for (String ruleName : syntax.keySet())
        {
            GrammarParser parser =
                new GrammarParser(new GrammarLexer(syntax.getString(ruleName)));
            ParserResult parsedGrammar = parser.run();
            if (parsedGrammar.getList().size() != 1) throw new ParserException("Improper ParserResult: " +
                                                                               parsedGrammar.toString());
            if (parsedGrammar.getList().get(0) instanceof AnonymousRule) myAnonymousRules.put(ruleName,
                                                                                              (AnonymousRule) parsedGrammar.getList()
                                                                                                                           .get(0));
            else if (parsedGrammar.getList().get(0) instanceof GrammarParseTreeNode)
            {
                parseTrees.put(ruleName,
                               (GrammarParseTreeNode) parsedGrammar.getList()
                                                                   .get(0));
            }
            else throw new RuntimeException("Invalid result: " +
                                            parsedGrammar.getList()
                                                         .get(0)
                                                         .toString());
        }
        return parseTrees;
    }


    public AbstractParser create (AbstractLexer lexer)
    {
        final AbstractParserRule rootRule = new AbstractParserRule()
        {};
        AbstractParser parser = new AbstractParser(lexer)
        {
            @Override
            protected AbstractParserRule getRootRule ()
            {
                return rootRule;
            }


            @Override
            protected void initializeRules ()
            {}
        };

        Map<String, AbstractParserRule> rules =
            new HashMap<String, AbstractParserRule>(myAnonymousRules);
        for (AnonymousRule rule : myAnonymousRules.values())
            rule.setRules(rules);
        for (Map.Entry<String, GrammarParseTreeNode> entry : myParseTrees.entrySet())
        {
            String ruleName = entry.getKey();
            GrammarParseTreeNode node = entry.getValue();
            node.setRules(rules);
            AbstractParserRule rule = node.toParserRule(parser);
            rule.setRuleName(ruleName);
            rules.put(ruleName, rule);
            parser.addRule(ruleName, rule);
        }
        for (AbstractParserRule rule : rules.values())
            rule.initializeRule();

        rootRule.setRule(rules.get("Root"));

        for (Map.Entry<String, AbstractParserRule> entry : rules.entrySet())
            System.out.println(entry.getKey() + ": " +
                               entry.getValue().toString());

        return parser;
    }
}
