package slogo.view;

import static org.junit.Assert.assertEquals;
import slogo.SLogo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.SwingConstants;

import slogo.model.arena.turtle.Turtle;
import slogo.model.arena.turtle.qualities.positioning.Position;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import slogo.view.resources.ImageListCellRenderer;
import slogo.view.subpanels.ArenaPanel;
import util.parser.ParserException;
import util.parser.ParserResult;

@SuppressWarnings("unused")

public class TurtleGUI {
	
	//State Variables
    public static boolean RIGHT_TO_LEFT = false;
    public int ID_NUMBER;
    public TreeSet<Integer> myArenaList = new TreeSet<Integer>();
    public ArrayList<ArenaPanel> myArenaPanels = new ArrayList<ArenaPanel>();
    
  //Declare components
    static JPanel myPanel;
	static JButton button;
	static JTextField textbox;
	static JList turtleList;
	static JTabbedPane display;
	static JScrollPane myScroll;
	static JFrame entireFrame;
    
	
    /**
     * Create the GUI and show it.
     */
	public TurtleGUI (int ID_NUM)
	{
		ID_NUMBER=ID_NUM;
		createAndShowGUI();
	}
	
	/**
	 * Adds the specified arenaID to "myArenaList". Should probably create
	 * a pane at this time as well. Will deal with all necessary calls out
	 * by saying "hey controller, tell the Arena with ID_NUMBER blablabla to
	 * do this..."
	 * 
	 * @param arenaID the ID_NUMBER of the Arena you want to add
	 * @return false if the ID_NUMBER is already present in myArenaList
	 */
	public boolean addArena(int arenaID){
		if(myArenaList.add(arenaID)){
			ArenaPanel a = new ArenaPanel(arenaID);
			myArenaPanels.add(a);
			myPanel=a.getPanel();
	    	display.addTab("Arena "+arenaID, myPanel);
			return true;
		}
		return false;
	}
	
	/**
	 * Updates the correct arena when called.
	 */
	public void updateArena(int arenaID){

		for (ArenaPanel a: myArenaPanels){
			if (a.getArenaID()==arenaID){
				a.draw();
			}
		}
		entireFrame.repaint();
	}
	
	/**
	 * Create the GUI and show it.
	 */
    private static void createAndShowGUI() {
        //Create and set up the window.
        entireFrame = new JFrame("I like turtles.");
        entireFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel basePanel = makeNewPanel();
        entireFrame.getContentPane().add(basePanel);
        addComponentsToPane(basePanel);
        entireFrame.pack();
        entireFrame.setVisible(true);
    }
    
    public static void addComponentsToPane(JPanel pane) {
    	
    	JPanel borderPanel;
    	
    	//Calls the menu to be created
    	//Adds it at the top of the display
    	myPanel = makeNewPanel();
    	myPanel.add(createMenu());
        pane.add(myPanel, BorderLayout.PAGE_START);
        
        //Creates a new JTabbedPane.
        //Currently "myPanel" is acting as an example
    	display = new JTabbedPane();
    	display.setPreferredSize(new Dimension(600,400));
        pane.add(display, BorderLayout.CENTER);
        
        
        //Creates space to the left of PANE
        myPanel=makeNewPanel();
        myPanel.add(makeNewPanel(), BorderLayout.PAGE_START);
        myPanel.add(makeNewPanel(), BorderLayout.CENTER);
        pane.add(makeNewPanel(), BorderLayout.LINE_START);
        
        
        //Creates the awesome list we see on the right side
        //File f = new File("src/images/default.png");
        //ArrayList<Turtle> a = new ArrayList<Turtle>();
        //for (int i=0; i<5; i++){        	a.add(new Turtle("Turtle "+i, f ));
        	//a.get(i).setPosition(new Position(50, 50));  }
        
        
        //CAUTION: this was done mostly guess/check for a few hours...
        //This was the only way it ended up working, but I know theres
        //got to be better ways to do it out there.
    	//myPanel = makeNewPanel();
    	//turtleList = createAndPopulateList(a);
    	//turtleList.setCellRenderer(new ImageListCellRenderer());
		//turtleList.setBorder(BorderFactory.createRaisedBevelBorder());
    	//myScroll= new JScrollPane(turtleList);
        //myScroll.setPreferredSize(new Dimension (165, 300));
        //myScroll.setMaximumSize(new Dimension (170, 350));
    	//myScroll.doLayout();
        //myPanel.setPreferredSize(new Dimension (165, 300));
        //myPanel.add(myScroll, BorderLayout.CENTER);
        
        //borderPanel=makeNewPanel();
        //borderPanel.add(makeNewPanel(), BorderLayout.PAGE_START);
        //borderPanel.add(makeNewPanel(), BorderLayout.LINE_END);
        //borderPanel.add(myPanel, BorderLayout.CENTER);
        
        //pane.add(borderPanel, BorderLayout.LINE_END);
        
        
        //Creates the textbox and button in a panel, then adds them to
        //PAGE_END in the main panel.
        
        myPanel = makeNewPanel();
        textbox = makeInput();
        button= makeButton();
        
        borderPanel=makeNewPanel();
        borderPanel.add(makeNewPanel(), BorderLayout.PAGE_END);
        borderPanel.add(makeNewPanel(), BorderLayout.LINE_START);
        borderPanel.add(textbox, BorderLayout.CENTER);
        myPanel.add(borderPanel, BorderLayout.CENTER);
        
        borderPanel=makeNewPanel();
        borderPanel.add(makeNewPanel(), BorderLayout.PAGE_END);
        borderPanel.add(makeNewPanel(), BorderLayout.LINE_END);
        borderPanel.add(button, BorderLayout.CENTER);
        myPanel.add(borderPanel, BorderLayout.LINE_END);
        
        pane.add(myPanel, BorderLayout.PAGE_END);
    }

