package slogo.view;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.*;  

import util.resources.ResourceManager;

public class Frame extends JFrame
{
	/**
	 * 
	 */
	private final long serialVersionUID = 1L;
	private HashMap<String,JPanel> myPanels;
	ResourceManager resources;
	JFrame myFrame;
	
	public Frame()
	{
        resources = ResourceManager.getInstance();
        resources.addResourcesFromFile("view");
		myPanels = new HashMap<String,JPanel>();
        
        //set up basic characteristics
        setTitle(resources.getString("title"));
        int[] size = resources.getIntegerArray("totalSize", "x");
        setSize(size[0],size[1]);
		setLayout(null);
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createFileMenu();
		
		String[] panelsToAdd = {"draw","select","history","text","go"};
		
		for(String toAdd : panelsToAdd)
		{
			createPanel(toAdd);
		}

		setVisible(true);
	    updatePanel("history");
	    //repaint();
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
	private void createPanel(String frameType)
	{
		int[] size = resources.getIntegerArray(frameType+"Size", "x");
		int[] location = resources.getIntegerArray(frameType+"Location");
		
		JPanel drawPanel = new JPanel();
		drawPanel.setBounds(location[0],location[1],size[0],size[1]);
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.black,resources.getInteger(frameType+"Border")));

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
