package slogo.model.arena;

import java.util.TreeMap;

import slogo.model.turtle.Turtle;


public class Arena
{
	private static TreeMap<Integer,String> commandHistory;
	
	public Arena()
	{
		commandHistory = new TreeMap<Integer,String>();
		
		//testing purposes
		commandHistory.put(0,"fd 50");
		
	}
	
    public Turtle getCurrentTurtle ()
    {
        return null;
    }
    public static TreeMap<Integer,String> getHistory()
    {
    	return commandHistory;
    }
}
