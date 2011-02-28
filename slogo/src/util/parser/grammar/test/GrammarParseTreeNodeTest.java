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

        Map<String, AbstractParserRule> rules =
            new HashMap<String, AbstractParserRule>();
        AbstractParser parser = new AbstractParser(new GrammarLexer("hello"))
        {};

        for (ParseTreeNode node : nodes)
        {
            AbstractParserRule rule = node.toParserRule(parser, rules);
            if (node.equals(someWhitespace))
            {
                rule.setRuleName("SomeWhitespace");
                rules.put("SomeWhitespace", rule);
            }
            System.out.println(String.format("%s => %s", node, rule));
        }

        for (AbstractParserRule rule : rules.values())
            rule.initializeRule();
        for (ParseTreeNode node : nodes)
            node.toParserRule(parser, rules).initializeRule();

        System.out.println(firstOf.toParserRule(parser, rules).evaluate());
    }

}
