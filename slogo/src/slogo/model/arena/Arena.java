package slogo.model.arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import slogo.model.arena.draw.ArenaDraw;
import slogo.model.arena.turtle.Turtle;

public class Arena
{
	public static int ID_NUMBER;
	
    private static ArrayList<Turtle> myTurtleList;
    private Map<String, String> myVariables;
    private static Map<Integer, String> myHistory;
    private static int currTurtle;
    ArenaDraw myDraw = new ArenaDraw(myTurtleList);
    
    public Arena(int ID_NUM)
    {
    	ID_NUMBER=ID_NUM;
    	myTurtleList = new ArrayList<Turtle>();
        myVariables = new HashMap<String,String>();
        myHistory = new HashMap<Integer,String>();
        currTurtle = 0;
    }
    
    public Arena (int ID_NUM, ArrayList<Turtle> turtlelist,
                  Map<String, String> variables,
                  Map<Integer, String> history,
                  int currturtle)
    {
    	ID_NUMBER=ID_NUM;
        myTurtleList = turtlelist;
        myVariables = variables;
        myHistory = history;
        currTurtle = currturtle;
    }

    public Arena clone(){
        return new Arena(ID_NUMBER, myTurtleList, myVariables, myHistory, currTurtle);
    }
    
    
    public void addTurtle(){
        addTurtle(new Turtle("Turtle " + myTurtleList.size()));
    }
    
    public void addTurtle (Turtle turtle)
    {
        myTurtleList.add(turtle);
        currTurtle = myTurtleList.size()-1;
    }

    public Turtle getLastTurtle()
    {
        return getTurtle(myTurtleList.size()-1);
    }

    public Turtle getTurtle (int index)
    {
        return myTurtleList.get(index);
    }

    public void setTurtleList (ArrayList<Turtle> myTurtles)
    {
        myTurtleList = myTurtles;
    }

    public static ArrayList<Turtle> getTurtleList ()
    {
        return myTurtleList;
    }

    public void setVariables (Map<String, String> variables)
    {
        myVariables = variables;
    }

    public Map<String, String> getVariables ()
    {
        return myVariables;
    }

    public void setHistory (Map<Integer, String> history)
    {
        myHistory = history;
    }

    public static Map<Integer, String> getHistory ()
    {
        return myHistory;
    }

    public void setCurrentTurtle (Integer cTurtle)
    {
        currTurtle = cTurtle;
    }
    public static int getCurrentIndex ()
    {
    	return currTurtle;
    }
    public Turtle getCurrentTurtle ()
    {
        return myTurtleList.get(currTurtle);
    }

	public int getID() {
		return ID_NUMBER;
	}

	public void updatePanel(){
		myDraw.drawSpace(myTurtleList);
	}
	public JPanel getPanel(){
		return new ArenaDraw(myTurtleList);
	}
	
}
