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
import slogo.model.expression.Constant;
import slogo.model.expression.Expression;
import slogo.model.expression.Variable;


/**
 * @author Michael Ansel
 */
public class UserCommand extends Command
{

    private String myName;
    private List<Variable> myVariables;
    private List<Object> myParameters;


    public UserCommand (String commandName)
    {
        this(commandName,
             Arrays.asList(new Variable[] {}),
             Arrays.asList(new Object[] {}));
    }


    public UserCommand (String commandName,
                        List<Variable> variables,
                        List<Object> parameters)
    {
        myName = commandName;
        myVariables = variables;
        myParameters = parameters;
    }


    @Override
    public int evaluate (Arena arena)
    {
        Map<String, Expression> savedVariables =
            new HashMap<String, Expression>();
        int retval = 0;
        if (myVariables.size() != 0)
        {
            // Save variable values and override
            for (int i=0; i<myVariables.size(); i++)
            {
                Variable variable = myVariables.get(i);
                Expression value = (Expression) myParameters.get(i);
                savedVariables.put(variable.getName(),
                                   arena.getVariable(variable.getName()));
                arena.setVariable(variable.getName(), new Constant(value.evaluate(arena)));
            }
        }
        
        retval = arena.getUserCommand(myName).evaluate(arena);
        
        if (myVariables.size() != 0)
        {
            // Restore old variable values
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
        return Arrays.asList(new Expression[] {});
    }


    @Override
    public String toString ()
    {
        return String.format("%s(?)", myName);
    }
}
