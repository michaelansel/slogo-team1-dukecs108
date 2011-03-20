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
public class YCor extends Command
{

    public YCor ()
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating YCor Expression");
    }


    public YCor (ParserResult result)
    {
        // <ycor>
        this();
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        if (logger.isLoggable(Level.FINE)) logger.fine("Evaluating: " +
                                                       this.toString());
        int retval = (int) Math.round(turtle.getPosition().getY());
        if (logger.isLoggable(Level.FINER)) logger.finer("Returning: " + retval);
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
