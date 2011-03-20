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
public class Right extends Command
{

    private Expression myAngleExpression;


    public Right (Expression angleExpression)
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating Right Expression: " +
                                                         angleExpression.toString());
        myAngleExpression = angleExpression;
    }


    public Right (ParserResult result)
    {
        // <rt>,<whitespace>,expression
        this((Expression) result.getList().get(2));
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        if (logger.isLoggable(Level.FINE)) logger.fine("Evaluating: " +
                                                       this.toString());
        int val = myAngleExpression.evaluate(arena, turtle);
        if (logger.isLoggable(Level.FINER)) logger.finer("SubExpression: " +
                                                         val);
        int retval = turtle.rotate(-1 * val);
        if (logger.isLoggable(Level.FINER)) logger.finer("Returning: " + retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { myAngleExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("Right(%s)", myAngleExpression);
    }

}
