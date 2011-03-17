package slogo.view.resources;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;

/**
 * A JList with the capability to display images. Requires
 * ImageListCellRenderer to work properly.
 * @author Dave
 */
public class ImageList extends JList{
	private static final long serialVersionUID = 1L;
	private double maxImageWidth = 30.0;
	private double maxImageHeight = 30.0;
	private List<JLabel> myLabelList = new ArrayList<JLabel>();
	private int JLABEL_ORIENTATION = SwingConstants.LEFT;

	public ImageList(){
		this(new ArrayList<JLabel>());
	}
	public ImageList(List<JLabel> jLabelList){
		setCellRenderer(new ImageListCellRenderer());
		setCells(jLabelList);
	}
	public ImageList(Map<String, BufferedImage> m){
		setCellRenderer(new ImageListCellRenderer());
		setCells(m);
	}

	/**
	 * Updates this list to jLabelList. WARNING: Current list will
	 * be destroyed if you call this, so make certain your new list
	 * contains all the cells you want to render.
	 * @param jLabelList the list of JLabels you want to render
	 */
	public void setCells(List<JLabel> list){
		myLabelList=list;
		
		Object[] listCells = new Object[list.size()];
		int count = 0;
		for (JLabel label: list){
			JFrame frame = new JFrame();
			frame.getContentPane().add(label);
			listCells[count]=frame.getContentPane();
			count++;
		}
		setListData(listCells);
	} 
	/**
	 * Updates this list to contain the data stored in the Map. Useful
	 * if you just want the default layout for your list items. The default
	 * is "[IMAGE] String". By default the image will be resized to 30x30.
	 * @param m the map you want to create your list from
	 */
	public void setCells(Map<String, BufferedImage> m){
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		
		for(Entry<String, BufferedImage> entry: m.entrySet()){
			BufferedImage image = entry.getValue();
			if (image.getWidth()>maxImageWidth || image.getHeight()>maxImageHeight){
				image = resizeBufferedImage(maxImageWidth, maxImageHeight, image);
			}
			JLabel j = new JLabel(entry.getKey(), new ImageIcon(image), 
					JLABEL_ORIENTATION);
			labelList.add(j);
		}
		
		setCells(labelList);
	}
	
	/**
	 * Changes the maxWidth and maxHeight of your BufferedImage (if you
	 * are using setCells(Map...) from the default of 30x30.
	 * @param width the new max Width
	 * @param height the new max Height
	 */
	public void setMaxImageSize(int width, int height){
		maxImageWidth=(double)width;
		maxImageHeight=(double)height;
	}
	
	/**
	 * Tells the list to display your image first. (only applicable if
	 * you are using setCells(Map<String, BufferedImage>)
	 */
	public void setImageFirst(){
		JLABEL_ORIENTATION=SwingConstants.LEFT;
		setCells(myLabelList);
	}
	
	/**
	 * Tells the list to display your String first, if you are using
	 * setCells(Map<String, BufferedImage>)
	 */
	public void setStringFirst(){
		JLABEL_ORIENTATION=SwingConstants.RIGHT;
		setCells(myLabelList);
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
