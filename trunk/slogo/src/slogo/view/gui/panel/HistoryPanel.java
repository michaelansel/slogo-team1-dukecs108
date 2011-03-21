package slogo.view.gui.panel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HistoryPanel extends JPanel
{
	List<String> history;
	public HistoryPanel(List<String> hist)
	{
		history = hist;
		
		//hard-coded, put in bundle if time allows
		setPreferredSize(new Dimension(100,390));
		update();
	}
	public void update()
	{
		JTextArea text = new JTextArea(22,8);
		for(String str : history)
		{
			text.append(str+"\n");
		}
		add(new JScrollPane(text));
	}
}
