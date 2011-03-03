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
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class GrammarParserFactory
{

    private Map<String, IResultHandler> myHandlers;
    private Map<String, ParseTreeNode> myParseTrees;
    private ResourceBundle mySyntax;


    public GrammarParserFactory (ResourceBundle syntax) throws ParserException
    {
        mySyntax = syntax;
        myParseTrees = buildParseTrees(mySyntax);
        myHandlers = new HashMap<String, IResultHandler>();
    }


    private Map<String, ParseTreeNode> buildParseTrees (ResourceBundle syntax)
        throws ParserException
    {
        Map<String, ParseTreeNode> parseTrees =
            new HashMap<String, ParseTreeNode>();
        for (String ruleName : syntax.keySet())
        {
            GrammarParser parser =
                new GrammarParser(new GrammarLexer(syntax.getString(ruleName)));
            ParserResult parsedGrammar = parser.run();
            if (parsedGrammar.getList().size() != 1) throw new ParserException("Improper ParserResult: " +
                                                                               parsedGrammar.toString());
            if (parsedGrammar.getList().get(0) instanceof ParseTreeNode)
            {
                parseTrees.put(ruleName,
                               (ParseTreeNode) parsedGrammar.getList().get(0));
            }
            else throw new RuntimeException("Invalid result: " +
                                            parsedGrammar.getList()
                                                         .get(0)
                                                         .toString());
        }
        return parseTrees;
    }


    public AbstractParser create (AbstractLexer lexer) throws ParserException
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
            new HashMap<String, AbstractParserRule>();
        for (Map.Entry<String, ParseTreeNode> entry : myParseTrees.entrySet())
        {
            String ruleName = entry.getKey();
            ParseTreeNode node = entry.getValue().clone();
            AbstractParserRule rule = node.toParserRule(parser, rules);
            rule.setRuleName(ruleName);
            rules.put(ruleName, rule);
            parser.addRule(ruleName, rule);
        }
        for (int i = 0; i < 10; i++)
            System.out.println();
        System.out.println("Done parsing syntax. Initializing parsed rules.");
        for (int i = 0; i < 10; i++)
            System.out.println();
        for (AbstractParserRule rule : rules.values())
            rule.initializeRule();

        rootRule.setRule(rules.get("Root"));

        for (Map.Entry<String, IResultHandler> entry : myHandlers.entrySet())
        {
            System.out.println("Adding handler to " + entry.getKey());
            parser.getRule(entry.getKey()).setHandler(entry.getValue());
        }

        for (Map.Entry<String, AbstractParserRule> entry : rules.entrySet())
            System.out.println(entry.getKey() + ": " +
                               entry.getValue().toString());

        return parser;
    }


    public void setHandler (String ruleName, IResultHandler parserResultHandler)
    {
        myHandlers.put(ruleName, parserResultHandler);
    }
}
