package slogo.view.gui.panel;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

import slogo.model.arena.Arena;
import util.resources.ResourceManager;

public class DisplayPanel extends JTabbedPane
{
	ResourceManager resources;
	private ArrayList<ArenaPanel> myArenaPanels = new ArrayList<ArenaPanel>();
	
	public DisplayPanel()
	{
		resources = ResourceManager.getInstance();
		resources.addResourcesFromFile("panel","slogo.view.resources");
		
		int[] size = resources.getIntegerArray("displaySize","x");
		setPreferredSize(new Dimension(size[0],size[1]));
		
	}
	
	/**
	 * Creates a new ArenaPanel, draws it, and adds it to the tabbed display
	 * and to myArenaPanels list
	 * 
	 */
	public void addArenaPanel(Arena a)
	{
		ArenaPanel arP = new ArenaPanel(a);
		myArenaPanels.add(arP);
		addTab("Arena "+myArenaPanels.size(), arP );
	}
}
