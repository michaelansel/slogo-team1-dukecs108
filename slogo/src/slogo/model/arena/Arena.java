package slogo.model.arena;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import slogo.model.action.Action;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;
import slogo.view.gui.panel.subpanels.ArenaDraw;


/**
 * Top-level state container for a defined view of the world (e.g. a tab in the
 * application). Contains Turtles, defined variables and user commands, and a
 * history of evaluated expressions.
 * 
 * @author Julian Genkins
 * @author Michael Ansel
 */
public class Arena extends Observable implements Cloneable, Observer
{
    private List<String> myHistory;
    private List<Turtle> mySelectedTurtles;
    private Map<Integer, Turtle> myTurtles;
    private Map<String, Expression> myUserCommands;
    private Map<String, Expression> myVariables;
    private int myNextTurtleID;
    private List<Action> myActions;


    /**
     * Create a new Arena with a default Turtle
     */
    public Arena ()
    {
        this(new Turtle());
    }


    /**
     * Create a new Arena with the given Turtle
     * 
     * @param turtle First Turtle in the new Arena
     */
    public Arena (Turtle turtle)
    {
        myTurtles = new HashMap<Integer, Turtle>();
        myVariables = new HashMap<String, Expression>();
        myHistory = new ArrayList<String>();
        mySelectedTurtles = new ArrayList<Turtle>();
        myUserCommands = new HashMap<String, Expression>();
        myNextTurtleID = 0;
        addTurtle(turtle);
    }


    /**
     * Add the given expression to the expression history
     * 
     * @param historyEntry Entry to add to the history
     * @return Index of added history entry
     */
    public int addHistoryEntry (String historyEntry)
    {
        myHistory.add(historyEntry);
        return myHistory.size() - 1;
    }


    /**
     * @see #addTurtle(Turtle)
     */
    public int addTurtle ()
    {
        return addTurtle(new Turtle());
    }


    private int addTurtle (int id)
    {
        addTurtle(id, new Turtle());
        return id;
    }


    private int addTurtle (int id, Turtle turtle)
    {
        myTurtles.put(id, turtle);
        turtle.addObserver(this);

        // Observable.setChanged()
        setChanged();

        // Select the first Turtle added to the Arena
        if (mySelectedTurtles.isEmpty()) selectTurtles(turtle);

        return id;
    }


    /**
     * Add a Turtle to the Arena and return its ID.
     * 
     * @param turtle Turtle to add to Arena
     * @return ID of Turtle
     */
    public int addTurtle (Turtle turtle)
    {
        if (turtle == null) throw new IllegalArgumentException("Cannot add a null Turtle!");

        int newTurtleID = myNextTurtleID;
        myNextTurtleID++;

        addTurtle(newTurtleID, turtle);

        return newTurtleID;
    }


    public void addUserCommand (String commandName, Expression howToExpression)
    {
        myUserCommands.put(commandName, howToExpression);
    }


    /**
     * Create a deep clone of the current Arena
     */
    public Arena clone ()
    {
        Arena newArena;
        try
        {
            newArena = (Arena) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
            return null;
        }

        for (Map.Entry<Integer, Turtle> entry : myTurtles.entrySet())
        {
            int turtleID = entry.getKey();
            Turtle turtle = entry.getValue();
            newArena.myTurtles.put(turtleID, turtle.clone());
        }
        newArena.myVariables.putAll(myVariables);
        newArena.myUserCommands.putAll(myUserCommands);
        newArena.myHistory.addAll(myHistory);
        newArena.mySelectedTurtles = mySelectedTurtles;

        return newArena;
    }


    /**
     * Evaluate the Expression tree in the context of this Arena for all
     * selected Turtles.
     * 
     * @param expression Expression to evaluate in this Arena
     * @return numeric result of Expression evaluation (see language definition
     *         for specifics)
     */
    public int evaluateExpression (Expression expression)
    {
        int retval = 0;
        for (Turtle turtle : new ArrayList<Turtle>(getSelectedTurtles()))
            retval = expression.evaluate(this, turtle);
        return retval;
    }
    
    public List<Action> getActions()
    {
        return Collections.unmodifiableList(new ArrayList<Action>(myActions));
    }


    public Point2D getCenter ()
    {
        //TODO make this not static
        return ArenaDraw.ORIGIN;
    }


