/**
 * 
 */
package slogo.model.expression.binary;

import slogo.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public abstract class Arithmetic extends BinaryExpression
{

    public Arithmetic (Expression subExpressionA, Expression subExpressionB)
    {
        super(subExpressionA, subExpressionB);
    }
}
