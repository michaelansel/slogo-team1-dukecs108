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
public class Left extends Command
{

    private Expression myAngleExpression;


    public Left (Expression angleExpression)
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating Left Expression: " +
                                                         angleExpression.toString());
        myAngleExpression = angleExpression;
    }


    public Left (ParserResult result)
    {
        // <lt>,<whitespace>,expression
        this((Expression) result.getList().get(2));
    }


    @Override
    public int evaluate (Arena arena)
    {
        if (logger.isLoggable(Level.FINE)) logger.fine("Evaluating: " +
                                                       this.toString());
        int val = myAngleExpression.evaluate(arena);
        if (logger.isLoggable(Level.FINER)) logger.finer("SubExpression: " +
                                                         val);
        int retval = arena.getCurrentTurtle().rotate(val);
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
        return String.format("Left(%s)", myAngleExpression);
    }

}
