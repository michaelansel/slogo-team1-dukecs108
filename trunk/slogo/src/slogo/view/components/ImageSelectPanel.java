package slogo.view.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import slogo.model.arena.turtle.Turtle;

public class ImageSelectPanel extends JPanel implements IFileHolder{

	private static final long serialVersionUID = 1L;
	private File activeImageFile= Turtle.DEFAULT_IMAGE;
	private ActiveImagePanel imgPanel=new ActiveImagePanel(this);
	

	public ImageSelectPanel() {
		setLayout(new BorderLayout(8,8));
		add(imgPanel, BorderLayout.PAGE_START);
		
		//add the "Grid" of turtle images
		JPanel j=new JPanel(new GridLayout(3,5));
		add(j, BorderLayout.CENTER);
		//Load turtle images from folder, create buttons
	    File folder = new File("src/images/turtles/");
	    File[] listOfFiles = folder.listFiles();
	    for(File f: listOfFiles){
	    	ImageButton button=new ImageButton(f, this);		
	    	j.add(button);
	    }
	}
 
	public File getFile() {
		return activeImageFile;
	}

	public void setFile(File f) {
		activeImageFile=f;
		imgPanel.setActiveImage(f);
	}
	
}
