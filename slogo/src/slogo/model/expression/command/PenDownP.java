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
public class PenDownP extends Command
{

    public PenDownP ()
    {
        logger.log(Level.FINER, "Creating PenDownP Expression");
    }


    public PenDownP (ParserResult result)
    {
        // <pendownp>
        this();
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);
        int retval =
            arena.getCurrentTurtle().getPen().isDown() ? Expression.TRUE
                                                      : Expression.FALSE;
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
        return "PenDownP";
    }

}
