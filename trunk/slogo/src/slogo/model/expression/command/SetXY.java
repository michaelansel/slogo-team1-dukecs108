/**
 * 
 */
package slogo.model.expression.command;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Collection;
import slogo.model.arena.Arena;
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
        logger.finer("Creating SetXY Expression: " +
                           xExpression.toString() + "," +
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
    public int evaluate (Arena arena)
    {
        logger.fine("Evaluating: " + this.toString());
        int xVal = myXExpression.evaluate(arena);
        int yVal = myYExpression.evaluate(arena);
        logger.finer("X Expression: " + xVal);
        logger.finer("Y Expression: " + yVal);
        int retval =
            arena.getCurrentTurtle().move(new Point2D.Double(xVal, yVal));
        logger.finer("Returning: " + retval);
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
