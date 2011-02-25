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
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ParserException;
import util.parser.grammar.GrammarLexer;
import util.parser.grammar.GrammarParseTreeNode;


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
     * Test method for
     * {@link util.parser.grammar.GrammarParseTreeNode#setRules(java.util.Map)}.
     */
    @Test
    public final void testSetRules ()
    {
        fail("Not yet implemented"); // TODO
    }


    /**
     * Test method for
     * {@link util.parser.grammar.GrammarParseTreeNode#toParserRule()}.
     * 
     * @throws ParserException
     */
    @Test
    public final void testToParserRule () throws ParserException
    {
        GrammarParseTreeNode exactlyOne1 =
            new GrammarParseTreeNode("ExactlyOne", "Constant");
        GrammarParseTreeNode exactlyOne2 =
            new GrammarParseTreeNode("SomeWhitespace");
        GrammarParseTreeNode someWhitespace =
            new GrammarParseTreeNode("ExactlyOne", "Whitespace");

        GrammarParseTreeNode firstOf =
            new GrammarParseTreeNode("FirstOf",
                                     Arrays.asList(new GrammarParseTreeNode[] {
                                             exactlyOne1,
                                             exactlyOne2 }));
        List<GrammarParseTreeNode> nodes =
            Arrays.asList(new GrammarParseTreeNode[] {
                    exactlyOne1,
                    exactlyOne2,
                    someWhitespace,
                    firstOf });

        Map<String, AbstractParserRule> rules =
            new HashMap<String, AbstractParserRule>();
        AbstractParser parser = new AbstractParser(new GrammarLexer("hello"))
        {};

        for (GrammarParseTreeNode node : nodes)
        {
            node.setRules(rules);
            AbstractParserRule rule = node.toParserRule(parser);
            if (node.equals(someWhitespace))
            {
                rule.setRuleName("SomeWhitespace");
                rules.put("SomeWhitespace", rule);
            }
            System.out.println(String.format("%s => %s", node, rule));
        }

        for (AbstractParserRule rule : rules.values())
            rule.initializeRule();
        for (GrammarParseTreeNode node : nodes)
            node.toParserRule(parser).initializeRule();

        System.out.println(firstOf.toParserRule(parser).evaluate());
    }

}
