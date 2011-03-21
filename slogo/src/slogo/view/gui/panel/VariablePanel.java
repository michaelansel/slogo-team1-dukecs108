package slogo.view.gui.panel;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import slogo.model.expression.Expression;

public class VariablePanel extends JPanel
{
	Map<String,Expression> variables;

	public VariablePanel(Map<String,Expression> var)
	{
		variables = var;
		//hard-coded, put in bundle if time allows
		setPreferredSize(new Dimension(100,390));
		update();
	}
	public void update()
	{
		//hard-coded, put in bundle if time allows
		JTextArea text = new JTextArea(22,8);
		for(String str : variables.keySet())
		{
			text.append(str+" = "+variables.get(str)+"\n");
		}
		add(new JScrollPane(text));
	}
}
