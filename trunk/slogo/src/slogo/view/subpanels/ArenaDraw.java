package slogo.view.subpanels;

import java.awt.Dimension;  
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.util.interfaces.IDraw2D;

/**
 * Drawing base, send your instructions here, it calls others as
 * needed.
 * 
 * @author Dave
 *
 */
public class ArenaDraw extends JPanel{
	private static final long serialVersionUID = 1L;
	private Graphics2D graphics;
	public static Dimension myDimension=new Dimension(400, 400);
	public static Point2D ORIGIN = new Point2D.Double(myDimension.getWidth()/2,myDimension.getHeight()/2);
	private Map<Integer, Turtle> myTurtleMap;

	//Notice: While not implemented, we would have liked to draw this stuff on two separate levels, to
	//Allow for good animation without redrawing lines to our animable one every time. As it stands
	//we will have to make a container for our currentTurtleImage if he's not in his final
	//position, or redraw all turtles every time (which is doable but not desired)
	
	//The following two panels were left in for that reason, and in our methods you will see what
	//we would have done had it been possible. Unfortunately we could not figure it out.
	//private TurtleLevel turtlePanel;
	//private LinesLevel linePanel;
	
	/**
	 * called when you instantiate the class. Sets everything up.
	 */
	public ArenaDraw(Arena a){
		myTurtleMap=a.getTurtleMap();
		setVisible(true);
		
		//turtlePanel=new TurtleLevel();
		//linePanel=new LinesLevel(myDimension);
		//add(turtlePanel);
		//add(linePanel);
	}

	/**
	 * called when the panel needs to be redrawn
	 */
	public void paintComponent(Graphics g) {
		System.out.println("[Drawing]");
		graphics = (Graphics2D)g;
		clear(graphics);

		if(myTurtleMap != null){
			for (Entry<Integer, Turtle> entry: myTurtleMap.entrySet()){
				Turtle curren=entry.getValue();
				
				//Load Turtle Image and action list
				BufferedImage img = null;
				try {
					img = ImageIO.read(curren.getImage());
				} catch (IOException e) {
					e.printStackTrace();
				}
				List<IDraw2D> actionSet=curren.getAllDrawables();

				//Draw Actions
				for (IDraw2D action: actionSet){
					action.draw(graphics);
				}
				
				//Draw Turtle at correct position. Notice this is after for loop- so we can draw and
				//delete turtle as many times as we need to during animation, if/when added.
                AffineTransform aTransform = new AffineTransform();
                aTransform.translate((int) curren.getPosition().getX(), (int) curren.getPosition().getY());
                aTransform.rotate( -((curren.getPosition().getHeading())*Math.PI / 180.0)+Math.PI/2);
                aTransform.translate(-img.getWidth() / 2.0, -img.getHeight() / 2.0);
                
				graphics.drawImage(img, aTransform, null);
				
				
			}
		}
		System.out.println("[/Drawing]");
	}
	
	/**
	 * clears the panel
	 * @param g
	 */
	private void clear(Graphics g){
		super.paintComponent(g);
	}
	
	/**
	 * When something is updated, redraws as necessary.
	 * @param a the Arena you're updating
	 */
	public void drawSpace(Arena a){

		myTurtleMap=a.getTurtleMap();

		//Repaints turtles every call
		//turtlePanel.paintTurtles(turtleList);

		//Paints a line if it's new (ie: not in the treeset as 
		//members of treeset must be unique)
		//for(Turtle t: turtleList){
		//	List<Line> list = t.getLines();
		//	for (Line l: list){
		//		if (lineSet.add(l)){
		//			linePanel.drawLine(l);
		//		}
		//	}
		//}
	}


	//	public void updateTurtle(int turtleId)
	//{
	//MyTurtleList = slogo.model.arena.Arena.getTurtleList();
	//if(MyTurtleList != null)
	//cur = MyTurtleList.get(turtleId);
	//}
}
