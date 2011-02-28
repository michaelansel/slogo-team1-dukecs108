package slogo.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.*;  

import slogo.view.panel.PanelFactory;
import slogo.view.panel.TextPanel;
import util.resources.ResourceManager;

/**
 * @author Austin Benesh
 *
 */
public class Frame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private HashMap<String,JPanel> myPanels;
	private ResourceManager resources;
	
	public Frame()
	{
		myPanels = new HashMap<String,JPanel>();
		resources = ResourceManager.getInstance();
		
		setUpFrame();        
        setUpPanels();
       
		setVisible(true);
	    //repaint();
	}
	private void setUpFrame()
	{
        resources.addResourcesFromFile("view");
        setTitle(resources.getString("title"));
        int[] size = resources.getIntegerArray("size", "x");
        setSize(size[0],size[1]);
		setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createFileMenu();
	}
	private void setUpPanels()
	{		
		for(String panelType : new String[] {"turtle","select","history","text","go"} )
		{
			createPanel(panelType);
		}
	}
	private void createFileMenu()
	{
		JMenuBar fileMenuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(resources.getString("menuTitle"));
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		String[] menuOptions = resources.getStringArray("menuOptions");
		for(String option : menuOptions)
		{
			fileMenu.add(new JMenuItem(option));
		}
		
		fileMenuBar.add(fileMenu);
		this.setJMenuBar(fileMenuBar);
	}
	private void createPanel(String panelType)
	{	
		JPanel drawPanel = PanelFactory.createPanel(panelType);
		myPanels.put(panelType,drawPanel);
		getContentPane().add(drawPanel);
	}
	private void updatePanel(String toUpdate)
	{
		JPanel modifiedPanel = myPanels.get(toUpdate);

		getContentPane().add(modifiedPanel);
	}
}
