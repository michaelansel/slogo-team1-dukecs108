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
public class PenDown extends Command
{

    public PenDown ()
    {
        logger.log(Level.FINER, "Creating PenDown Expression");

    }


    public PenDown (ParserResult result)
    {
        // <pendown>
        this();
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);

        arena.getCurrentTurtle().getPen().putDown();
        int retval = Expression.TRUE;
        logger.log(Level.FINER, "Returning: {0}", retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] {});
    }


    @Override
    public String toString ()
    {
        return "PenDown";
    }

}