    /**
     * General use "makepanel" when you just want a new
     * default panel.
     * @return default panel with borderlayout and borders of 10.
     */
	private static JPanel makeNewPanel(){
    	JPanel panel = new JPanel();
    	panel.setLayout(new BorderLayout(8,8));
    	return panel;
    }
    
	/**
	 * Responsible for creating the scrollable JList with images and
	 * awesome background colors. Does this through the class
	 * "ImageListCellRenderer.java" which edits how the default JList
	 * items are rendered.
	 * 
	 * @param list the list of turtles you want to represent
	 * @return a JList of those turtles names+images.
	 */
    public static JList createAndPopulateList(ArrayList<Turtle> list){
    	Object[] panels = new Object[list.size()];
    	
    	// construct the menuList as a JList
    	JList turtleList = new JList();
    	turtleList.setCellRenderer(new ImageListCellRenderer());
    	int count = 0;
    	for (Turtle t: list){
    		JFrame frame = new JFrame("Turtle image");
             BufferedImage im = null;
             try {
     			im = ImageIO.read(t.getImage());
     		} catch (IOException e) {
				JOptionPane.showMessageDialog(myPanel,
						e.getMessage(),
						"FILE ERROR; DEFAULT ADDED",
						JOptionPane.ERROR_MESSAGE);
     			try {
					im = ImageIO.read(new File("src/images/default.png"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(myPanel,
							e.getMessage(),
							"UNHANDLED FILE ERROR; PLEASE DELETE OFFENDING TURTLE",
							JOptionPane.ERROR_MESSAGE);
				}
     		}
     		  		
            Container imgPane = frame.getContentPane();
            JLabel imaj = new JLabel("Turtle "+count, new ImageIcon(im), SwingConstants.LEFT);
            imgPane.add(imaj);
    		panels[count]=imgPane;
     		    		count++;
    	}
    	 turtleList.setListData(panels);
		return turtleList;
    }
    
    
    /**
	 * Creates a textfield that evaluates on ENTER.
	 */
	protected static JTextField makeInput ()
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
	protected static JButton makeButton(){
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
	 * Evaluate function. Sends the input to controller for further action,
	 * along with the ID of the arrayList that is currently active.
	 */
	public static void evaluateInput() {
		try {
			SLogo.myController.evaluateExpression(textbox.getText(), 0);
		} catch (ParserException e) {
			JOptionPane.showMessageDialog(myPanel,
					e.getMessage(),
					"Input Error!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean hasArena(int ID){
		if (myArenaList.contains(ID)){
			return true;
		}
		
		return false;
	}
    
    
	/**
	 * creates and populates the menu with File>Save, Variables>Add, 
	 * and Variables>Remove
	 */
	private static JMenuBar createMenu() {
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
	
}

