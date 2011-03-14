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
public class Repeat extends Command
{

    private Expression myCountExpression;
    private Expression myRepeatExpression;


    public Repeat (Expression countExpression, Expression repeatExpression)
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating Repeat Expression: " +
                                                         countExpression.toString() +
                                                         " " +
                                                         repeatExpression.toString());
        myCountExpression = countExpression;
        myRepeatExpression = repeatExpression;
    }


    public Repeat (ParserResult result)
    {
        // <repeat>,<whitespace>,expression,commandgroup
        this((Expression) result.getList().get(2),
             (Expression) result.getList().get(3));
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);

        int countVal = myCountExpression.evaluate(arena);
        logger.log(Level.FINER, "Count Expression: {0}", countVal);

        int retval = 0;
        for (int i = 0; i < countVal; i++)
            retval = myRepeatExpression.evaluate(arena);

        logger.log(Level.FINER, "Returning: {0}", retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] {
                myCountExpression,
                myRepeatExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("Repeat(%s,%s)",
                             myCountExpression,
                             myRepeatExpression);
    }

}
