/**
 * 
 */
package slogo.model.expression.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import util.parser.ParserResult;
import util.parser.Token;


/**
 * @author Michael Ansel
 */
public class Forward extends Command
{

    private Expression myDistanceExpression;


    public Forward (ParserResult result)
    {
        // <fd>,<whitespace>,expression
        this((Expression) result.getList().get(2));
    }


    public Forward (Expression distanceExpression)
    {
        System.out.println("Creating Forward Expression: " +
                           distanceExpression.toString());
        myDistanceExpression = distanceExpression;
    }


    @Override
    public int evaluate (Arena arena)
    {
        System.out.println("Evaluating: " + this.toString());
        int val = myDistanceExpression.evaluate(arena);
        System.out.println("SubExpression: " + val);
        int retval = arena.getCurrentTurtle().move(val);
        System.out.println("Returning: " + retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { myDistanceExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("Forward(%s)", myDistanceExpression);
    }

}
