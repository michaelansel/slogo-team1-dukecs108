package slogo.view.gui;

import java.awt.BorderLayout;  
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import slogo.controller.Controller;
import slogo.model.arena.Arena;
import slogo.view.ViewHelper;
import slogo.view.components.PopupAddTurtle;
import slogo.view.gui.panel.ArenaPanel;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;
import slogo.model.expression.*;


public class TurtleGUI implements Observer {
	//State Variables
	public static boolean RIGHT_TO_LEFT = false;
	private ArrayList<ArenaPanel> myArenaPanels = new ArrayList<ArenaPanel>();
	private Controller myController;

	//Declare components
	JPanel myPanel;
	JButton button;
	JTextField textbox;
	JList turtleList;
	JTabbedPane display;
	JScrollPane myScroll;
	JFrame entireFrame;
	TurtleGUI me= this;
 
	//Constructor
	/**
	 * Create and show the GUI
	 * @param c - the Controller for the new GUI
	 */
	public TurtleGUI(Controller c){
		myController = c;
		createAndShowGUI();
	} 


	//Create the GUI
	/**
	 * Creates a new ArenaPanel, draws it, and adds it to the tabbed display
	 * and to myArenaPanels list
	 * 
	 */
	public void addArenaPanel(Arena a){
		ArenaPanel arP = new ArenaPanel(a);
		myArenaPanels.add(arP);
		display.addTab("Arena "+myArenaPanels.size(), arP );
	}

	/**
	 * Create the GUI and show it.
	 */
	private void createAndShowGUI() {
		//Create and set up the window.
		entireFrame = new JFrame("I like turtles.");
		entireFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel basePanel = ViewHelper.makeNewPanel();
		entireFrame.getContentPane().add(basePanel);
		addComponentsToPane(basePanel);
		entireFrame.pack();
		entireFrame.setVisible(true);
	}

	/**
	 * Adds the components to our pane
	 * @param pane the pane we want to add components to
	 */
	public void addComponentsToPane(JPanel pane) {
		//Calls the menu to be created
		//Adds it at the top of the display
		myPanel = ViewHelper.makeNewPanel();
		myPanel.add(createMenu());
		pane.add(myPanel, BorderLayout.PAGE_START);


		//Creates a new JTabbedPane.
		display = new JTabbedPane();
		display.setPreferredSize(new Dimension(600,400));
		pane.add(display, BorderLayout.CENTER);


		//Creates space to the left of PANE
		myPanel=ViewHelper.makeNewPanel();
		myPanel.add(ViewHelper.makeNewPanel(), BorderLayout.PAGE_START);
		myPanel.add(ViewHelper.makeNewPanel(), BorderLayout.CENTER);
		pane.add(ViewHelper.makeNewPanel(), BorderLayout.LINE_START);


		//Creates the textbox & button, adds spaces where necessary
		myPanel = ViewHelper.makeNewPanel();
		textbox = makeInput();
		button= makeButton();
		//Textbox added here
		JPanel borderPanel = ViewHelper.makeNewPanel();
		borderPanel.add(ViewHelper.makeNewPanel(), BorderLayout.PAGE_END);
		borderPanel.add(ViewHelper.makeNewPanel(), BorderLayout.LINE_START);
		borderPanel.add(textbox, BorderLayout.CENTER);
		myPanel.add(borderPanel, BorderLayout.CENTER);
		//Button added here
		borderPanel=ViewHelper.makeNewPanel();
		borderPanel.add(ViewHelper.makeNewPanel(), BorderLayout.PAGE_END);
		borderPanel.add(ViewHelper.makeNewPanel(), BorderLayout.LINE_END);
		borderPanel.add(button, BorderLayout.CENTER);
		myPanel.add(borderPanel, BorderLayout.LINE_END);
		//whole thing added to pane
		pane.add(myPanel, BorderLayout.PAGE_END);
	}

	/**
	 * Creates a textfield that evaluates on ENTER.
	 */
	private JTextField makeInput ()
	{
		JTextField result = new JTextField();
		result.addActionListener(
				new ActionListener(){ 
					public void actionPerformed (ActionEvent evt){
						evaluateInput();
					}
				});
		result.setMinimumSize(new Dimension(40, 40));
		result.setPreferredSize(new Dimension(40, 40));
		return result;
	}

	/**
	 * Creates "Evaluate!" button that evaluates on LEFTCLICK.
	 */
	private JButton makeButton(){ 
		JButton result = new javax.swing.JButton("Go!");

		result.addActionListener(
				new ActionListener(){ 
					public void actionPerformed (ActionEvent evt){
						evaluateInput();
					}
				});
		result.setMinimumSize(new Dimension(80, 40));
		result.setPreferredSize(new Dimension(80, 40));
		return result;
	}

