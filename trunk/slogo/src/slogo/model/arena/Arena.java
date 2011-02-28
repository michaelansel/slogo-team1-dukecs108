package slogo.model.arena;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.arena.turtle.Turtle;

public class Arena
{

    private List<Turtle> myTurtleList;
    private Map<String, String> myVariables;
    private Map<Integer, String> myHistory;
    private Turtle currTurtle;
    private Graphics2D myGraphics;
    
    public Arena(Graphics g){
        setTurtleList(new ArrayList<Turtle>());
        setVariables(new HashMap<String, String> ());
        setHistory(new HashMap<Integer, String> ());
        setGraphics((Graphics2D) g);
    }

    public void addTurtle(){
        addTurtle(new Turtle());
    }
    
    private void addTurtle (Turtle turtle)
    {
        myTurtleList.add(turtle);
        currTurtle = getLastTurtle();
        
    }

    private Turtle getLastTurtle ()
    {
        return getTurtle(myTurtleList.size()-1);
    }

    private Turtle getTurtle (int index)
    {
        return myTurtleList.get(index);
    }

    public void setTurtleList (List<Turtle> myTurtles)
    {
        this.myTurtleList = myTurtles;
    }

    public List<Turtle> getTurtleList ()
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

    public Map<Integer, String> getHistory ()
    {
        return myHistory;
    }

    public void setCurrentTurtle (Turtle currTurtle)
    {
        this.currTurtle = currTurtle;
    }

    public Turtle getCurrentTurtle ()
    {
        return currTurtle;
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
