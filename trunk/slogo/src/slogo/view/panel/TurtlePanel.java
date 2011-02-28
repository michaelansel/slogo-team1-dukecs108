package slogo.view.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import slogo.model.arena.turtle.Turtle;

/**
 * @author Austin Benesh
 *
 */
public class TurtlePanel extends JPanel
{ 
	private ArrayList<Turtle> turtleList;
	private Turtle cur;
	public TurtlePanel()
	{
		super();
	}
	public void updateTurtle(int turtleId)
	{
		turtleList = slogo.model.arena.Arena.getTurtleList();
		if(turtleList != null)
			cur = turtleList.get(turtleId);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		/*	For debugging purposes */
		updateTurtle(0);
		if(turtleList != null)
			g.drawRect((int)cur.getPosition().getX()-10,(int)cur.getPosition().getY()-10,(int)cur.getPosition().getX()+10,(int)cur.getPosition().getY()+10);
		/**/
	}
}