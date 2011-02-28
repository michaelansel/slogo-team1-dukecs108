package slogo.view.panel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import slogo.model.arena.turtle.Turtle;
import slogo.model.arena.turtle.qualities.positioning.Position;
import slogo.util.trace.Trace;
import slogo.view.resources.ImageListCellRenderer;

/**
 * @author Austin Benesh
 *
 */
public class SelectPanel extends JPanel
{
	private JList listBox = new JList();
	
	private final int BORDER_WIDTH = 2;
	//TODO: Make this not hard-coded, i.e. FIGURE OUT how to get border width from BorderFactory
	
	
  //  private static void createAndShowGUI() {
  //      
  //      //Create and set up the window.
  //      JFrame frame = new JFrame("Turtle World");
  //      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //      addComponentsToPane(frame.getContentPane());
  //      frame.pack();
  //      frame.setVisible(true);
  //  }
	
	public SelectPanel()
	{
		JPanel SelectPanel=new JPanel();
		SelectPanel=addComponentsToPanel();
	}
	
    public static JPanel addComponentsToPanel() {
    	JPanel myPanel;
    	JButton button;
    	JTextField textbox;
    	JList turtleList;
        

        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        File f = new File("default.png");        
        
        ArrayList<Turtle> a = new ArrayList<Turtle>();
        for (int i=0; i<5; i++){
        	a.add(new Turtle("Turtle "+i, new Position(i*10, i*10), 
        		new Trace(new BasicStroke(), new java.awt.Color(i*10, i*10, i*10)), f
        		));
        	
            //JFrame frame = new JFrame("Turtle image");
            // BufferedImage im = null;
            // try {
     		//	im = ImageIO.read(f);
     		//} catch (IOException e) {
     		//	System.out.println("Yeah, you fucked it up bro.");
     		//}
     		//countt++;
             //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             //Container imgPane = frame.getContentPane();
             //JLabel imaj = new JLabel("CAT", new ImageIcon(im), SwingConstants.LEFT);
             //imgPane.add(imaj);
             //frame.pack();
            // frame.setVisible(true);
             //
        }
        
    	myPanel = makeNewPanel();
    	turtleList = createAndPopulateList(a);
    	turtleList.setCellRenderer(new ImageListCellRenderer());
    	JScrollPane myScroll = new JScrollPane(turtleList);
        myScroll.setPreferredSize(new Dimension (165, 300));
        myScroll.setMaximumSize(new Dimension (170, 350));
        myScroll.setSize(new Dimension (165, 300));
    	myScroll.doLayout();
        myPanel.setPreferredSize(new Dimension (165, 300));
        myPanel.add(myScroll, BorderLayout.CENTER);
        myPanel.setPreferredSize(new Dimension (165, 300));
        return myPanel;
    	//String[] ss = new String[40];
    	//for (int i=0;i<40;i++) ss[i]="Turtle "+i;
        //turtleList = new JList(ss);
	}
	
	private static JPanel makeNewPanel(){
		
    	JPanel panel = new JPanel();
    	panel.setLayout(new BorderLayout(10,10));
    	return panel;
    	
    }
    
    public static JList createAndPopulateList(ArrayList<Turtle> list){
    	Object[] panels = new Object[list.size()];
    	// construct the menuList as a JList
    	JList turtleList = new JList();
    	turtleList.setCellRenderer(new ImageListCellRenderer());
    	int count = 0;
    	JLabel lab = null;
    	for (Turtle t: list){
    		JFrame frame = new JFrame("Turtle image");
             BufferedImage im = null;
             try {
     			im = ImageIO.read(t.getImage());
     		} catch (IOException e) {
     			System.out.println("FILE NOT FOUND, DEFAULT ADDED");
     			try {
					im = ImageIO.read(new File("default.png"));
				} catch (IOException e1) {
					System.out.println("That one didn't exist either. Sorry, bad luck.");
				}
     		}
            //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Container imgPane = frame.getContentPane();
            JLabel imaj = new JLabel("Turtle "+count, new ImageIcon(im), SwingConstants.LEFT);
            imgPane.add(imaj);
    		panels[count]=imgPane;
            //frame.pack();
            //frame.setVisible(true);
     		

    		//JPanel p = makeNewPanel();
    		//BufferedImage i=null;

			//try {
			//	i = ImageIO.read(t.getImage());
			//} catch (IOException e) {
			//	System.out.println("ERRORS!!!");
			//	e.printStackTrace();
			//}
			
    		//lab = new JLabel("Turtle "+count, new ImageIcon(i), SwingConstants.LEADING);
    		
            //    JFrame frame2 = new JFrame("Turtle image");
            //    frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //    Container imgPane2 = frame2.getContentPane();
            //    imgPane2.add(lab);
            //    frame2.pack();
            //    frame2.setVisible(true);
                
    		//p.add(lab, BorderLayout.CENTER);
    		//panels.add(p);
    		count++;
    	}
    	 turtleList.setListData(panels);
		return turtleList;
    }

	
}