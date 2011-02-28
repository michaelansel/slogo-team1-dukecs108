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
        System.out.println(result.getList().toString());
        // fd,whitespace,expression
        myDistanceExpression =
            (Expression) ((Token) result.getList().get(2)).value;
    }


    @Override
    public int evaluate (Arena arena)
    {
        return arena.getCurrentTurtle()
                    .move(myDistanceExpression.evaluate(arena));
    }

}
