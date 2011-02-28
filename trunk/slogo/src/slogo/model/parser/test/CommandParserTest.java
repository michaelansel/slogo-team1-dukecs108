package slogo.model.parser.test;

import java.util.ResourceBundle;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import slogo.model.parser.CommandLexer;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.grammar.GrammarParserFactory;


public class CommandParserTest extends TestCase
{

    private GrammarParserFactory commandParserFactory;


    @Before
    public void setUp () throws Exception
    {
        commandParserFactory =
            new GrammarParserFactory(ResourceBundle.getBundle("slogo.model.parser.SlogoCommandGrammar"));
    }


    /**
     * @throws ParserException
     */
    @Test
    public void testParse () throws ParserException
    {
        ParserResult result = new ParserResult();
        AbstractParser commandParser =
            commandParserFactory.create(new CommandLexer(result.getList()));
        System.out.println(commandParser.run());
    }

}
