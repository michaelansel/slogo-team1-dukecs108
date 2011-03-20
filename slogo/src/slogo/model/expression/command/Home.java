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
public class Home extends Command
{

    public Home ()
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating Home Expression");
    }


    public Home (ParserResult result)
    {
        // <home>
        this();
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        if (logger.isLoggable(Level.FINE)) logger.fine("Evaluating: " +
                                                       this.toString());
        int retval = turtle.move(arena.getCenter());
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
        return "Home";
    }

}
