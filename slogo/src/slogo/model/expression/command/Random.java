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
public class Random extends Command
{

    private Expression myMaxExpression;


    public Random (Expression maxExpression)
    {
        logger.log(Level.FINER,
                   "Creating Random Expression: {0}",
                   maxExpression);
        myMaxExpression = maxExpression;
    }


    public Random (ParserResult result)
    {
        // <random>,<whitespace>,expression
        this((Expression) result.getList().get(2));
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);
        int val = myMaxExpression.evaluate(arena, turtle);
        logger.log(Level.FINER, "SubExpression: {0}", val);
        int retval = (int) Math.round(Math.random() * val);
        logger.log(Level.FINER, "Returning: {0}", retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { myMaxExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("Random(%s)", myMaxExpression);
    }

}
