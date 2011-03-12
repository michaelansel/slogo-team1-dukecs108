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
import util.parser.ITokenRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.grammar.GrammarLexer;
import util.parser.grammar.GrammarParser;
import util.parser.grammar.ParseTreeNode;


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
        AbstractParser parser =
            new GrammarParser(new GrammarLexer(input).tokenize());
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
        GrammarLexer lexer = new GrammarLexer(input);
        AbstractParser parser = new GrammarParser(lexer.tokenize());
        ParserResult result = parser.run();
        assertEquals("[ParseTreeNode<Sequence([ParseTreeNode<ZeroOrMore([ParseTreeNode(ExactlyOne(Delimiter))])>])>]",
                     result.getList().toString());

        ParseTreeNode node = ((ParseTreeNode) (result.getList().get(0)));
        Map<String, ITokenRule> tokenRules = lexer.getTokenRuleMap();
        Map<String, AbstractParserRule> grammarRules =
            new HashMap<String, AbstractParserRule>();
        AbstractParserRule rule = node.toParserRule(tokenRules, grammarRules);
        rule.initializeRule();
        ParserResult newResult =
            rule.evaluate(new GrammarLexer(",,,,,,,").tokenize());
        assertEquals(7, newResult.getList().size());

    }

}
