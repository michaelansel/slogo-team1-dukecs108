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
public class YCor extends Command
{

    public YCor ()
    {
        logger.finer("Creating YCor Expression");
    }


    public YCor (ParserResult result)
    {
        // <ycor>
        this();
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.fine("Evaluating: " + this.toString());
        int retval =
            (int) Math.round(arena.getCurrentTurtle().getPosition().getY());
        logger.finer("Returning: " + retval);
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
        return "YCor";
    }

}
