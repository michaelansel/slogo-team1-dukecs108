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
public class SetHeading extends Command
{

    private Expression myHeadingExpression;


    public SetHeading (Expression headingExpression)
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating SetHeading Expression: " +
                                                         headingExpression.toString());
        myHeadingExpression = headingExpression;
    }


    public SetHeading (ParserResult result)
    {
        // <setheading>,<whitespace>,expression
        this((Expression) result.getList().get(2));
    }


    @Override
    public int evaluate (Arena arena)
    {
        if (logger.isLoggable(Level.FINE)) logger.fine("Evaluating: " +
                                                       this.toString());
        int val = myHeadingExpression.evaluate(arena);
        if (logger.isLoggable(Level.FINER)) logger.finer("Heading Expression: " +
                                                         val);
        int retval = arena.getCurrentTurtle().setHeading(val);
        if (logger.isLoggable(Level.FINER)) logger.finer("Returning: " + retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { myHeadingExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("SetHeading(%s)", myHeadingExpression);
    }

}
