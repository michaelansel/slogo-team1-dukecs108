/**
 * 
 */
package slogo.model.expression.binary;

import slogo.model.arena.Arena;
import slogo.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public class Divide extends Arithmetic
{

    public static final char TOKEN = '/';
    public static final String TOKEN_REGEX = String.format("[%s]", TOKEN);


    public Divide (Expression subExpressionA, Expression subExpressionB)
    {
        super(subExpressionA, subExpressionB);
    }


    @Override
    protected int evaluate (Arena arena,
                            Expression expressionA,
                            Expression expressionB)
    {
        return expressionA.evaluate(arena) / expressionB.evaluate(arena);
    }


    @Override
    public String toString ()
    {
        return toString(TOKEN);
    }

}