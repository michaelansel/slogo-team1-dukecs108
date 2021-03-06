/**
 * 
 */
package slogo.model.expression.command;

import java.awt.geom.Point2D;
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
public class SetXY extends Command
{

    private Expression myXExpression, myYExpression;


    public SetXY (Expression xExpression, Expression yExpression)
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating SetXY Expression: " +
                                                         xExpression.toString() +
                                                         "," +
                                                         yExpression.toString());
        myXExpression = xExpression;
        myYExpression = yExpression;
    }


    public SetXY (ParserResult result)
    {
        // <setxy>,<whitespace>,expression,expression
        this((Expression) result.getList().get(2),
             (Expression) result.getList().get(3));
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        if (logger.isLoggable(Level.FINE)) logger.fine("Evaluating: " +
                                                       this.toString());
        int xVal = myXExpression.evaluate(arena, turtle);
        int yVal = myYExpression.evaluate(arena, turtle);
        if (logger.isLoggable(Level.FINER)) logger.finer("X Expression: " +
                                                         xVal);
        if (logger.isLoggable(Level.FINER)) logger.finer("Y Expression: " +
                                                         yVal);
        int retval = turtle.move(new Point2D.Double(xVal, yVal));
        if (logger.isLoggable(Level.FINER)) logger.finer("Returning: " + retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { myXExpression, myYExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("SetXY(%s,%s)", myXExpression, myYExpression);
    }

}
