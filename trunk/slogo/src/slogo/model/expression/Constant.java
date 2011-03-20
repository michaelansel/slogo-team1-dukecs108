/**
 * 
 */
package slogo.model.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.Token;


/**
 * @author Michael Ansel
 */
public class Constant extends Expression
{
    private static final class ParseResultHandler implements IResultHandler
    {

        /**
         * @see util.parser.IResultHandler#handleResult(util.parser.ParserResult)
         * @see slogo.model.expression.Expression#getParserResultHandler()
         */
        @Override
        public ParserResult handleResult (ParserResult result)
            throws ParserException
        {
            List<Object> objects = result.getList();
            if (objects.size() == 1 && objects.get(0) instanceof Token) return new ParserResult(new Constant(((Token) objects.get(0)).value.toString()));
            if (objects.size() == 2 && objects.get(0) instanceof Token &&
                objects.get(1) instanceof Token) return new ParserResult(new Constant(((Token) objects.get(0)).value.toString() +
                                                                                      ((Token) objects.get(1)).value.toString()));
            throw new IllegalArgumentException("Invalid result: " +
                                               objects.toString());
        }

    }

    private static IResultHandler myParseResultHandlerInstance;


    /**
     * Return the singleton for processing ParserResults into Expression trees
     * 
     * @return IParseResultHandler singleton
     */
    public static IResultHandler getParserResultHandler ()
    {
        if (myParseResultHandlerInstance == null)
        {
            myParseResultHandlerInstance = new ParseResultHandler();
        }
        return myParseResultHandlerInstance;
    }

    private int myValue;


    public Constant (int value)
    {
        myValue = value;
    }


    public Constant (String value)
    {
        myValue = Integer.parseInt(value);
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        return myValue;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return new ArrayList<Expression>();
    }


    @Override
    public String toString ()
    {
        return String.valueOf(myValue);
    }
}
