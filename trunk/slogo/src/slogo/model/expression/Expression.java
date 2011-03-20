/**
 * 
 */
package slogo.model.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public abstract class Expression
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
            return result;
        }
    }

    public static final int FALSE = 0;
    public static final Logger logger =
        Logger.getLogger(Expression.class.getName());
    private static IResultHandler myParseResultHandlerInstance;
    public static final int TRUE = 1;


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


    /**
     * Recursively build an easy to view representation of the Expression tree
     * starting at startExpression.
     * 
     * @param startExpression root of Expression tree
     * @return Ready-to-print String representation of Expression tree
     */
    public static String printExpressionTree (Expression startExpression)
    {
        return printExpressionTree(startExpression, 0);
    }


    private static String printExpressionTree (Expression startExpression,
                                               int level)
    {
        Collection<Expression> expressions = startExpression.getExpressions();
        String result = "";
        String indent = "";
        for (int i = 0; i < level; i++)
            indent += "  ";

        if (expressions.size() > 0)
        {
            result +=
                String.format("%s[%s]\n", indent, startExpression.getClass()
                                                                 .getName());
            for (Expression expr : expressions)
            {
                result += printExpressionTree(expr, level + 1);
            }
        }
        else
        {
            result +=
                String.format("%s\"%s\"\n", indent, startExpression.toString());
        }
        return result;
    }


    /**
     * Evaluate the Expression tree in the context of the given Arena for all
     * selected Turtles.
     * 
     * @param arena Arena in which to evalute the Expression
     * @return numeric result of Expression evaluation (see language definition
     *         for specifics)
     */
    public final int evaluate (Arena arena)
    {
        int retval = 0;
        for (Turtle turtle : new ArrayList<Turtle>(arena.getSelectedTurtles()))
            retval = evaluate(arena, turtle);
        return retval;
    }


    /**
     * Recursively evaluate this Expression and all of its child Expressions.
     * Children are evaluated first, followed by the parent.
     * 
     * @param arena Arena in which to evaluate the Expression
     * @param turtle Turtle on which to evaluate the Expression
     * @return numeric result of Expression evaluation (see language definition
     *         for specifics)
     */
    public abstract int evaluate (Arena arena, Turtle turtle);


    /**
     * @return Collection of Expression objects that are combined by this
     *         Expression
     */
    protected abstract Collection<Expression> getExpressions ();
}
