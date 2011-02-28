package slogo.view.panel;

import java.awt.Graphics;
import java.util.TreeMap;

import javax.swing.JList;
import javax.swing.JPanel;

/**
 * @author Austin Benesh
 *
 */
public class HistoryPanel extends JPanel
{
	private TreeMap<Integer,String> historyList = new TreeMap<Integer,String>();
	private final int ITEMS_TO_DISP = 7;
	private final int BORDER_WIDTH = 2;
	//TODO: Make these not hard-coded, i.e. FIGURE OUT how to get border width from BorderFactory
	
	private JList listBox = new JList();
	
	public HistoryPanel()
	{
		super();
	}
	public void updateHistory()
	{
		
		historyList = Controller.;
	}
	private void drawHistoryBox()
	{
		String[] toList = new String[ITEMS_TO_DISP];
		int listIndex = 0;
		int startIndex = historyList.size()-ITEMS_TO_DISP;
		if(historyList.size() < ITEMS_TO_DISP)
			startIndex = 0;
		for(int i=startIndex; i<historyList.size(); i++)
		{
			toList[listIndex] = historyList.get(i);
			listIndex++;
		}
		listBox.setListData(toList);
		listBox.setFixedCellHeight((getHeight()-(BORDER_WIDTH*2))/7);
		listBox.setFixedCellWidth(getWidth()-(BORDER_WIDTH*2));
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawHistoryBox();
		this.add(listBox);
	}
}