/**
 * 
 */
package slogo.model.expression.command;

import java.awt.geom.Point2D;
import java.text.MessageFormat;
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
public class ClearScreen extends Command
{

    public ClearScreen ()
    {
        logger.log(Level.FINER, "Creating ClearScreen Expression");
    }


    public ClearScreen (ParserResult result)
    {
        // <clearscreen>
        this();
    }



    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);
        int retval = turtle.resetTurtle(arena.getCenter());
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
