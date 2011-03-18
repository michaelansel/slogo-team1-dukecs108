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
public class Heading extends Command
{

    public Heading ()
    {
        logger.log(Level.FINER, "Creating Heading Expression");
    }


    public Heading (ParserResult result)
    {
        // <heading>
        this();
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);
        int retval =
            (int) Math.round(arena.getCurrentTurtle()
                                  .getPosition()
                                  .getHeading());
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
        return "Heading";
    }

}
