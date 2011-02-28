package slogo.view.panel;

import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * @author Austin Benesh
 *
 */
public class HistoryPanel extends JPanel
{
	private HashMap<Integer,String> commandHistory;
	private final int ITEMS_TO_DISP = 10;
	private final int BORDER_WIDTH = 2;
	//TODO: Make these not hard-coded, i.e. FIGURE OUT how to get border width from BorderFactory
	
	private JList listBox;
	
	public HistoryPanel()
	{
		super();
	}
	public void updateHistory()
	{
		commandHistory = (HashMap<Integer, String>) slogo.model.arena.Arena.getHistory();
		if(commandHistory == null)
			 commandHistory = new HashMap<Integer,String>();
	}
	private void drawHistoryBox()
	{
		updateHistory();
		listBox = new JList();
		String[] toList = new String[ITEMS_TO_DISP];
		int listIndex = 0;
		int startIndex = commandHistory.size()-ITEMS_TO_DISP;
		if(commandHistory.size() < ITEMS_TO_DISP)
			startIndex = 0;
		for(int i=startIndex; i<commandHistory.size(); i++)
		{
			toList[listIndex] = commandHistory.get(i);
			listIndex++;
		}
		listBox.setListData(toList);
		listBox.setFixedCellHeight((getHeight()-(BORDER_WIDTH*2))/10);
		listBox.setFixedCellWidth(getWidth()-(BORDER_WIDTH*2));
		
		this.add(listBox);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawHistoryBox();
	}
}