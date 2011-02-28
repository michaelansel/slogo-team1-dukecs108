package slogo.model.arena;

import java.util.TreeMap;

import slogo.model.turtle.Turtle;


public class Arena
{
	private TreeMap<Integer,String> commandHistory;
	
	public Arena()
	{
		commandHistory = new TreeMap<Integer,String>();
	}
	
    public Turtle getCurrentTurtle ()
    {
        return null;
    }
    public TreeMap<Integer,String> getHistory()
    {
    	return commandHistory;
    }
}
