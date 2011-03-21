package slogo.view.gui.panel;

import slogo.view.ViewHelper;
import util.resources.ResourceManager;

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
		add(ViewHelper.makeNewPanel());
		
	}
}
