/**
 * 
 */
package util.parser.grammar.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import util.parser.AbstractParserRule;
import util.parser.ITokenRule;
import util.parser.ParserException;
import util.parser.grammar.GrammarLexer;
import util.parser.grammar.ParseTreeNode;


/**
 * @author mra13
 */
public class GrammarParseTreeNodeTest extends TestCase
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {}


    /**
     * Test method for {@link util.parser.grammar.ParseTreeNode#toParserRule()}.
     * 
     * @throws ParserException
     */
    @Test
    public final void testToParserRule () throws ParserException
    {
        ParseTreeNode exactlyOne1 = new ParseTreeNode("ExactlyOne", "Constant");
        ParseTreeNode exactlyOne2 = new ParseTreeNode("SomeWhitespace");
        ParseTreeNode someWhitespace =
            new ParseTreeNode("ExactlyOne", "Whitespace");

        ParseTreeNode firstOf =
            new ParseTreeNode("FirstOf", Arrays.asList(new ParseTreeNode[] {
                    exactlyOne1,
                    exactlyOne2 }));
        List<ParseTreeNode> nodes =
            Arrays.asList(new ParseTreeNode[] {
                    exactlyOne1,
                    exactlyOne2,
                    someWhitespace,
                    firstOf });

        GrammarLexer lexer = new GrammarLexer("hello");
        Map<String, ITokenRule> tokenRules = lexer.getTokenRuleMap();
        Map<String, AbstractParserRule> grammarRules =
            new HashMap<String, AbstractParserRule>();

        for (ParseTreeNode node : nodes)
        {
            AbstractParserRule rule =
                node.toParserRule(tokenRules, grammarRules);
            if (node.equals(someWhitespace))
            {
                rule.setRuleName("SomeWhitespace");
                grammarRules.put("SomeWhitespace", rule);
            }
            System.out.println(String.format("%s => %s", node, rule));
        }

        for (AbstractParserRule rule : grammarRules.values())
            rule.initializeRule();
        for (ParseTreeNode node : nodes)
            node.toParserRule(tokenRules, grammarRules).initializeRule();

        System.out.println(firstOf.toParserRule(tokenRules, grammarRules)
                                  .evaluate(lexer.tokenize()));
    }

}
