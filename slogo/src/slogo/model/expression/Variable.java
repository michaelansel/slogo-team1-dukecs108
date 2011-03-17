/**
 * 
 */
package slogo.model.expression;

import java.util.Arrays;
import java.util.Collection;
import slogo.model.arena.Arena;
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.Token;


/**
 * @author Michael Ansel
 */
public class Variable extends Expression
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
            // Assignment
            if (result.getList().size() == 3 &&
                result.getList().get(0) instanceof Token &&
                result.getList().get(1) instanceof Token &&
                result.getList().get(2) instanceof Expression)
            {
                Token nameToken = (Token) result.getList().get(0);
                Expression valueExpression =
                    (Expression) result.getList().get(2);
                return new ParserResult(new Variable(nameToken.value.toString(),
                                                     valueExpression));
            }
            // Reference
            else if (result.getList().size() == 1 &&
                     result.getList().get(0) instanceof Token)
            {
                Token nameToken = (Token) result.getList().get(0);
                return new ParserResult(new Variable(nameToken.value.toString()));
            }
            else
            {
                throw new ParserException("Invalid ParserResult: " +
                                          result.getList().toString());
            }

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

    private boolean assignment;
    private String myName;
    private Expression myValue;


    public Variable (String name)
    {
        this(name, null);
        assignment = false;
    }


    public Variable (String name, Expression value)
    {
        myName = name;
        myValue = value;
        assignment = true;
    }


    /**
     * @see slogo.model.expression.Expression#evaluate(slogo.model.arena.Arena)
     */
    @Override
    public int evaluate (Arena arena)
    {
        if (assignment)
        {
            arena.setVariable(myName, myValue);
            return 0;
        }
        else
        {
            return arena.getVariable(myName).evaluate(arena);
        }
    }


    /**
     * @see slogo.model.expression.Expression#getExpressions()
     */
    @Override
    protected Collection<Expression> getExpressions ()
    {
        if (assignment) return Arrays.asList(new Expression[] { myValue });
        return Arrays.asList(new Expression[] {});
    }


    @Override
    public String toString ()
    {
        if (assignment) return String.format("%s=%s",
                                             myName,
                                             myValue.toString());
        else return myName;
    }


    public String getName ()
    {
        return myName;
    }

}
