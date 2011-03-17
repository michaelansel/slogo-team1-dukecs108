package slogo.view.subpanels;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import slogo.model.arena.turtle.Turtle;

public class TurtleLevel extends JComponent {
	ArrayList<Turtle> turtleList = new ArrayList<Turtle>();
	
	TurtleLevel(){
	}
	
	  public void paintComponent(Graphics g) {
		  	//clear(g);

		    super.paintComponent(g);
		  
		  
		  	//Runs for each turtle, grabs image, rotates by heading, draws.
		    for (Turtle t: turtleList){
				  BufferedImage img = null;
				  try {
						img = ImageIO.read(t.getImage());
					} catch (IOException e) {
						try {
							img = ImageIO.read(new File("src/images/default.png"));
						} catch (IOException e1) {
							System.out.println("Failed again. bad luck, guy.");
							e1.printStackTrace();
						}
						e.printStackTrace();
					}

				    AffineTransform at = new AffineTransform();
		            at.translate((int) t.getPosition().getX(), (int) t.getPosition().getY());
					at.rotate( Math.PI/4 );
					at.translate(-img.getWidth() / 2.0, -img.getHeight() / 2.0);

				    Graphics2D graphics = (Graphics2D)g;
					graphics.drawImage(img, at, null);
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
