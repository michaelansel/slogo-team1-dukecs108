package slogo.view.components;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ImageButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File myImageFile;
	private IFileHolder holder;
	
	public ImageButton(File f, IFileHolder h){
		myImageFile=f;
		holder=h;
		BufferedImage i=null;
		
		
		try {
			i = ImageIO.read(f);
		} catch (IOException e) {
		}
		
		
		BufferedImage image = resizeBufferedImage(20, 20, i);
		setIcon(new ImageIcon(image));
		
		
    	addActionListener(
				new ActionListener(){ 
					public void actionPerformed (ActionEvent evt){
						holder.setFile(myImageFile);
					}
				});
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
