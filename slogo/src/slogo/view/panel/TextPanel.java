package slogo.view.panel;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * @author Austin Benesh
 *
 */
public class TextPanel extends JPanel
{
	private final int BORDER_WIDTH = 1;
	//TODO: Make this not hard-coded, i.e. FIGURE OUT how to get border width from BorderFactory
	
	private JTextArea textBox;
	
	public TextPanel()
	{
		super();
		drawTextBox();
	}
	public String parseInput()
	{
		String toRet = textBox.getText();
		drawTextBox();
		return toRet;
	}
	public void drawTextBox()
	{
		textBox = new JTextArea();
		textBox.setLineWrap(true);
		//textBox.setSize(getWidth()-(2*BORDER_WIDTH),getHeight()-(2*BORDER_WIDTH));
		textBox.setSize(378,38);
		textBox.append("Input text here:");
		add(textBox);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}