/**
 * 
 */
package slogo.model.expression.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import slogo.model.expression.Variable;


/**
 * @author Michael Ansel
 */
public class UserCommand extends Command
{

    private String myName;
    private Expression myHowToExpression;
    private List<Variable> myVariables;
    private List<Object> myParameters;


    public UserCommand (String commandName, Expression howToExpression)
    {
        this(commandName,
             howToExpression,
             Arrays.asList(new Variable[] {}),
             Arrays.asList(new Object[] {}));
    }


    public UserCommand (String commandName,
                        Expression howToExpression,
                        List<Variable> variables,
                        List<Object> parameters)
    {
        myName = commandName;
        myHowToExpression = howToExpression;
        myVariables = variables;
        myParameters = parameters;
    }


    @Override
    public int evaluate (Arena arena)
    {
        // TODO user-defined commands with parameters
        Map<String, Expression> savedVariables =
            new HashMap<String, Expression>();
        int retval = 0;
        if (myVariables.size() != 0)
        {
            for (int i=0; i<myVariables.size(); i++)
            {
                Variable variable = myVariables.get(i);
                Expression value = (Expression) myParameters.get(i);
                savedVariables.put(variable.getName(),
                                   arena.getVariable(variable.getName()));
                arena.setVariable(variable.getName(), value);
            }
        }
        retval = myHowToExpression.evaluate(arena);
        if (myVariables.size() != 0)
        {
            for (int i=0; i<myVariables.size(); i++)
            {
                Variable variable = myVariables.get(i);
                arena.setVariable(variable.getName(), savedVariables.get(variable.getName()));
            }
        }
        return retval;
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { myHowToExpression });
    }


    @Override
    public String toString ()
    {
        return String.format("%s(%s)", myName, myHowToExpression);
    }
}
