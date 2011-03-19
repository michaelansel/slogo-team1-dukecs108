package slogo.view.components;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ImageSelectGrid extends JPanel implements ActionListener {
	IFileHolder myOwner;
	public ImageSelectGrid(List<File> l, IFileHolder i){
		myOwner=i;
		setLayout(new GridLayout(0,5));
		for (File f: l){
			JButton button=makeImageButton(f);
			button.addActionListener(this);
		}
	}
	private JButton makeImageButton(File f) {
		try {
			BufferedImage i = ImageIO.read(f);
			BufferedImage image = resizeBufferedImage(50, 50, i);
			JButton j = new JButton(new ImageIcon(image));
			j.setBorder(LineBorder.createGrayLineBorder());
			return j;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}