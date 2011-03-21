package slogo.view.gui.panel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import slogo.view.ViewHelper;
import util.resources.ResourceManager;
import slogo.model.expression.Expression;
import javax.swing.JPanel;
/**
 * Panel responsible for space around main display
 * 
 * @author Austin Benesh
 *
 */
public class BorderLeftPanel extends JPanel
{
	ResourceManager resources;
	
	public BorderLeftPanel()
	{
		resources = ResourceManager.getInstance();
		resources.addResourcesFromFile("panel","slogo.view.resources");
		add(ViewHelper.makeNewPanel(),BorderLayout.LINE_START);
	}
	public void updateHistory(List<String> hist)
	{
		removeAll();
		add(new HistoryPanel(hist),BorderLayout.LINE_START);
	}
	public void updateVariables(Map<String,Expression> var)
	{	
		removeAll();
		add(new VariablePanel(var),BorderLayout.LINE_START);
	}
	public void updateCommands(Map<String,Expression> com)
	{
		removeAll();
		add(new CommandPanel(com),BorderLayout.LINE_START);
	}
	public void clearPanel()
	{
		removeAll();
		add(ViewHelper.makeNewPanel(),BorderLayout.LINE_START);
	}

}
