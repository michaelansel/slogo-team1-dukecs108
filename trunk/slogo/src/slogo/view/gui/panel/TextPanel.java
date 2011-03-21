package slogo.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import slogo.model.arena.Arena;
import slogo.view.ViewHelper;
import slogo.view.gui.InputManager;
import slogo.view.gui.TurtleGUI;
import util.parser.ParserException;
import util.resources.ResourceManager;

public class TextPanel extends JPanel
{
	ResourceManager resources;
	private int[] size;
	
	public JTextField textbox;
	public TextPanel()
	{		
		resources = ResourceManager.getInstance();
		resources.addResourcesFromFile("panel","slogo.view.resources");
		size = resources.getIntegerArray("textSize","x");
		setLayout(new BorderLayout(8,8));
		add(ViewHelper.makeNewPanel(), BorderLayout.PAGE_END);
		add(ViewHelper.makeNewPanel(), BorderLayout.LINE_START);
		textbox = new JTextField();
		add(textbox, BorderLayout.CENTER);
		
	}
	public void setText(JTextField toMake)
	{
		textbox = toMake;
		add(textbox,BorderLayout.CENTER);
	}

}
