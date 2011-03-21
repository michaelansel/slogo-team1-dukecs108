/**
 * 
 */
package slogo.model.expression.binary;

import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public class GreaterThan extends slogo.model.expression.binary.Boolean
{

    public static final char TOKEN = '>';
    public static final String TOKEN_REGEX = String.format("[%s]", TOKEN);


    public GreaterThan (Expression subExpressionA, Expression subExpressionB)
    {
        super(subExpressionA, subExpressionB);
    }


    @Override
    protected int evaluate (Arena arena,
                            Turtle turtle,
                            Expression expressionA,
                            Expression expressionB)
    {
        boolean compare =
            expressionA.evaluate(arena, turtle) > expressionB.evaluate(arena,
                                                                       turtle);
        return compare ? Expression.TRUE : Expression.FALSE;
    }


    @Override
    public String toString ()
    {
        return toString(TOKEN);
    }

}
