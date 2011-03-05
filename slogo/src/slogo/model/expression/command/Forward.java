/**
 * 
 */
package slogo.model.expression.command;

import java.util.Arrays;
import java.util.Collection;
import slogo.model.arena.Arena;
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
        logger.finer("Creating Forward Expression: " +
                           distanceExpression.toString());
        myDistanceExpression = distanceExpression;
    }


    public Forward (ParserResult result)
    {
        // <fd>,<whitespace>,expression
        this((Expression) result.getList().get(2));
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.fine("Evaluating: " + this.toString());
        int val = myDistanceExpression.evaluate(arena);
        logger.finer("SubExpression: " + val);
        int retval = arena.getCurrentTurtle().move(val);
        logger.finer("Returning: " + retval);
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
