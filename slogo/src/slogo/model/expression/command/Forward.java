/**
 * 
 */
package slogo.model.expression.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class Forward extends Command
{

    private Expression myDistanceExpression;


    public Forward (Expression distanceExpression)
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating Forward Expression: " +
                                                         distanceExpression.toString());
        myDistanceExpression = distanceExpression;
    }


    public Forward (ParserResult result)
    {
        // <fd>,<whitespace>,expression
        this((Expression) result.getList().get(2));
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        if (logger.isLoggable(Level.FINE)) logger.fine("Evaluating: " +
                                                       this.toString());
        int val = myDistanceExpression.evaluate(arena, turtle);
        if (logger.isLoggable(Level.FINER)) logger.finer("SubExpression: " +
                                                         val);
        int retval = turtle.move(val);
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
        return String.format("Forward(%s)", myDistanceExpression);
    }

}
