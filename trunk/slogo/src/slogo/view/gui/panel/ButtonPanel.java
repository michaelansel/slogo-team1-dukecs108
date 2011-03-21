package slogo.view.gui.panel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import slogo.view.ViewHelper;
import slogo.view.gui.InputManager;
import slogo.view.gui.TurtleGUI;
import util.resources.ResourceManager;

public class ButtonPanel extends JPanel
{
	ResourceManager resources;
	private int[] size;
	
	public JButton button;
	public ButtonPanel()
	{
		resources = ResourceManager.getInstance();
		resources.addResourcesFromFile("panel","slogo.view.resources");
		size = resources.getIntegerArray("buttonSize","x");
		setLayout(new BorderLayout(8,8));
		add(ViewHelper.makeNewPanel(), BorderLayout.PAGE_END);
		add(ViewHelper.makeNewPanel(), BorderLayout.LINE_END);
		button = new JButton();
		add(button, BorderLayout.CENTER);
	}
	public void setButton(JButton toMake)
	{
		button = toMake;
		add(button,BorderLayout.CENTER);
	}
}
