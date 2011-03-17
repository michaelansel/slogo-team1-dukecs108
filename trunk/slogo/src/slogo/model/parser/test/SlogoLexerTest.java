/**
 * 
 */
package slogo.model.parser.test;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.parser.SlogoLexer;
import util.parser.ParserException;
import util.parser.Token;


/**
 * @author Michael Ansel
 */
public class SlogoLexerTest extends TestCase
{
    private List<Token> expected;
    private List<Token> actual;


    @Before
    public void setUp () throws Exception
    {}


    @Test
    public final void testToWithoutParameters () throws ParserException
    {
        actual = new SlogoLexer("to draw[fd 50]").tokenize().getTokens();
        expected =
            Arrays.asList(new Token[] {
                    makeToken("To", "to"),
                    makeToken("Whitespace", " "),
                    makeToken("CommandName", "draw"),
                    makeToken("BeginParameterGroup", "["),
                    makeToken("Forward", "fd"),
                    makeToken("Whitespace", " "),
                    makeToken("Constant", "50"),
                    makeToken("EndParameterGroup", "]") });
        assertEquals(expected.toString(), actual.toString());
    }


    @Test
    public final void testToWithParameters () throws ParserException
    {
        actual =
            new SlogoLexer("to draw[:var][fd :var]").tokenize().getTokens();
        expected =
            Arrays.asList(new Token[] {
                    makeToken("To", "to"),
                    makeToken("Whitespace", " "),
                    makeToken("CommandName", "draw"),
                    makeToken("BeginParameterGroup", "["),
                    makeToken("Variable", ":var"),
                    makeToken("EndParameterGroup", "]"),
                    makeToken("BeginParameterGroup", "["),
                    makeToken("Forward", "fd"),
                    makeToken("Whitespace", " "),
                    makeToken("Variable", ":var"),
                    makeToken("EndParameterGroup", "]") });
        assertEquals(expected.toString(), actual.toString());
    }


    private Token makeToken (String tokenRuleName, String tokenValue)
    {
        return new SlogoLexer("").getTokenRuleByName(tokenRuleName)
                                 .makeToken(tokenValue);
    }

}
