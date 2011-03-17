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
public class If extends Command
{

    private Expression myConditionalExpression;
    private Expression myTrueExpression;


    public If (Expression conditionalExpression, Expression trueExpression)
    {
        logger.log(Level.FINER,
                   "Creating If Expression: {0} {1}",
                   new Object[] { conditionalExpression, trueExpression });
        myConditionalExpression = conditionalExpression;
        myTrueExpression = trueExpression;
    }


    public If (ParserResult result)
    {
        // <if>,<whitespace>,expression,commandgroup
        this((Expression) result.getList().get(2),
             (Expression) result.getList().get(3));
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);

        int condVal = myConditionalExpression.evaluate(arena);
        logger.log(Level.FINER, "Conditional Expression: {0}", condVal);

        int retval = condVal;
        if (condVal != 0)
        {
            logger.log(Level.FINE, "Evaluating \"true\" branch");
            myTrueExpression.evaluate(arena);
        }

        logger.log(Level.FINER, "Returning: {0}", retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] {
                myConditionalExpression,
                myTrueExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("If(%s,%s)",
                             myConditionalExpression,
                             myTrueExpression);
    }

}
