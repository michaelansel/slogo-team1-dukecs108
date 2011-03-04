package slogo.model.arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;


public class Arena implements Cloneable
{
    private Map<Integer, Turtle> myTurtles;
    private int myCurrentTurtleID;
    private Map<String, Expression> myVariables;
    private List<String> myHistory;


    /**
     * Create a new Arena with a default Turtle (Jim)
     */
    public Arena ()
    {
        this(new Turtle("Turtle Jim"));
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
        myCurrentTurtleID = addTurtle(turtle);
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
        newArena.myHistory.addAll(myHistory);
        newArena.myCurrentTurtleID = myCurrentTurtleID;

        return newArena;
    }


    /**
     * @see #addTurtle(Turtle)
     */
    public int addTurtle ()
    {
        return addTurtle(new Turtle("Turtle " + myTurtles.size()));
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

        // Get next unused turtle ID
        int nextID;
        if (myTurtles.isEmpty())
        {
            nextID = 0;
        }
        else
        {
            List<Integer> turtleIDs =
                new ArrayList<Integer>(myTurtles.keySet());
            Collections.sort(turtleIDs);
            nextID = turtleIDs.get(myTurtles.size() - 1) + 1;
        }

        setTurtle(nextID, turtle);
        return nextID;
    }


    /**
     * Add Turtle with ID turtleID to the Arena.
     * 
     * @param turtleID
     * @param turtle
     */
    private void setTurtle (int turtleID, Turtle turtle)
    {
        myTurtles.put(turtleID, turtle);
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
     * Change the current Turtle
     * 
     * @param turtleID
     */
    public void setCurrentTurtleID (Integer turtleID)
    {
        if (getTurtle(turtleID) == null) throw new IllegalArgumentException("Turtle with ID not found! ID=" +
                                                                            turtleID);
        myCurrentTurtleID = turtleID;
    }


    /**
     * @return ID of this Arena's currently selected Turtle
     */
    public int getCurrentTurtleID ()
    {
        return myCurrentTurtleID;
    }


    /**
     * @return This Arena's currently selected Turtle
     */
    public Turtle getCurrentTurtle ()
    {
        return myTurtles.get(myCurrentTurtleID);
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
     * set the current turtle in the arena by entering the desired turtle's name
     * primarily for command line funtionality
     * @param name
     * @throws TurtleException
     */
    public void setCurrTurtleByName(String name) throws TurtleException{
        if (!this.containsTurtleByName(name))
            throw new TurtleException("No turtle with that name exists");
        for (Entry<Integer, Turtle> entry: myTurtles.entrySet()){
            if (entry.getValue().getName().equals(name))
                this.setCurrentTurtleID(entry.getKey());
        }
        
    }
    
    
    /**
     * Retrieve a turtle in this arena by name
     * @param name
     * @return
     * @throws TurtleException
     */
    public Turtle getTurtleByName(String name) throws TurtleException{
        
           
        for (Entry<Integer, Turtle> entry: myTurtles.entrySet()){
            if (entry.getValue().getName().equals(name))
                return entry.getValue();
        }
        throw new TurtleException("No turtle with that name exists");
    }
    
    
    
    


    private boolean containsTurtleByName (String name)
    {
        for (Turtle t: myTurtles.values()){
            if (t.getName().equals(name)) return true;
        }
        return false;
    }
    
}
