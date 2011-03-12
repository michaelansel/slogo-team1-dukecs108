/**
 * 
 */
package slogo.model.expression.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class Repeat extends Command
{

    private Expression myCountExpression;
    private List<Expression> myExpressions;


    public Repeat (Expression countExpression, List<Expression> expressions)
    {
        if (logger.isLoggable(Level.FINER)) logger.finer("Creating Repeat Expression: " +
                                                         countExpression.toString() +
                                                         " " +
                                                         expressions.toString());
        myCountExpression = countExpression;
        myExpressions = expressions;
    }


    @SuppressWarnings("unchecked")
    public Repeat (ParserResult result)
    {
        // <repeat>,<whitespace>,expression,commandgroup
        this((Expression) result.getList().get(2),
             (List<Expression>) result.getList().get(3));
    }


    @Override
    public int evaluate (Arena arena)
    {
        if (logger.isLoggable(Level.FINE)) logger.fine("Evaluating: " +
                                                       this.toString());
        int countVal = myCountExpression.evaluate(arena);
        if (logger.isLoggable(Level.FINER)) logger.finer("Count Expression: " +
                                                         countVal);
        int retval = 0;
        for(int i=0; i<countVal; i++)
            for(Expression expression : myExpressions)
                retval  = expression.evaluate(arena);
        
        if (logger.isLoggable(Level.FINER)) logger.finer("Returning: " + retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        List<Expression> list =
            Arrays.asList(new Expression[] { myCountExpression });
        list.addAll(myExpressions);
        return list;
    }


    @Override
    public String toString ()
    {
        return String.format("Repeat(%s,%s)", myCountExpression, myExpressions);
    }

}
