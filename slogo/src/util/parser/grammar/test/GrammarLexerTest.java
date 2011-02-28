/**
 * 
 */
package util.parser.grammar.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import util.parser.ITokenRule;
import util.parser.grammar.GrammarLexer;

/**
 * @author Michael Ansel
 */
public class GrammarLexerTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link util.parser.AbstractLexer#tokenize()}.
	 */
	@Test
	public final void testTokenize() {
		for (ITokenRule rule : GrammarLexer.Token.rules)
			System.out.println(rule.toString());
	}

}
