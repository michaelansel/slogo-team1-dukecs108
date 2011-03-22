package slogo.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import slogo.model.action.Action;
import slogo.model.action.DrawRoutines;
import slogo.model.arena.turtle.Turtle;
import slogo.util.Position;
import slogo.util.drawables2D.Line;
import slogo.view.gui.panel.subpanels.ArenaDraw;


public class AnimatedSwingDraw implements DrawRoutines
{
    public class TurtleGhost
    {
        public Position position = new Position();
        public File image = Turtle.DEFAULT_IMAGE;
        public boolean visible = true;
    }

    private Graphics2D myGraphics;
    private Map<Integer, TurtleGhost> myGhosts;
    private int myDrawSpeed;
    private JPanel myPanel;
    private BufferedImage myImage;
    private BufferedImage myState;


    public AnimatedSwingDraw (JPanel panel, int drawSpeed, BufferedImage tImg, BufferedImage state)
    {
        this((Graphics2D)panel.getGraphics());
        myPanel=panel;
    	myDrawSpeed=drawSpeed;
    	myImage=tImg;
    	myState=state;
    }


    private AnimatedSwingDraw (Graphics2D g)
    {
        myGraphics = g;
        myGhosts = new HashMap<Integer, TurtleGhost>();
    }


    @Override
    public void drawLine (Action action, Line line)
    {
    	int myCount=1;
    	myDrawSpeed=80;
    	BufferedImage[] slideShow=new BufferedImage[myDrawSpeed];

    	//Pre-Calculate change in X and rotation of our image
    	double dX=((line.x2-line.x1));
    	double dY=((line.y2-line.y1));
    	double rotation=Math.atan2(dY, dX);
//    	System.out.println("dx,dy: "+dX+", "+dY+", "+" Rotation: "+rotation);
    	dX=dX/myDrawSpeed;
    	dY=dY/myDrawSpeed;
    	 
    	//Create "slides" to animate over
    	for (int i=1;i<myDrawSpeed;i++){
    		//Clear Panel
    		BufferedImage slide = new BufferedImage(400,400,BufferedImage.TYPE_INT_ARGB);
    		Graphics2D slideGFX = (Graphics2D) slide.getGraphics();

    		//Calculate what to draw
    		Line l = new Line(line.x1, line.y1, line.x1+dX*i, line.y1+dY*i);

    		AffineTransform aTransform = new AffineTransform();
    		aTransform.translate((int)line.x1+dX*i, (int)line.y1+dY*i);
    		aTransform.rotate(rotation+Math.PI/2);
    		aTransform.translate(-myImage.getWidth()/2.0, -myImage.getHeight()/2.0);

    		//Draw
			slideGFX.drawImage(myState, 0, 0, null);
    		l.draw(slideGFX);
    		slideGFX.drawImage(myImage, aTransform, null);
    		i++;
    		slideShow[i-1]=slide;
    		slideGFX.dispose();
    	}

    	//Animate over the created slides
    	try {
    		
    		
    		while (myCount<myDrawSpeed) {
    			//Clear Panel
    			((ArenaDraw)myPanel).clear(myGraphics);

    			//Draw our current "slide"
    			myGraphics.drawImage(slideShow[myCount-1], 0, 0, myPanel);
    			myCount++;

    			//repaint what was drawn
    			myPanel.repaint();

    			//wait
    			Thread.sleep(1000/40);
    		}
    	} catch (InterruptedException ie) {
    	}
  	  
    }


    private TurtleGhost getGhost (int turtleID)
    {
        if (!myGhosts.containsKey(turtleID)) myGhosts.put(turtleID,
                                                          new TurtleGhost());
        return myGhosts.get(turtleID);
    }


    @Override
    public void walk (Action action, Point2D from, Point2D to)
    {
    	//Calculate
    	int myCount=1;
    	myDrawSpeed=80;
    	BufferedImage[] slideShow=new BufferedImage[myDrawSpeed];

    	//Pre-Calculate change in X and rotation of our image
    	double dX=to.getX()-from.getX();
    	double dY=to.getY()-from.getY();
    	double rotation=Math.atan2(dY, dX);
//    	System.out.println("dx,dy: "+dX+", "+dY+", "+" Rotation: "+rotation);
    	dX=dX/myDrawSpeed;
    	dY=dY/myDrawSpeed;
    	
    	//Create "slides" to animate over
    	for (int i=1;i<myDrawSpeed;i++){
    		//Clear Panel
    		BufferedImage slide = new BufferedImage(400,400,BufferedImage.TYPE_INT_ARGB);
    		Graphics2D slideGFX = (Graphics2D) slide.getGraphics();

            //Draw
	    	  int x = (int)(from.getX()+dX*i);
	    	  int y = (int)(from.getY()+dY*i);
	          
	        AffineTransform aTransform = new AffineTransform();
            aTransform.translate(x,y);
    		aTransform.rotate(rotation+Math.PI/2);
            aTransform.translate(-myImage.getWidth() / 2.0, -myImage.getHeight() / 2.0);

    		//Draw
			slideGFX.drawImage(myState, 0, 0, null);
            slideGFX.drawImage(myImage, aTransform, null);
    		i++;
    		slideShow[i-1]=slide;
    		slideGFX.dispose();
    	}
    	 

    	//Animate over the created slides
    	try {
    		
    		
    		while (myCount<myDrawSpeed) {
    			//Clear Panel
    			((ArenaDraw)myPanel).clear(myGraphics);

    			//Draw our current "slide"
    			myGraphics.drawImage(slideShow[myCount-1], 0, 0, myPanel);
    			myCount++;

    			//repaint what was drawn
    			myPanel.repaint();

    			//wait
    			Thread.sleep(1000/40);
    		}
    	} catch (InterruptedException ie) {
    	}

    	//        getGhost(action.getTurtleID()).position.setLocation(to);
    }


    @Override
    public void rotate (Action action, int degrees)
    {
        getGhost(action.getTurtleID()).position.setHeadingTo(degrees);
    }


    @Override
    public void disguise (Action action, File imageFile)
    {
        getGhost(action.getTurtleID()).image = imageFile;
    }


    @Override
    public void show (Action action)
    {
        getGhost(action.getTurtleID()).visible = true;
    }


    @Override
    public void hide (Action action)
    {
        getGhost(action.getTurtleID()).visible = false;
    }
}
