package slogo.view.subpanels;

import java.awt.Dimension; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.util.drawables2D.Line;
import slogo.util.interfaces.ICartesian2D;
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
	//private TurtleLevel turtlePanel;
	//private LinesLevel linePanel;
	public static Dimension myDimension=new Dimension(400, 400);
    public static Point2D ORIGIN = new Point2D.Double(myDimension.getWidth()/2,myDimension.getHeight()/2); //TODO this needs to not be static

	private Arena myArena;
	private boolean amAnimated;

	/**
	 * called when you instantiate the class. Sets everything up.
	 */
	public ArenaDraw(Arena a){
		myArena=a;
		setVisible(true);
		repaint();
		amAnimated = false;
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
		
		for (Turtle t : myArena.getTurtleMap().values()){
		    List<IDraw2D> draws = t.getDrawablesToDraw();
		    if (draws.size() != 0){
    		    for (IDraw2D l : draws){
    		        for (Point2D p : ((Line) l).splitToPoints()){
    		            
    //		            if (this.amAnimated){
    //	                    graphics.drawLine((int)((Line) l).getX1(), (int)((Line) l).getY1(),(int)p.getX(),(int) p.getY());
    //		                t.drawAtPoint(graphics, p);
    //		                //draw this graphics to panel
    //		                //delay somehow
    //		                graphics.
    //		            }
    //		            else{
    		                graphics.drawLine((int)p.getX(), (int)p.getY(),(int)p.getX(),(int) p.getY());
    //		            }
    		        }
    		    }
		    
//		    t.drawAtPoint(graphics, ((Line)draws.get(draws.size()-1)).getP2());
		    }
//		    else
    		    
		        t.draw(graphics);
//		    t.setCurrentDrawToEnd(); Due to Observe/observable, I THINK, there are multiple draws going on here. 
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

		myArena=a;

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
