package slogo.view.panel;

import javax.swing.JList;
import javax.swing.JPanel;

/**
 * @author Austin Benesh
 *
 */
public class SelectPanel extends JPanel
{
	private JList listBox = new JList();
	
	private final int BORDER_WIDTH = 2;
	//TODO: Make this not hard-coded, i.e. FIGURE OUT how to get border width from BorderFactory
	
	
	public SelectPanel()
	{
		super();
	}
}