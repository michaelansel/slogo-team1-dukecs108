/**
 * 
 */
package slogo.model.expression.command;

import java.util.logging.Logger;
import slogo.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public abstract class Command extends Expression
{
    protected static final Logger logger =
        Logger.getLogger(Command.class.getName());
    static
    {
        logger.setParent(Expression.logger);
    }
}