    /**
     * @return This Arena's currently selected Turtle
     */
    @Deprecated
    public Turtle getCurrentTurtle ()
    {
        return myTurtles.get(mySelectedTurtles.get(0));
    }


    /**
     * Retrieve the history entry at the given index
     * 
     * @param index
     * @return History entry at given index
     */
    public String getHistoryEntry (int index)
    {
        return myHistory.get(index);
    }


    /**
     * @return ID of this Arena's currently selected Turtle
     */
    public List<Turtle> getSelectedTurtles ()
    {
        return mySelectedTurtles;
    }


    /**
     * Retrieve a Turtle from the Arena using the given ID
     * 
     * @param turtleID ID of Turtle in this Arena
     * @return Turtle with given ID, null if not found
     */
    public Turtle getTurtle (int turtleID)
    {
        return myTurtles.get(turtleID);

    }


    /**
     * Retrieve the entire turtle map from the arena
     * 
     * @return Turtle map
     */
    public Map<Integer, Turtle> getTurtleMap ()
    {
        return myTurtles;
    }


    public Expression getUserCommand (String commandName)
    {
        return myUserCommands.get(commandName);
    }


    /**
     * Retrieve the named variable's value
     * 
     * @param variableName Name of the variable to retrieve
     * @return Value of the variable (null if not found)
     */
    public Expression getVariable (String variableName)
    {
        return myVariables.get(variableName);
    }


    /**
     * Removes the turtle with the given ID from the Arena.
     * 
     * @return true if a Turtle with the given ID was removed from the Arena;
     *         otherwise, false.
     */
    public boolean removeTurtle (int id)
    {
        setChanged();

        // Unselect Turtle prior to removal
        if (myTurtles.containsKey(id)) mySelectedTurtles.remove(myTurtles.get(id));

        // Remove the Turtle
        return myTurtles.remove(id) != null;
    }


    /**
     * Removes the given Turtle from the Arena.
     * 
     * @return true if a Turtle with the given ID was removed from the Arena;
     *         otherwise, false.
     */
    public boolean removeTurtle (Turtle turtle)
    {
        setChanged();

        // Remove the first matching Turtle
        for (Map.Entry<Integer, Turtle> entry : myTurtles.entrySet())
            if (entry.getValue() == turtle) return removeTurtle(entry.getKey());
        return false;
    }


    /**
     * Select Turtles by ID
     * 
     * @param turtleIDs Turtles to select
     */
    public void selectTurtles (Integer ... turtleIDs)
    {
        for (Integer turtleID : turtleIDs)
            if (getTurtle(turtleID) == null) addTurtle(turtleID);

        mySelectedTurtles.clear();
        for (Integer turtleID : turtleIDs)
            mySelectedTurtles.add(getTurtle(turtleID));
    }


    public void selectTurtles (List<Integer> turtleIds)
    {
        selectTurtles((Integer[]) turtleIds.toArray(new Integer[turtleIds.size()]));
    }


    /**
     * Select Turtles
     * 
     * @param turtleIDs Turtles to select
     */
    public void selectTurtles (Turtle ... turtles)
    {
        mySelectedTurtles.clear();
        for (Turtle turtle : turtles)
        {
            if (turtle == null) throw new IllegalArgumentException("Can't select a null Turtle!");
            // Add the Turtle to the Arena if not already a member
            if (!myTurtles.containsValue(turtle)) addTurtle(turtle);
            // Select the Turtle
            mySelectedTurtles.add(turtle);
        }
    }


    @Deprecated
    public void setCurrentTurtle (int id)
    {
        selectTurtles(id);
    }


    /**
     * Let the named variable contain the given value.
     * 
     * @param variableName Name of variable
     * @param variableValue Value to be stored in the named variable
     */
    public void setVariable (String variableName, Expression variableValue)
    {
        myVariables.put(variableName, variableValue);
    }


    @Override
    public void update (Observable turtle, Object o)
    {
        Action action = (Action) o;
        for (Map.Entry<Integer, Turtle> entry : myTurtles.entrySet())
            if (entry.getValue() == turtle) action.setTurtleID(entry.getKey());
        myActions.add(action);
        setChanged();
        notifyObservers();
    }

}
