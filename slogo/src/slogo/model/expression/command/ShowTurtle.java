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
public class ShowTurtle extends Command
{

    public ShowTurtle ()
    {
        logger.log(Level.FINER, "Creating ShowTurtle Expression");

    }


    public ShowTurtle (ParserResult result)
    {
        // <showturtle>
        this();
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);

        turtle.showTurtle();
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
        return "ShowTurtle";
    }

}
