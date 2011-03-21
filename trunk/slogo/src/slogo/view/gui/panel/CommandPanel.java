package slogo.view.gui.panel;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import slogo.model.expression.Expression;

public class CommandPanel extends JPanel
{
	Map<String,Expression> commands;
	public CommandPanel(Map<String,Expression> comm)
	{
		commands = comm;
		
		//hard-coded, put in bundle if time allows
		setPreferredSize(new Dimension(100,390));
		update();
	}
	public void update()
	{
		JTextArea text = new JTextArea(22,8);
		for(String str : commands.keySet())
		{
			text.append(str+" = "+commands.get(str)+"\n");
		}
		add(new JScrollPane(text));
	}
}
