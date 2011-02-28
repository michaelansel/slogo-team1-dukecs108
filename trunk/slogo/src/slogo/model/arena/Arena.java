package slogo.model.arena;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import slogo.model.arena.turtle.Turtle;

public class Arena
{

    private static ArrayList<Turtle> myTurtleList;
    private Map<String, String> myVariables;
    private static Map<Integer, String> myHistory;
    private static int currTurtle;
    private Graphics2D myGraphics;

    public Arena(Graphics g){
        setTurtleList(new ArrayList<Turtle>());
        setVariables(new HashMap<String, String> ());
        setHistory(new HashMap<Integer, String> ());
        setGraphics((Graphics2D) g);
    }
    public Arena()
    {
    	myTurtleList = new ArrayList<Turtle>();
        myVariables = new HashMap<String,String>();
        myHistory = new HashMap<Integer,String>();
        currTurtle = 0;
    }
    public Arena (ArrayList<Turtle> turtlelist,
                  Map<String, String> variables,
                  Map<Integer, String> history,
                  int currturtle,
                  Graphics2D graphics)
    {
        myTurtleList = turtlelist;
        myVariables = variables;
        myHistory = history;
        currTurtle = currturtle;
        myGraphics = graphics;
    }

    public Arena clone(){
        return new Arena(myTurtleList, myVariables, myHistory, currTurtle, myGraphics);
    }
    
    
    public void addTurtle(){
        addTurtle(new Turtle("Turtle " + myTurtleList.size()));
    }
    
    public static void addTurtle (Turtle turtle)
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
        this.myTurtleList = myTurtles;
    }

    public static ArrayList<Turtle> getTurtleList ()
    {
        return myTurtleList;
    }

    public void setVariables (Map<String, String> myVariables)
    {
        this.myVariables = myVariables;
    }

    public Map<String, String> getVariables ()
    {
        return myVariables;
    }

    public void setHistory (Map<Integer, String> myHistory)
    {
        this.myHistory = myHistory;
    }

    public static Map<Integer, String> getHistory ()
    {
        return myHistory;
    }

    public void setCurrentTurtle (Integer currTurtle)
    {
        this.currTurtle = currTurtle;
    }
    public static int getCurrentIndex ()
    {
    	return currTurtle;
    }
    public Turtle getCurrentTurtle ()
    {
        return myTurtleList.get(currTurtle);
    }

    public void setGraphics (Graphics2D myGraphics)
    {
        this.myGraphics = myGraphics;
    }

    public Graphics2D getGraphics ()
    {
        return myGraphics;
    }
    
    
}
