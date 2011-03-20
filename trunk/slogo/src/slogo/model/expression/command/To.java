/**
 * 
 */
package slogo.model.expression.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;
import slogo.model.expression.Variable;
import slogo.model.parser.SlogoParser;
import util.parser.ParserResult;
import util.parser.Token;


/**
 * @author Michael Ansel
 */
public class To extends Command
{

    private String myCommandName;
    private Expression myExpression;
    private List<Variable> myVariableList;


    public To (String commandName, Expression expression)
    {
        logger.log(Level.FINER,
                   "Creating To Expression: {0} {1}",
                   new Object[] { commandName, expression });
        myCommandName = commandName;
        myExpression = expression;
    }


    @SuppressWarnings("unchecked")
    public To (ParserResult result)
    {
        // <to>,<whitespace>,userCommandName,optional(variablegroup),commandgroup
        myCommandName = ((Token) result.getList().get(2)).value.toString();
        if (result.getList().size() == 5) myVariableList =
            (List<Variable>) result.getList().get(3);
        else myVariableList = Arrays.asList(new Variable[] {});
        myExpression =
            (Expression) result.getList().get(result.getList().size() - 1);
    }


    @Override
    public int evaluate (Arena arena, Turtle turtle)
    {
        logger.log(Level.FINE, "Evaluating: {0}", this);

        SlogoParser.addUserCommand(myCommandName, myVariableList);
        arena.addUserCommand(myCommandName, myExpression);
        int retval = 0;

        logger.log(Level.FINER, "Returning: {0}", retval);
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { myExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("To(%s,%s)", myCommandName, myExpression);
    }

}
