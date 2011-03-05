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
public class Right extends Command
{

    private Expression myAngleExpression;


    public Right (Expression angleExpression)
    {
        logger.finer("Creating Right Expression: " +
                           angleExpression.toString());
        myAngleExpression = angleExpression;
    }


    public Right (ParserResult result)
    {
        // <rt>,<whitespace>,expression
        this((Expression) result.getList().get(2));
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.fine("Evaluating: " + this.toString());
        int val = myAngleExpression.evaluate(arena);
        logger.finer("SubExpression: " + val);
        int retval = arena.getCurrentTurtle().rotate(val);
        logger.finer("Returning: " + retval);
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
