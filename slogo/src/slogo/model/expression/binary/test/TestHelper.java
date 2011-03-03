package slogo.model.expression.binary.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import slogo.model.expression.Expression;
import slogo.model.expression.binary.BinaryExpression;
import util.parser.ParserException;
import util.parser.ParserResult;


public class TestHelper
{

    public static void testCreate (List<Object> tokens, Expression expected)
        throws ParserException
    {
        ParserResult result =
            BinaryExpression.getParserResultHandler()
                            .handleResult(new ParserResult(tokens));
        assertTrue(result.getList().toString(),
                   result.getList().get(0) instanceof Expression);
        Expression actual = (Expression) result.getList().get(0);
        assertEquals(expected.toString(), actual.toString());
    }

}
