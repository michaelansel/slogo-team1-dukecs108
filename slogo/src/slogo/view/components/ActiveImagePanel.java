package slogo.view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import slogo.model.arena.turtle.Turtle;

public class ActiveImagePanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JFileChooser fc = new JFileChooser();
	private JLabel imageDisplay=new JLabel();
	private BufferedImage myResizedImage;



	public ActiveImagePanel(){
		setLayout(new BorderLayout(8,8));
		add(new HeaderLabel("Image: "), BorderLayout.PAGE_START);
		JPanel j = new JPanel(new BorderLayout(8,8));
		j.add(makeLoadButton("Load An Image"), BorderLayout.PAGE_START);
		j.add(new JLabel("Or choose one:"), BorderLayout.PAGE_END);
		add(j, BorderLayout.LINE_END);
		try {
			setDisplay(Turtle.DEFAULT_IMAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(imageDisplay, BorderLayout.CENTER);
	}
	
	private JButton makeLoadButton(String string) {
		JButton load = new JButton(string);
        load.addActionListener(this);
		return load;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal = fc.showOpenDialog(ActiveImagePanel.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        		File f = fc.getSelectedFile();
    			try {
    				setDisplay(f);
    				repaint();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this,
							"Sorry, the file you are trying to load could not be read as an image file. Please " +
							"try another file or select from the defaults.",
							"Unreadable image error!",
							JOptionPane.ERROR_MESSAGE);
				}
        }		
	}
	
	public void setDisplay(File f) throws IOException{
		BufferedImage i = ImageIO.read(f);
		BufferedImage image = resizeBufferedImage(50, 50, i);
		myResizedImage=image;
		remove(imageDisplay);
		imageDisplay=new JLabel(new ImageIcon(image));
		imageDisplay.setBorder(LineBorder.createGrayLineBorder());
		imageDisplay.setPreferredSize(new Dimension(53, 53));
		add(imageDisplay, BorderLayout.CENTER);
		doLayout();
	} 
	
	public BufferedImage getResizedImage(){
		return myResizedImage;
	}
	
	public void setActiveImage(File f){
			try {
				setDisplay(f);
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(this,
						"Sorry, the file you are trying to load could not be read as an image file. Please " +
						"try another file or select from the defaults.",
						"Unreadable image error!",
						JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/**
	 * Resizes a BufferedImage to fit the given Width and Height.
	 * Keeps the ratio of width/height the same so there's no
	 * change in shape.
	 */
	private BufferedImage resizeBufferedImage(double maxWidth, 
					double maxHeight, BufferedImage img){
		int imgHeight=img.getHeight();
		int imgWidth=img.getWidth();
		double dFactor;

		//Width relatively greater than height, Width=maxWidth, height adjusts
		//by the same factor as width to keep our list looking good.
		if ((imgWidth/maxWidth)>(imgHeight/maxHeight)){
			dFactor=maxWidth/imgWidth;
			imgHeight=(int) (imgHeight*dFactor);
			imgWidth=(int) maxWidth;
		}
		//Height relatively greater than width, Height=maxHeight, width adjusts
		//by the same factor as width to keep our list looking good.
		else if ((imgHeight/maxHeight)>(imgWidth/maxWidth)){
			dFactor=maxHeight/imgHeight;
			imgWidth=(int) (imgWidth*dFactor);
			imgHeight=(int) maxHeight;
		}
		//There is no difference. Set both to max.
		else {
			imgWidth= (int) maxHeight;
			imgHeight= (int) maxWidth;
		}
		//Makes new BufferedImage, of type "ARGB" where A is alpha, so list
		//displays transparency correctly
		BufferedImage resizedImage = new BufferedImage(imgWidth, imgHeight, 
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = resizedImage.createGraphics();
		graphics.drawImage(img, 0, 0, imgWidth, imgHeight, null);
		graphics.dispose();
		return resizedImage;
	}
	
}
