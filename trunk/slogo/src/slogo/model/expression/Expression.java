/**
 * 
 */
package slogo.model.expression;

import java.util.Collection;
import slogo.model.arena.Arena;
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

    private static IResultHandler myParseResultHandlerInstance;


    /**
     * Recursively evaluate this Expression and all of its child Expressions.
     * Children are evaluated first, followed by the parent.
     * 
     * @param arena Arena in which to evaluate the Expression
     * @return numeric result of Expression evaluation (see language definition
     *         for specifics)
     */
    public abstract int evaluate (Arena arena);


    /**
     * @return Collection of Expression objects that are combined by this
     *         Expression
     */
    protected abstract Collection<Expression> getExpressions ();


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
}
