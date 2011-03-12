/**
 * 
 */
package slogo.model.expression.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class Backward extends Command
{

    private Expression myDistanceExpression;


    public Backward (Expression distanceExpression)
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating Backward Expression: " +
                                                         distanceExpression.toString());
        myDistanceExpression = distanceExpression;
    }


    public Backward (ParserResult result)
    {
        // <bk>,<whitespace>,expression
        this((Expression) result.getList().get(2));
    }


    @Override
    public int evaluate (Arena arena)
    {
        if (logger.isLoggable(Level.FINE)) logger.fine("Evaluating: " +
                                                       this.toString());
        int val = myDistanceExpression.evaluate(arena);
        if (logger.isLoggable(Level.FINER)) logger.finer("SubExpression: " +
                                                         val);
        int retval = arena.getCurrentTurtle().move(-1*val);
        if (logger.isLoggable(Level.FINER)) logger.finer("Returning: " + retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { myDistanceExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("Backward(%s)", myDistanceExpression);
    }

}
