package slogo.view.gui.panel;

import java.awt.BorderLayout;

import slogo.view.ViewHelper;
import slogo.view.resources.PanelFactory;
import util.resources.ResourceManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Panel responsible for space around main display
 * 
 * @author Austin Benesh
 *
 */
public class BorderBottomPanel extends JPanel
{
	ResourceManager resources;
	public TextPanel text;
	public ButtonPanel button;
	
	public BorderBottomPanel()
	{
		resources = ResourceManager.getInstance();
		resources.addResourcesFromFile("panel","slogo.view.resources");
		setLayout(new BorderLayout(8,8));
		addSubPanels();
	}
	public void addSubPanels()
	{
		text = new TextPanel();
		add(text,PanelFactory.getBorderLayout("textPosition"));
		button = new ButtonPanel();
		add(button,PanelFactory.getBorderLayout("buttonPosition"));
	}
}
