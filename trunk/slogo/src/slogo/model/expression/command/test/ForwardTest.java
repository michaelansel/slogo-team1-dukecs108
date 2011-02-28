/**
 * 
 */
package slogo.model.expression.command.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;

/**
 * @author Michael Ansel
 * 
 */
public class ForwardTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link slogo.model.expression.command.Forward#evaluate(slogo.model.arena.Arena)}
	 * .
	 * 
	 * @throws ParserException
	 */
	@Test
	public final void testEvaluate() throws ParserException {
		ParserResult result = SlogoParser.parse("fd fd 50");
		Expression expression = (Expression) result.getList().get(0);
		Arena arena = new Arena(null);
		arena.addTurtle();
		assertEquals(50, expression.evaluate(arena));
	}

}