	/**
	 * creates and populates the menu with File>Save, Variables>Add, 
	 * and Variables>Remove
	 */
	private JMenuBar createMenu() {
		JMenuBar jMenuBar1=new JMenuBar();
		JMenu fileMenu=new JMenu();
		JMenu variableMenu=new JMenu();

		//Create and populate "file"
		fileMenu.setText("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		fileMenu.getAccessibleContext().setAccessibleDescription(
		"The file menu");
		jMenuBar1.add(fileMenu);

		//a group of JMenuItems
		JMenuItem menuItem;
		menuItem = new JMenuItem("Create New Arena",
				new ImageIcon(""));
		menuItem.setMnemonic(KeyEvent.VK_B);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(
				new ActionListener(){  
					public void actionPerformed (ActionEvent evt){
						me.update(myController.addArena(me));
					}
				});
		fileMenu.add(menuItem);
		
		menuItem = new JMenuItem("Connect to Arena",
				new ImageIcon(""));
		menuItem.setMnemonic(KeyEvent.VK_B);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(
				new ActionListener(){  
					public void actionPerformed (ActionEvent evt){
						connectToArena();
					}
				});
		fileMenu.add(menuItem);
		
		menuItem = new JMenuItem("Close Current Arena",
				new ImageIcon(""));
		menuItem.setMnemonic(KeyEvent.VK_B);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(
				new ActionListener(){  
					public void actionPerformed (ActionEvent evt){
						ArenaPanel toRemove=getActivePanel();
						display.remove(toRemove);
						myArenaPanels.remove(toRemove);
					}
				});
		fileMenu.add(menuItem);
		

		//Create and populate "Turtles"
		variableMenu.setText("Turtles");
		jMenuBar1.add(variableMenu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Add Turtle",
				new ImageIcon("src/images/add_icon.gif"));  
		menuItem.addActionListener(
				new ActionListener(){ 
					public void actionPerformed (ActionEvent evt){
						addTurtle();
					}
				});
		variableMenu.add(menuItem);

		menuItem = new JMenuItem("Remove Turtle",
				new ImageIcon("src/images/del_icon.png"));
		menuItem.addActionListener(
				new ActionListener(){  
					public void actionPerformed (ActionEvent evt){ 
						removeTurtle();
					}
				});
		variableMenu.add(menuItem);

		return jMenuBar1;
	}

	//Change the GUI as the user interacts with the program
	/**
	 * Updates the correct ArenaPanel for our arena o.
	 * Not sure what arg is...
	 */
	//TODO: Figure out what arg is and/or how to use it.
	@Override
	public void update(Observable o, Object arg) { 
		if (o instanceof Arena)
			drawAndRepaint((Arena) o);
	}
 
	/**
	 * Updates the correct arena when called. Creates a new ArenaPanel
	 * if there is not already one watching the given Arena
	 */
	public void update(Arena a){ 
		boolean newPanel=true;
		//Checks if we already have an ArenaPanel for this Arena
		for (ArenaPanel arP: myArenaPanels){
			if (arP.getArena()==a){
				//Yes, so this is not a new Arenapanel and we need to update
				newPanel=false;
				arP.draw();
				entireFrame.repaint();
			}
		}
		if(newPanel==true){
			//No, so we need to add a new ArenaPanel
			addArenaPanel(a);
		}
	}

	/** 
	 * Evaluate function. Sends the input to controller for further action,
	 * along with the ID of the arrayList that is currently active.
	 */
	public void evaluateInput(){ 
		Arena a = getActiveArena();
		try {
			//tells Controller to evaluate it
			myController.evaluateExpression(textbox.getText(), a);
			//sets our textbox to blank 
			textbox.setText("");
		} catch (ParserException e) {
			JOptionPane.showMessageDialog(myPanel,
					e.getMessage(),
					"Input Error!",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Creates a new generic turtle in the active Arena
	 */
	public void addTurtle() { 
		Arena a = getActiveArena();
		JFrame popup = new JFrame("Add Turtle");
		popup.setContentPane(new PopupAddTurtle(a, popup, myController));
		popup.pack();
		popup.setVisible(true);
		popup.repaint();
	}

	/**
	 * Remove the active turtle from the active Arena
	 */
	public void removeTurtle(){
		Arena a = getActiveArena();

		String s = (String)JOptionPane.showInputDialog(
				new JFrame(),
				"Enter the [ID #] of the turtle you want to remove" +
				"from your active arena.:\n"
				+ "\"(Be careful! This action is not reversible)\"",
				"Remove Turtle",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"0");
		//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			if (!myController.removeTurtle(a, Integer.parseInt(s)))
				JOptionPane.showMessageDialog(new JFrame(),
					    "No turtle exists with that ID in the current Arena." +
					    " Make sure you are using the turtle's ID and not its name.",
					    "ERROR!",
					    JOptionPane.ERROR_MESSAGE);
				
		}

		drawAndRepaint(a);
	}
	
	public void connectToArena(){
		//Popup, ask for user input
		String s = (String)JOptionPane.showInputDialog(
				new JFrame(),
				"What is the number of the Arena you would like to add?",
				"Connect to Arena",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"1");
		//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			int i = Integer.parseInt(s);
			Arena ar = myController.addExistingArena(me, i);
			if (ar!=null){
				update(ar);
			}else{
				JOptionPane.showMessageDialog(new JFrame(),
					    "No Arena exists with this ID!" +
					    "Be sure the Arena you want to connect to exists.",
					    "ERROR!",
					    JOptionPane.ERROR_MESSAGE);
			}
	}
	}
	

	/**
	 * Returns the active ArenaPanel for this view. (the current tab)
	 */
	public ArenaPanel getActivePanel(){
		ArenaPanel arP = (ArenaPanel) display.getSelectedComponent();
		return arP;
	}
	
	/**
	 * Returns the active Arena for this gui
	 */
	public Arena getActiveArena(){
		return getActivePanel().getArena();
	}

	/**
	 * Draws the active panel, repaints the entire frame
	 */
	public void drawAndRepaint(Arena a){
		//Updates our drawn Arena
		update(a);
		//Repaints our view to reflect any changes
		entireFrame.repaint();
	}

}
