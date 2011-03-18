package slogo.view.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
		if(turtleList != null){
			Turtle curren = turtleList.get(0);
            BufferedImage im = null;
            try {
				im = ImageIO.read(new File("src/default.png"));
			} catch (IOException e) {
				//Catches so I don't have to worry about IO exceptions messing with the "paintComponent" super
				System.out.println("FAIL");
				e.printStackTrace();
			}
			System.out.println(new Dimension ((int) curren.getPosition().getX(),(int) curren.getPosition().getY()));
			g.drawImage(im, (int)cur.getPosition().getX(),(int)cur.getPosition().getY(),
					null);
		}
		/**/
	}
	
}