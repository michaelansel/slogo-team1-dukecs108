package slogo.view.panel;

import java.awt.Graphics;
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
	
	public TurtlePanel()
	{
		super();
	}
	public void updateTurtle(int turtleId)
	{
		File imageFile = turtleList.get(turtleId).getImage();
		
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
	}
}