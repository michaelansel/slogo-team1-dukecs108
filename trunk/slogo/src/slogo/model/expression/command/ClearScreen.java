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
public class ClearScreen extends Command
{

    public ClearScreen ()
    {
        logger.log(Level.FINER, "Creating Home Expression");

    }


    public ClearScreen (ParserResult result)
    {
        // <clearscreen>
        this();
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);
        int retval = arena.getCurrentTurtle().resetTurtle(arena.getCenter());
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
        return "ClearScreen";
    }

}
