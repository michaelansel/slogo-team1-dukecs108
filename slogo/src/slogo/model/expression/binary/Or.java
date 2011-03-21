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
public class Or extends slogo.model.expression.binary.Boolean
{

    public static final char TOKEN = '|';
    public static final String TOKEN_REGEX = String.format("[%s]", TOKEN);


    public Or (Expression subExpressionA, Expression subExpressionB)
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
            expressionA.evaluate(arena, turtle) != 0 ||
                    expressionB.evaluate(arena, turtle) != 0;
        return compare ? Expression.TRUE : Expression.FALSE;
    }


    @Override
    public String toString ()
    {
        return toString(TOKEN);
    }

}
