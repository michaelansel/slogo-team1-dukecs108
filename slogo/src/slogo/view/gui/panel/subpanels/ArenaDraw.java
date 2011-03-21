package slogo.view.gui.panel.subpanels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import slogo.model.action.Action;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.util.interfaces.IDraw2D;
import slogo.view.AnimatedSwingDraw;
import slogo.view.SwingDraw;

/**
 * Draws an Arena Object. This implementation currently draws all
 * actions immediately rather than allowing for animation.
 * 
 * @author Dave
 *
 */
public class ArenaDraw extends JPanel{
	private static final long serialVersionUID = 1L;
	private Graphics2D graphics;
	public static Dimension myDimension=new Dimension(400, 400);
	public static Point2D ORIGIN = new Point2D.Double(myDimension.getWidth()/2,myDimension.getHeight()/2);
    private Arena myArena;
    private boolean myIsBusy;
    private int myLastActionID;

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
		myArena = a;
		setVisible(true);
		myLastActionID=0;
		//turtlePanel=new TurtleLevel();
		//linePanel=new LinesLevel(myDimension);
		//add(turtlePanel);
		//add(linePanel);
	}

	/**
	 * called when the panel needs to be redrawn
	 */
	public void paintComponent(Graphics g) {
		if (!myIsBusy){
			myIsBusy=true;
        System.out.println("[Drawing]");
        graphics = (Graphics2D) g;
        clear(graphics);
        if(myLastActionID>myArena.getActions().size()){
        	myLastActionID=0;
        }
        SwingDraw state=new SwingDraw(graphics);
        for (Action action : myArena.getActions().subList(0, myLastActionID)){
            action.draw(state);
        }
        AnimatedSwingDraw swingDraw = new AnimatedSwingDraw(this, 20);
        for (Action action : myArena.getActions().subList(myLastActionID, myArena.getActions().size())){
            action.draw(swingDraw);
        }
        myLastActionID=myArena.getActions().size();

        for (Entry<Integer, Turtle> entry : myArena.getTurtleMap().entrySet())
        {
            Turtle curren = entry.getValue();
            
            if(!curren.isVisible()) continue;
            
            //Load Turtle Image and action list
            BufferedImage img = null;
            try {
                img = ImageIO.read(curren.getImage());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Draw Turtle at correct position. Notice this is after for loop- so we can draw and
            //delete turtle as many times as we need to during animation, if/when added.
            AffineTransform aTransform = new AffineTransform();
            aTransform.translate((int) curren.getPosition().getX(),
                                 (int) curren.getPosition().getY());
            aTransform.rotate(-((curren.getPosition().getHeading()) * Math.PI / 180.0) +
                              Math.PI / 2);
            aTransform.translate(-img.getWidth() / 2.0, -img.getHeight() / 2.0);

            graphics.drawImage(img, aTransform, null);
        }
        System.out.println("[/Drawing]");
			myIsBusy=false;
		}
    }
	
	/**
	 * clears the panel
	 * @param g
	 */
	private void clear(Graphics g){
		super.paintComponent(g);
	}
	
	{ //Bracketed so it can be folded in Eclipse
//	/**
//	 * When something is updated, redraws as necessary.
//	 * @param a the Arena you're updating
//	 */
//	public void drawSpace(Arena a){
//
//		myTurtleMap=a.getTurtleMap();
//
//		//Repaints turtles every call
//		//turtlePanel.paintTurtles(turtleList);
//
//		//Paints a line if it's new (ie: not in the treeset as 
//		//members of treeset must be unique)
//		//for(Turtle t: turtleList){
//		//	List<Line> list = t.getLines();
//		//	for (Line l: list){
//		//		if (lineSet.add(l)){
//		//			linePanel.drawLine(l);
//		//		}
//		//	}
//		//}
//	}


	//	public void updateTurtle(int turtleId)
	//{
	//MyTurtleList = slogo.model.arena.Arena.getTurtleList();
	//if(MyTurtleList != null)
	//cur = MyTurtleList.get(turtleId);
	//}
	}
}
