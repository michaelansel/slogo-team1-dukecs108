/**
 * 
 */
package slogo.model.expression.binary;

import slogo.model.arena.Arena;
import slogo.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public abstract class Boolean extends BinaryExpression
{
    public Boolean (Expression subExpressionA, Expression subExpressionB)
    {
        super(subExpressionA, subExpressionB);
    }
}
