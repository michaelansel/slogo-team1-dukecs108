package slogo.view.subpanels;

import java.awt.BasicStroke; 
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import slogo.model.arena.turtle.Turtle;
import slogo.model.arena.turtle.qualities.positioning.Position;
import slogo.util.Line;

/**
 * Level where the lines are drawn and stored. Doesn't need to be
 * cleared, just leave what you put on. Will need to be edited/
 * changed/swapped for "undo" but animation should work all right
 * if we set other things up to handle it.
 * 
 * @author Dave
 *
 */
public class LinesLevel extends JComponent {
	Graphics2D graphics;
	Line myCurrentLine=new Line(new Point());
	public Dimension myDimension;
	
	
	public LinesLevel(Dimension d){
		myDimension = d;
	}

	public void paintComponent(Graphics g) {
		graphics = (Graphics2D)g;
		myCurrentLine.draw(graphics, myDimension);
	}

	/**
	 * Draws a line starting at x1,y1 and going to x2,y2. This line 
	 * will have width "width" and color "color".
	 */
	public void drawLine(Line l){
		myCurrentLine=l;
	}

	/**
	 * clears the layer. called whenever we draw a new position.
	 * @param g
	 */
	protected void clear(Graphics g) {
		super.paintComponent(g);
	}


}
