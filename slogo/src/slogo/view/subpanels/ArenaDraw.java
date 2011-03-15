package slogo.view.subpanels;

import java.awt.Dimension; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.util.drawables2D.IDraw2D;
import slogo.util.drawables2D.Line;

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
	//private TurtleLevel turtlePanel;
	//private LinesLevel linePanel;
	public static Dimension myDimension=new Dimension(400, 400);
	private Map<Integer, Turtle> myTurtleMap;

	/**
	 * called when you instantiate the class. Sets everything up.
	 */
	public ArenaDraw(Arena a){
		myTurtleMap=a.getTurtleMap();
		setVisible(true);
		repaint();
		
		//turtlePanel=new TurtleLevel();
		//linePanel=new LinesLevel(myDimension);
		//add(turtlePanel);
		//add(linePanel);
	}

	/**
	 * called when the panel needs to be redrawn
	 */
	public void paintComponent(Graphics g) { 
		graphics = (Graphics2D)g;
		clear(graphics);
		
		if(myTurtleMap != null){
			for (Entry<Integer, Turtle> entry: myTurtleMap.entrySet()){
				Turtle curren=entry.getValue();
				
				BufferedImage img = null;
				try {
					img = ImageIO.read(curren.getImage());
				} catch (IOException e) {
					e.printStackTrace();
				}
				for (IDraw2D l: curren.getLinesToDraw(0)){ //redraws every line every time
					l.draw(graphics, myDimension);
				}
				g.drawImage(img, (int)curren.getPosition().getX()-img.getWidth()/2,
						(int)curren.getPosition().getY()-img.getHeight()/2, null);
			}
		}
		//add(turtlePanel);
		//add(linePanel);
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
