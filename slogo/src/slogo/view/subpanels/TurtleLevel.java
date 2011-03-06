package slogo.view.subpanels;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import slogo.model.arena.turtle.Turtle;

public class TurtleLevel extends JComponent {
	Graphics2D graphics;
	ArrayList<Turtle> turtleList = new ArrayList<Turtle>();
	
	TurtleLevel(){
	}
	
	  public void paintComponent(Graphics g) {
		  	clear(g);
		    graphics = (Graphics2D)g;
		    for (Turtle t: turtleList){
				  BufferedImage im = null;
		          try {
						im = ImageIO.read(t.getImage());
					} catch (IOException e) {
						try {
							im = ImageIO.read(new File("src/images/default.png"));
						} catch (IOException e1) {
							System.out.println("Failed again. bad luck, guy.");
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
					graphics.drawImage(im, (int) t.getPosition().getX(), (int) t.getPosition().getY(), null);
		    }
		  }
	  
	  public void paintTurtles(ArrayList<Turtle> tList){
		  turtleList=tList;
	  }

		  /**
		   * clears the layer. called whenever we draw a new position.
		   * @param g
		   */
		  protected void clear(Graphics g) {
		    super.paintComponent(g);
		  }
}
