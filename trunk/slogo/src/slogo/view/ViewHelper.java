package slogo.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import slogo.model.arena.turtle.Turtle;

/**
 * Contains static methods and variables that multiple view classes use.
 */
public class ViewHelper {
	
    /**
     * Creates a new generic Panel with BorderLayout and borders (8,8)
     * @return default panel with borderlayout and borders of 8.
     */
    public static JPanel makeNewPanel(){
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(8,8));
            return panel;
    }
    

	/**
	 * Returns the BufferedImage for our turtle
	 * @param myName the name of the turtle
	 * @param myImage the turtle's image
	 * @param parent Component we can call an error message to in case
	 * of a bad turtle file.
	 * @return a BufferedImage of myFile, or in the case of a file error- the default.
	 */
	public static BufferedImage bufferTurtleImage(String myName, 
												  File myImage, 
												  Component parent) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(myImage);
		} catch (IOException e) {
			try {
				JOptionPane.showMessageDialog(parent,
						"Could not read image, default added For "+ myName +" instead.",
						"Unreadable image error!",
						JOptionPane.ERROR_MESSAGE);
				img = ImageIO.read(Turtle.DEFAULT_IMAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(parent,
						"The file is corrupt, please upload an image of the correct type.",
						"UNHANDLED FILE ERROR; PLEASE CHANGE IMAGE",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} 
		}

		return img;
	}

}