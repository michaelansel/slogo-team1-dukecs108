package slogo.view.subpanels;


import slogo.*;
import slogo.SLogo.*;
import slogo.SLogo;
import java.awt.BorderLayout; 
import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.view.resources.ImageListCellRenderer;

/**
 * Panel containing the Arena and its TurtleList. Can be plugged into
 * the tabbed view item of TurtleGUI.
 * 
 * @author Dave
 *
 */
public class ArenaPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JPanel myPanel;
	private static JPanel BasePanel;
	private JScrollPane myScroll;
	private Arena myArena;

	
	/**
	 * Creates an arena panel (drawnArenaPanel and Turtle List)
	 * (you should probably add it to your display under a tab) 
	 * 
	 * @param a the Arena you want to disply in this tab
	 */
	public ArenaPanel(Arena a){
		myArena=a;
		draw();
	}
	
	/**
	 * Updates the Panel to match the current state of the Arena.
	 */
	public void draw(){
		BasePanel = makeNewPanel();
		BasePanel.add(drawArenaPanel(), BorderLayout.CENTER);
		BasePanel.add(makeList(), BorderLayout.LINE_END);
		BasePanel.repaint();
	}
	
	/**
	 * Grab a displayable panel of the drawnArenaPanel and Turtle List
	 * @return the ArenaPanel
	 */
	public JPanel getPanel(){
		return BasePanel;
	}
	
	/**
	 * Returns this object's Arena
	 */
	public Arena getArena(){
		return myArena;
	}

	/**
	 * Draws an updated view of this ArenaPanel's Arena
	 */
	private JPanel drawArenaPanel(){
    	return new drawnArena(myArena);
	}

	/**
	 * Makes the Displayable Turtle list, returns it in a panel.
	 */
	private JPanel makeList(){
		
		//Sets up the list
		HashMap<Integer, Turtle> tMap = (HashMap<Integer, Turtle>) myArena.getTurtleMap();
		JList turtleList = createAndPopulateList(tMap);
    	turtleList.setCellRenderer(new ImageListCellRenderer());
		turtleList.setBorder(BorderFactory.createRaisedBevelBorder());
		
		//Sets up the scrolling list display
    	myScroll= new JScrollPane(turtleList);
        myScroll.setPreferredSize(new Dimension (165, 300));
        myScroll.setMaximumSize(new Dimension (170, 350));
    	myScroll.doLayout(); 

    	//Puts the list in a panel
    	myPanel = makeNewPanel();
        myPanel.setPreferredSize(new Dimension (165, 300));
        myPanel.add(myScroll, BorderLayout.CENTER);

        //TODO: is this necessary???
        //Places this panel in another panel? and returns.
		JPanel pane=makeNewPanel();
        pane.add(myPanel, BorderLayout.CENTER);
        return pane;
	}

	/**
	 * Creates the scrollable JList with images and Turtle Names.
	 * Look at ImageListCellRenderer to see how this is done.
	 * 
	 * @param list the list of turtles you want to represent
	 * @return a JList of those turtles names+images.
	 */
    public static JList createAndPopulateList(Map<Integer, Turtle> tMap){ 
    	Object[] panels = new Object[tMap.size()];
    	// construct the menuList as a JList
    	JList turtleList = new JList();
    	turtleList.setCellRenderer(new ImageListCellRenderer());
    	int count = 0;
    	
        for (Entry<Integer, Turtle> entry: tMap.entrySet()){
    		JFrame frame = new JFrame("Turtle image");
    		Turtle t = entry.getValue();
    		String myName = t.getName();
    		File myImage = t.getImage();

    		BufferedImage im = bufferTurtleImage(myName, myImage);
    		
        	JLabel imaj = new JLabel(t.getName()+" (ID: "+entry.getKey()+")", new ImageIcon(im), SwingConstants.LEFT);
        	frame.getContentPane().add(imaj);
        	panels[count]=frame.getContentPane();
        	count++;
        }

    	
    	turtleList.setListData(panels);
		return turtleList;
    }

	/**
	 * Returns the BufferedImage for our turtle
	 * @param myName the name of the turtle
	 * @param myImage the turtle's image
	 * @return a BufferedImage of myFile, or in the case of a reading error- the default.
	 */
	private static BufferedImage bufferTurtleImage(String myName, File myImage) {
		BufferedImage im = null;
		try {
			im = ImageIO.read(myImage);
		} catch (IOException e) {
			try {
				im = ImageIO.read(new File("src/images/default.png"));
				JOptionPane.showMessageDialog(BasePanel,
						"Could not read image, default added For "+ myName +" instead.",
						"Unreadable image error!",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(BasePanel,
						"The file is corrupt, please upload an image of the correct type.",
						"UNHANDLED FILE ERROR; PLEASE CHANGE IMAGE",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return im;
	}
    
    /**
     * Creates a new generic Panel with BorderLayout with borders (8,8)
     * @return default panel with borderlayout and borders of 8.
     */
	private static JPanel makeNewPanel(){
    	JPanel panel = new JPanel();
    	panel.setLayout(new BorderLayout(8,8));
    	return panel;
    }

}
