package slogo.view;


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
import slogo.view.panel.ArenaPanel;
import util.parser.ParserException;


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
	
    /**
     * Create the GUI and show it.
     */
	public TurtleGUI (Controller c){
		myController = c;
		createAndShowGUI();
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
			}
		}
		if(newPanel==true){
			//No, so we need to add a new ArenaPanel
			addArenaPanel(a);
		}
	}
	
	/**
	 * Updates the correct ArenaPanel for our arena o.
	 * Not sure what arg is...
	 */
	@Override
	//TODO: Figure out what arg is and/or how to use it.
	public void update(Observable o, Object arg) { 
		if (o instanceof Arena)
			update((Arena) o);
	}
	
	/**
	 * Creates a new ArenaPanel, draws it, and adds it to the tabbed display
	 * and to myArenaPanels list
	 * 
	 */
	public void addArenaPanel(Arena a){
			ArenaPanel arP = new ArenaPanel(a);
			myArenaPanels.add(arP);
	    	display.addTab("Arena "+myArenaPanels.size(), arP.getPanel() );
	}
	
	/**
	 * Create the GUI and show it.
	 */
    private void createAndShowGUI() {
        //Create and set up the window.
        entireFrame = new JFrame("I like turtles.");
        entireFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel basePanel = makeNewPanel();
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
    	myPanel = makeNewPanel();
    	myPanel.add(createMenu());
        pane.add(myPanel, BorderLayout.PAGE_START);
        
        
        //Creates a new JTabbedPane.
    	display = new JTabbedPane();
    	display.setPreferredSize(new Dimension(600,400));
        pane.add(display, BorderLayout.CENTER);
        
        
        //Creates space to the left of PANE
        myPanel=makeNewPanel();
        myPanel.add(makeNewPanel(), BorderLayout.PAGE_START);
        myPanel.add(makeNewPanel(), BorderLayout.CENTER);
        pane.add(makeNewPanel(), BorderLayout.LINE_START);
        
        
        //Creates the textbox & button, adds spaces where necessary
        myPanel = makeNewPanel();
        textbox = makeInput();
        button= makeButton();
        //Textbox added here
    	JPanel borderPanel = makeNewPanel();
        borderPanel.add(makeNewPanel(), BorderLayout.PAGE_END);
        borderPanel.add(makeNewPanel(), BorderLayout.LINE_START);
        borderPanel.add(textbox, BorderLayout.CENTER);
        myPanel.add(borderPanel, BorderLayout.CENTER);
        //Button added here
        borderPanel=makeNewPanel();
        borderPanel.add(makeNewPanel(), BorderLayout.PAGE_END);
        borderPanel.add(makeNewPanel(), BorderLayout.LINE_END);
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
	
	/** Evaluate function. Sends the input to controller for further action,
	 * along with the ID of the arrayList that is currently active.
	 */
	public void evaluateInput() { 
		ArenaPanel arP = (ArenaPanel) display.getSelectedComponent();
		Arena a = arP.getArena();
		try { 
			myController.evaluateExpression(textbox.getText(), a);
		} catch (ParserException e) {
			JOptionPane.showMessageDialog(myPanel,
					e.getMessage(),
					"Input Error!",
					JOptionPane.ERROR_MESSAGE);
		}
		textbox.setText("");
		//Repaints our view to reflect any changes
		entireFrame.repaint();
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
			menuItem = new JMenuItem("Save",
			                         new ImageIcon("src/images/save_icon.gif"));
			menuItem.setMnemonic(KeyEvent.VK_B);
			menuItem.setAccelerator(KeyStroke.getKeyStroke(
			        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			menuItem.addActionListener(
					new ActionListener(){ 
						public void actionPerformed (ActionEvent evt){
								//TODO: do stuff
				}
			});
			fileMenu.add(menuItem);

			//Create and populate "Variables"
			variableMenu.setText("Variables");
			jMenuBar1.add(variableMenu);
			
			//a group of JMenuItems
			menuItem = new JMenuItem("Add Variable",
					new ImageIcon("src/images/add_icon.gif"));  
			menuItem.addActionListener(
					new ActionListener(){ 
						public void actionPerformed (ActionEvent evt){
							//TODO: do stuff
						}
					});
			variableMenu.add(menuItem);

			menuItem = new JMenuItem("Remove Variable",
					new ImageIcon("src/images/del_icon.gif"));
			menuItem.addActionListener(
					new ActionListener(){ 
						public void actionPerformed (ActionEvent evt){
							//TODO: do stuff
						}
					});
			variableMenu.add(menuItem);

		   return jMenuBar1;
	}
	
    /**
     * Creates a new generic Panel with BorderLayout with borders (8,8)
     * @return default panel with borderlayout and borders of 8.
     */
	private JPanel makeNewPanel(){
    	JPanel panel = new JPanel();
    	panel.setLayout(new BorderLayout(8,8));
    	return panel;
    }
	

}
