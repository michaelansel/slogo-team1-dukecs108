package slogo.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.*;  

import slogo.view.panel.PanelFactory;
import util.resources.ResourceManager;
import util.reflection.Reflection;

public class Frame extends JFrame
{
	/**
	 * 
	 */
	private final long serialVersionUID = 1L;
	private HashMap<String,JPanel> myPanels;
	PanelFactory panelFactory;
	
	
	public Frame()
	{
        ResourceManager resources = ResourceManager.getInstance();
        resources.addResourcesFromFile("view");

        //panelResources.addResourcesFromFile("panel");
        
		myPanels = new HashMap<String,JPanel>();
        //set up basic characteristics
        setTitle(resources.getString("title"));
        int[] size = resources.getIntegerArray("size", "x");
        setSize(size[0],size[1]);
		setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create File menu from view.properties
		createFileMenu(resources);
		
		//moving onto panel resource package now that frame is built
        resources.addResourcesFromFile("panel");		
		for(String toAdd : new String[] {"turtle","select","history","text","go"} )
		{
			createPanel(toAdd, resources);
		}

		setVisible(true);
	    updatePanel("history");
	    //repaint();
	}
	private void createFileMenu(ResourceManager frameResources)
	{
		JMenuBar fileMenuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(frameResources.getString("menuTitle"));
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		String[] menuOptions = frameResources.getStringArray("menuOptions");
		for(String option : menuOptions)
		{
			fileMenu.add(new JMenuItem(option));
		}
		
		fileMenuBar.add(fileMenu);
		this.setJMenuBar(fileMenuBar);
	}
	private void createPanel(String frameType, ResourceManager panelResources)
	{
		int[] size = panelResources.getIntegerArray(frameType+"Size", "x");
		int[] location = panelResources.getIntegerArray(frameType+"Location");
		
		JPanel drawPanel = PanelFactory.createPanel(panelResources.getString(frameType));
		drawPanel.setBounds(location[0],location[1],size[0],size[1]);
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.black,panelResources.getInteger(frameType+"Border")));

		drawPanel.setBackground(Color.white);
		myPanels.put(frameType,drawPanel);
		
		getContentPane().add(drawPanel);
	}
	private void updatePanel(String toUpdate)
	{
		JPanel modifiedPanel = myPanels.get(toUpdate);
		Graphics g = modifiedPanel.getGraphics();
		g.setColor(Color.blue);
		g.fillOval(1,1,100,100);
		g.drawString("hello",1,1);
		modifiedPanel.paint(g);
		getContentPane().add(modifiedPanel);
	}
}
