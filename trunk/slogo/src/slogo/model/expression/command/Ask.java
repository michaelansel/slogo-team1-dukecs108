/**
 * 
 */
package slogo.model.expression.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class Ask extends Command
{

    private List<Expression> myTurtleIdExpressions;
    private Expression myScopedExpression;


    public Ask (List<Expression> turtleIdExpressions, Expression scopedExpression)
    {
        logger.log(Level.FINER,
                   "Creating Ask Expression: {0} {1}", new Object[]{
                   turtleIdExpressions, scopedExpression});
        myTurtleIdExpressions = turtleIdExpressions;
        myScopedExpression = scopedExpression;
    }


    @SuppressWarnings("unchecked")
    public Ask (ParserResult result)
    {
        // <Tell>,<Whitespace>,ExpressionGroup,CommandGroup
        this((List<Expression>) result.getList().get(2),(Expression)result.getList().get(3));
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);
        List<Integer> turtleIds = new ArrayList<Integer>();
        for (Expression expression : myTurtleIdExpressions)
        {
            logger.log(Level.FINER, "Evaluating TurtleID Expression: {0}", expression);
            int val = expression.evaluate(arena, turtle);
            logger.log(Level.FINER, "TurtleID Expression: {0}", val);
            turtleIds.add(val);
        }
        
        // save current turtle selection
        List<Turtle> oldTurtles = new ArrayList<Turtle>(arena.getSelectedTurtles());
        
        // evaluate scoped expression with given turtles
        arena.selectTurtles(turtleIds);
        int retval = arena.evaluateExpression(myScopedExpression);
        
        // restore old turtle selection
        arena.selectTurtles(oldTurtles.toArray(new Turtle[oldTurtles.size()]));
        
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
