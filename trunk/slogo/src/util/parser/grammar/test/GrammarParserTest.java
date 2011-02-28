/**
 * 
 */
package util.parser.grammar.test;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.grammar.GrammarLexer;
import util.parser.grammar.ParseTreeNode;
import util.parser.grammar.GrammarParser;


/**
 * @author Michael Ansel
 */
public class GrammarParserTest extends TestCase
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {}


    /**
     * Test method for
     * {@link util.parser.grammar.GrammarParser#GrammarParser(util.parser.AbstractLexer)}
     * .
     */
    @Test
    public final void testGrammarParser ()
    {
        String input = "a(b(e,f,g),c(d))";
        AbstractParser parser = new GrammarParser(new GrammarLexer(input));
    }


    /**
     * Test method for {@link util.parser.grammar.GrammarParser#run()}.
     * 
     * @throws ParserException
     */
    @Test
    public final void testGrammarParserRun () throws ParserException
    {
        String input = "Sequence(ZeroOrMore(<Delimiter>))";
        AbstractParser parser = new GrammarParser(new GrammarLexer(input));
        ParserResult result = parser.run();
        System.out.println(result);
        ParseTreeNode node =
            ((ParseTreeNode) (result.getList().get(0)));
        Map<String, AbstractParserRule> rules =
            new HashMap<String, AbstractParserRule>();
        AbstractParser newParser =
            new AbstractParser(new GrammarLexer(",,,,,,,"))
            {};
        AbstractParserRule rule = node.toParserRule(newParser, rules);
        rule.initializeRule();
        ParserResult newResult = rule.evaluate();
        assertEquals(7, newResult.getList().size());

    }

}
