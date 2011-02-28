/**
 * 
 */
package slogo.model.expression.command;

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
        super(result);
        System.out.println("Creating Forward Expression: " +
                           result.getList().toString());
        // fd,whitespace,expression
        if (result.getList().get(2) instanceof Token) myDistanceExpression =
            (Expression) ((Token) result.getList().get(2)).value;
        else myDistanceExpression = (Expression) result.getList().get(2);
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
    public String toString ()
    {
        return String.format("Forward(%s)", myDistanceExpression);
    }

}
