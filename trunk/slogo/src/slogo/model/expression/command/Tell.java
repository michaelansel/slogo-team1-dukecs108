/**
 * 
 */
package slogo.model.expression.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class Tell extends Command
{

    private List<Expression> myTurtleIdExpressions;


    public Tell (List<Expression> turtleIdExpressions)
    {
        logger.log(Level.FINER,
                   "Creating Tell Expression: {0}",
                   turtleIdExpressions);
        myTurtleIdExpressions = turtleIdExpressions;
    }


    @SuppressWarnings("unchecked")
    public Tell (ParserResult result)
    {
        // <Tell>,<Whitespace>,ExpressionGroup
        this((List<Expression>) result.getList().get(2));
    }


    @Override
    public int evaluate (Arena arena)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);
        List<Integer> turtleIds = new ArrayList<Integer>();
        for (Expression expression : myTurtleIdExpressions)
        {
            int val = expression.evaluate(arena);
            logger.log(Level.FINER, "TurtleID Expression: {0}", val);
            turtleIds.add(val);
        }
        arena.setCurrentTurtleID(turtleIds.get(turtleIds.size() - 1)); // TODO only selects the last turtle
        int retval = turtleIds.get(turtleIds.size() - 1);
        logger.log(Level.FINER, "Returning: {0}", retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return new ArrayList<Expression>(myTurtleIdExpressions);
    }


    @Override
    public String toString ()
    {
        return String.format("Tell(%s)", myTurtleIdExpressions);
    }

}
