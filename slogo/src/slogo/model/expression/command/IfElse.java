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
public class IfElse extends Command
{

    private Expression myConditionalExpression;
    private Expression myTrueExpression, myFalseExpression;


    public IfElse (Expression conditionalExpression,
                   Expression trueExpression,
                   Expression falseExpression)
    {
        logger.log(Level.FINER,
                   "Creating IfElse Expression: {0} {1} {2}",
                   new Object[] {
                           conditionalExpression,
                           trueExpression,
                           falseExpression });
        myConditionalExpression = conditionalExpression;
        myTrueExpression = trueExpression;
        myFalseExpression = falseExpression;
    }


    public IfElse (ParserResult result)
    {
        // <ifelse>,<whitespace>,expression,commandgroup,commandgroup
        this((Expression) result.getList().get(2),
             (Expression) result.getList().get(3),
             (Expression) result.getList().get(4));
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);

        int condVal = myConditionalExpression.evaluate(arena, turtle);
        logger.log(Level.FINER, "Conditional Expression: {0}", condVal);

        int retval = condVal;
        if (condVal != 0)
        {
            logger.log(Level.FINE, "Evaluating \"true\" branch");
            myTrueExpression.evaluate(arena, turtle);
        }
        else
        {
            logger.log(Level.FINE, "Evaluating \"false\" branch");
            myFalseExpression.evaluate(arena, turtle);
        }

        logger.log(Level.FINER, "Returning: {0}", retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] {
                myConditionalExpression,
                myTrueExpression,
                myFalseExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("IfElse(%s,%s,%s)",
                             myConditionalExpression,
                             myTrueExpression,
                             myFalseExpression);
    }

}
