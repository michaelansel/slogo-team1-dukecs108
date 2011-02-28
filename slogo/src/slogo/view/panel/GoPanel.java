package slogo.view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author Austin Benesh
 *
 */
public class GoPanel extends JPanel
{

	public GoPanel()
	{
		super();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		//Font is set here, size of "Go!" can be adjusted
		g.setFont(new Font("Dialog",1,20));
		
		int adjustedLeft = (int)((g.getFont().getSize())/2.3+getHeight()/2);
		g.drawString("Go!",getWidth()/4,adjustedLeft);		
	}
}