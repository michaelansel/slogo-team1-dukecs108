package slogo.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.view.ViewHelper;
import slogo.view.gui.panel.subpanels.ArenaDraw;
import slogo.view.resources.ImageList;

/**
 * Panel containing the Arena and its TurtleList. Can be plugged into
 * the tabbed view item of TurtleGUI.
 * 
 * @author Dave
 *
 */
public class ArenaPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	//State Variables
	private JPanel myPanel;
	private JScrollPane myScroll;
	private Arena myArena;
	private ImageList turtleList=new ImageList();
	
	//Constructors
	/**
	 * Creates an arena panel (drawnArenaPanel and Turtle List)
	 * (you should probably add it to your display under a tab) 
	 * 
	 * @param a the Arena you want to disply in this tab
	 */
	public ArenaPanel(Arena a){
		myArena=a;
		addComponents();
		draw();
	}
	/**
	 * Aids the constructor, separated for more understandable division.  
	 * Can be called if we need a "hard reset" of components rather than
	 * just to redraw them.
	 */
	public void addComponents(){
		removeAll();
		this.setLayout(new BorderLayout(8,8));
		this.add(new ArenaDraw(myArena), BorderLayout.CENTER);
		this.add(makeList(), BorderLayout.LINE_END);
		repaint();
	}
	
	//Helpers
	/**
	 * Makes the Displayable Turtle list, returns it in a panel.
	 */
	private JPanel makeList(){

		//Sets up the list
		HashMap<Integer, Turtle> tMap = (HashMap<Integer, Turtle>) myArena.getTurtleMap();
		updateTurtleList(tMap);
		turtleList.setBorder(BorderFactory.createRaisedBevelBorder());

		//Sets up the scrolling list display
		myScroll= new JScrollPane(turtleList);
		myScroll.setPreferredSize(new Dimension (165, 300));
		myScroll.setMaximumSize(new Dimension (170, 350));
		myScroll.doLayout(); 

		//Puts the list in a panel
		myPanel = ViewHelper.makeNewPanel();
		myPanel.setPreferredSize(new Dimension (200, 300));
		myPanel.add(myScroll, BorderLayout.CENTER);

		//TODO: is this necessary???
		//Places this panel in another panel? and returns.
		JPanel pane=ViewHelper.makeNewPanel();
		pane.add(myPanel, BorderLayout.CENTER);
		return pane;
	}

	//Accessors
	/**
	 * Returns this object's Arena
	 */
	public Arena getArena(){
		return myArena;
	}
	
	//Updaters
	/**
	 * Updates the Panel to match the current state of the Arena.
	 */
	public void draw(){
		HashMap<Integer, Turtle> tMap = (HashMap<Integer, Turtle>) myArena.getTurtleMap();
		updateTurtleList(tMap);
	}
	/**
	 * Updates our TurtleList to refelct the Turtle Map of our relevant
	 * Arena.
	 * 
	 * @param tMap the map of turtles to update the List for.
	 */
	private void updateTurtleList(Map<Integer, Turtle> tMap){
		Map<String, BufferedImage> imap = new HashMap<String, BufferedImage>();

		for (Entry<Integer, Turtle> entry: tMap.entrySet()){
			Turtle t = entry.getValue();
			String myName = t.getName();
			File myImage = t.getImage();

			BufferedImage img = ViewHelper.bufferTurtleImage(myName, myImage, this);
			imap.put(t.getName()+" (ID: "+entry.getKey()+" )", img);
		}
		turtleList.setCells(imap);
	}

}