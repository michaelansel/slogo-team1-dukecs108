/**
 * 
 */
package slogo.model.parser.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class SlogoParserTest extends TestCase
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {}


    /**
     * Test method for
     * {@link slogo.model.parser.SlogoParser#parse(java.lang.String)}.
     * 
     * @throws ParserException
     */
    @Test
    public final void testParse () throws ParserException
    {
        ParserResult result = SlogoParser.parse("fd 50");
        System.out.println(result);
    }

}
