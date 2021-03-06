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
public class Exponent extends Arithmetic
{

    public static final char TOKEN = '^';
    public static final String TOKEN_REGEX = String.format("[\\%s]", TOKEN);


    public Exponent (Expression subExpressionA, Expression subExpressionB)
    {
        super(subExpressionA, subExpressionB);
    }


    @Override
    protected int evaluate (Arena arena,
                            Turtle turtle,
                            Expression expressionA,
                            Expression expressionB)
    {
        return (int) Math.round(Math.pow(expressionA.evaluate(arena, turtle),
                                         expressionB.evaluate(arena, turtle)));
    }


    @Override
    public String toString ()
    {
        return toString(TOKEN);
    }

}
