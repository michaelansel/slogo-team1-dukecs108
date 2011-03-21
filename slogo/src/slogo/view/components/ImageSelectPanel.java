package slogo.view.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;

import slogo.model.arena.turtle.Turtle;

public class ImageSelectPanel extends JPanel implements IFileHolder{

	private static final long serialVersionUID = 1L;
	private File activeImageFile= Turtle.DEFAULT_IMAGE;
	private ActiveImagePanel imgPanel=new ActiveImagePanel();
	

	public ImageSelectPanel() {
		setLayout(new BorderLayout(8,8));
		add(imgPanel, BorderLayout.PAGE_START);
		
		//add the "Grid" of turtle images
		JPanel j=new JPanel(new GridLayout(0,5));
		add(j, BorderLayout.CENTER);
		//Load turtle images from folder, create buttons
	    File folder = new File("src/images/turtles/");
	    File[] listOfFiles = folder.listFiles();
	    int count=1;
	    for(File f: listOfFiles){
		    	try{
			    	ImageButton button=new ImageButton(f, this);		
			    	j.add(button);
			    	count++;
		    	}
		    	catch (Exception e){
		    		//If the file is not an image, ie we can't open
		    		//inputstream or something- ignore it.
	    	}
	    }
	}
 
	public BufferedImage getResizedImage() {
		return imgPanel.getResizedImage();
	}

	public void setFile(File f) {
		activeImageFile=f;
		imgPanel.setActiveImage(f);
	}
	
}
