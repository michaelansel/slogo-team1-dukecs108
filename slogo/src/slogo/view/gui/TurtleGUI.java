package slogo.view.gui;

import java.awt.BorderLayout;  
import java.awt.Component;
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
import slogo.view.gui.panel.BorderBottomPanel;
import slogo.view.gui.panel.BorderLeftPanel;
import slogo.view.gui.panel.BorderRightPanel;
import slogo.view.resources.PanelFactory;
import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.resources.ResourceManager;
import slogo.model.expression.*;


public class TurtleGUI implements Observer {
	//State Variables
	public static boolean RIGHT_TO_LEFT = false;
	private ArrayList<ArenaPanel> myArenaPanels = new ArrayList<ArenaPanel>();
	private Controller myController;
	private ResourceManager resources;
	
	//Declare components
	JPanel myPanel;
	JList turtleList;
	public JTextField textbox;
	public JButton button;
	
	JTabbedPane display;
	BorderBottomPanel borderBottom;
	BorderLeftPanel borderLeft;
	BorderRightPanel borderRight;
	
	JScrollPane myScroll;
	JFrame entireFrame;
	TurtleGUI me= this;
 
	//Constructor
	/**
	 * Create and show the GUI
	 * @param c - the Controller for the new GUI
	 */
	public TurtleGUI(Controller c){
		resources = ResourceManager.getInstance();
		resources.addResourcesFromFile("view","slogo.view.resources");
		
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
		entireFrame = new JFrame(resources.getString("title"));
		entireFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel basePanel = ViewHelper.makeNewPanel();
		entireFrame.getContentPane().add(basePanel);
		
		addMenu(basePanel);
		addPanels(basePanel);
		
		entireFrame.pack();
		entireFrame.setVisible(true);
	}
	public void addMenu(JPanel pane)
	{
		//Calls the menu to be created
		//Adds it at the top of the display
		myPanel = ViewHelper.makeNewPanel();
		myPanel.add(createMenu());
		pane.add(myPanel, BorderLayout.PAGE_START);
	}
	/**
	 * Adds the components to our pane
	 * @param pane the pane we want to add components to
	 */
	public void addPanels(JPanel pane) {
		resources.addResourcesFromFile("panel","slogo.view.resources");
		
		//Messy, but the reflection required here is messier.
		display = (JTabbedPane) PanelFactory.createPanel("display");
			pane.add(display,PanelFactory.getBorderLayout("displayPosition"));
		borderLeft = (BorderLeftPanel) PanelFactory.createPanel("borderLeft");
			pane.add(borderLeft,PanelFactory.getBorderLayout("borderLeftPosition"));
		borderRight = (BorderRightPanel) PanelFactory.createPanel("borderRight");
			pane.add(borderRight,PanelFactory.getBorderLayout("borderRightPosition"));
		borderBottom = (BorderBottomPanel) PanelFactory.createPanel("borderBottom");
			pane.add(borderBottom,PanelFactory.getBorderLayout("borderBottomPosition"));
		textbox = makeInput();
		button = makeButton();
		borderBottom.text.setText(textbox);
		borderBottom.button.setButton(button);
	}

	/**
	 * creates and populates the menu with File>Save, Variables>Add, 
	 * and Variables>Remove
	 */
	private JMenuBar createMenu() {
		JMenuBar jMenuBar1=new JMenuBar();
	
		jMenuBar1.add(createFileMenu());
		jMenuBar1.add(createVariableMenu());
		jMenuBar1.add(createInfoMenu());
		

		return jMenuBar1;
	}
	/**
	 * Creates File Menu
	 */
	public JMenu createFileMenu()
	{
		JMenu fileMenu = new JMenu();
		//Create and populate "file"
		fileMenu.setText("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		fileMenu.getAccessibleContext().setAccessibleDescription(
		"The file menu");

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
		return fileMenu;
	}
	/**
	 * Creates Variable Menu
	 */
	public JMenu createVariableMenu()
	{
		JMenu variableMenu = new JMenu();
		variableMenu.setText("Turtles");
		
		JMenuItem menuItem;
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
		return variableMenu;
	}
	/**
	 * Creates Information Menu - Variables and History
	 */
	public JMenu createInfoMenu()
	{
		JMenu infoMenu = new JMenu();
		infoMenu.setText("Information");
		
		JMenuItem menuItem;
		menuItem = new JMenuItem("History");
		menuItem.addActionListener(
				new ActionListener(){  
					public void actionPerformed (ActionEvent evt){ 
						borderLeft.updateHistory(getActiveArena().getHistoryEntries());
						entireFrame.pack();

					}
				});
		infoMenu.add(menuItem);
		
		menuItem = new JMenuItem("Variables");
		menuItem.addActionListener(
				new ActionListener(){  
					public void actionPerformed (ActionEvent evt){ 
						borderLeft.updateVariables(getActiveArena().getVariableMap());
						entireFrame.pack();
					}
				});
		infoMenu.add(menuItem);
		
		menuItem = new JMenuItem("User Commands");
		menuItem.addActionListener(
				new ActionListener(){  
					public void actionPerformed (ActionEvent evt){ 
						borderLeft.updateCommands(getActiveArena().getUserCommands());
						entireFrame.pack();
					}
				});
		infoMenu.add(menuItem);
		
		menuItem = new JMenuItem("Close Panel");
		menuItem.addActionListener(
				new ActionListener(){  
					public void actionPerformed (ActionEvent evt){ 
						borderLeft.clearPanel();
						entireFrame.pack();
					}
				});
		infoMenu.add(menuItem);
		

		return infoMenu;
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
	 * Creates a textfield that evaluates on ENTER.
	 */
	public JTextField makeInput ()
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
	public JButton makeButton(){ 
		JButton result = new javax.swing.JButton(resources.getString("buttonText"));

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
