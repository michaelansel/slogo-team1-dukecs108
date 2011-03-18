/**
 * 
 */
package slogo.model.expression.binary;

import slogo.model.arena.Arena;
import slogo.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public class LessThan extends slogo.model.expression.binary.Boolean
{

    public static final char TOKEN = '<';
    public static final String TOKEN_REGEX = String.format("[%s]", TOKEN);


    public LessThan (Expression subExpressionA, Expression subExpressionB)
    {
        super(subExpressionA, subExpressionB);
    }


    @Override
    protected int evaluate (Arena arena,
                            Expression expressionA,
                            Expression expressionB)
    {
        return expressionA.evaluate(arena) < expressionB.evaluate(arena) ? Expression.TRUE
                                                                        : Expression.FALSE;
    }


    @Override
    public String toString ()
    {
        return toString(TOKEN);
    }

}
