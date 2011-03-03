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
	
	int ArenaID;
	JPanel WorldPanel;
	JPanel TurtleList;
	JPanel myPanel;
	static JPanel BasePanel;
	JList turtleList;
	JScrollPane myScroll;

	
	/**
	 * Creates an arena panel (Main Panel and Turtle List)
	 * (you should probably add it to your display under a tab) 
	 * 
	 * .....Call this when you want to update the tab (ie: destroy
	 * old and create new with correct variables)
	 * 
	 * @param aID the ID Number of the Arena you want to display.
	 */
	public ArenaPanel(int aID){
		ArenaID=aID;
		draw();
	}
	
	/**
	 * Updates the Panel to match the current state of the Arena.
	 */
	public void draw(){
		BasePanel = makeNewPanel();
		BasePanel.add(drawArenaPanel(), BorderLayout.CENTER);
		BasePanel.add(makeList(), BorderLayout.LINE_END);
	}
	
	/**
	 * Grab a displayable panel consisting of the Arena image
	 * and the turtle list for the ArenaPanel's Arena.
	 * @return
	 */
	public JPanel getPanel(){
		return BasePanel;
	}
	
	/**
	 * returns the ID of the Arena this ArenaPanel is associated with
	 */
	public int getArenaID(){
		return ArenaID;
	}
	
    /**
     * General use "makepanel" for use whenever you need a panel.
     * @return default panel with borderlayout and borders of 8.
     */
	private static JPanel makeNewPanel(){
    	JPanel panel = new JPanel();
    	panel.setLayout(new BorderLayout(8,8));
    	return panel;
    }
	
	/**
	 * Draws a view of the Arena to which this ArenaPanel
	 * is associated.
	 */
	private JPanel drawArenaPanel(){
    	return slogo.controller.Controller.getDrawnPanel(ArenaID);
	}
	
	/**
	 * Makes the Displayable Turtle list, returns it in a pane.
	 */
	private JPanel makeList(){
		JPanel pane=makeNewPanel();
    	
        //TODO: clean up code
        //CAUTION: this was done mostly guess/check for a few hours...
        //This was the only way it ended up working, but I know theres
        //got to be better ways to do it out there.

    	ArrayList<Turtle> tList = slogo.controller.Controller.getTurtleList(ArenaID);
        
    	myPanel = makeNewPanel();
    	turtleList = createAndPopulateList(tList);
    	turtleList.setCellRenderer(new ImageListCellRenderer());
		turtleList.setBorder(BorderFactory.createRaisedBevelBorder());
    	myScroll= new JScrollPane(turtleList);
    	
        myScroll.setPreferredSize(new Dimension (165, 300));
        myScroll.setMaximumSize(new Dimension (170, 350));
    	myScroll.doLayout();
        myPanel.setPreferredSize(new Dimension (165, 300));
        myPanel.add(myScroll, BorderLayout.CENTER);
        pane.add(myPanel, BorderLayout.CENTER);
        return pane;
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
			//	JOptionPane.showMessageDialog(BasePanel,
			//			"Can't read image for "+t.getName()+". Default added instead.",
			//			"FILE ERROR; DEFAULT ADDED",
			//			JOptionPane.ERROR_MESSAGE);
     			try {
					im = ImageIO.read(new File("src/images/default.png"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(BasePanel,
							"The file is corrupt, please upload an image of the correct type.",
							"UNHANDLED FILE ERROR; PLEASE CHANGE IMAGE",
							JOptionPane.ERROR_MESSAGE);
				}
     		}
     		
            Container imgPane = frame.getContentPane();
            JLabel imaj = new JLabel(t.getName(), new ImageIcon(im), SwingConstants.LEFT);
            imgPane.add(imaj);
    		panels[count]=imgPane;
     		    		count++;
    	}
    	turtleList.setListData(panels);
		return turtleList;
    }
}
