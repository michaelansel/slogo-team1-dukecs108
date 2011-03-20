package slogo.view.components;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.util.drawtools.Pen2D;
import slogo.view.ViewHelper;

/**
 * An "AddTurtle" menu. Right now it is called from TurtleGUI as
 * a popup, but as it's a Container, it should work in just about
 * any GUI element we want with minimal changes.
 * 
 *
 * Because we're not handling what frame we're in, the view can use
 * the constructor with no frame to have, for all appearances, a Container
 * that closes itself when cancel is clicked or a turtle is added.
 * @author dave
 *
 */
public class PopupAddTurtle extends Container{

	private static final long serialVersionUID = -3696238918889216411L;

	//State Variables
	private String name="";
	private File file=Turtle.DEFAULT_IMAGE;
	private Pen2D pen=new Pen2D(new BasicStroke(), Color.BLACK);
	private Arena arena;
	private LabeledTextBox textBox;
	private ImageSelectPanel imageSelect;
	private PathSelectPanel pathSelect;
	private JFrame myOwner;

	//Constructors
	/**
	 * Creates a new "Add Turtle" menu, presumably with reference
	 * to your current Arena.
	 * @param a - the Arena we want to link our AddTurtle menu to.
	 * @param frame - the frame you want to add this container to.
	 */
	public PopupAddTurtle(Arena a, JFrame frame){
		setLayout(new BorderLayout(8,8));
		//setPreferredSize(new Dimension(200,200));

		arena=a;
		myOwner=frame;
		textBox=new LabeledTextBox("Name:");
		imageSelect=new ImageSelectPanel();
		pathSelect=new PathSelectPanel();

		addSpacers();

		JPanel j = ViewHelper.makeNewPanel();
		j.add(textBox, BorderLayout.PAGE_START);
		j.add(imageSelect, BorderLayout.CENTER);
		j.add(pathSelect, BorderLayout.PAGE_END);

		add(j, BorderLayout.CENTER);
		add(makeButtons(), BorderLayout.PAGE_END);

	}

	/**
	 * Constructs the "AddTurtle" container without attaching it to an existing
	 * JFrame. This means that for example it can be implemented in a slide-out
	 * panel.
	 * @param a - the Arena we want to link our AddTurtle menu to.
	 */
	public PopupAddTurtle(Arena a){
		this(a, new JFrame());
	}

	//Helpers
	/**
	 * Adds space at the top, left, and right of the main panel
	 */
	private void addSpacers(){
		add(ViewHelper.makeNewPanel(), BorderLayout.PAGE_START);
		add(ViewHelper.makeNewPanel(), BorderLayout.LINE_START);
		add(ViewHelper.makeNewPanel(), BorderLayout.LINE_END);
	}

	/**
	 * Creates 2 buttons, one "Accept" and one "Cancel". Accept attempts
	 * to add the turtle to our Arena, call update, then close, while
	 * cancel will exit the popup without 
	 * @return
	 */
	private JPanel makeButtons(){
		JPanel result=ViewHelper.makeNewPanel();

		JButton accept=new JButton(" Accept ");
		accept.addActionListener(
				new ActionListener(){ 
					public void actionPerformed (ActionEvent evt){
						try{
							addTurtle();
							arena.notifyObservers();
							myOwner.dispose();
						} catch (Exception e){
							String s = e.getMessage();
							if(s==null){
								s="One of your fields was left blank, or the path to your image was incorrect";
							}
							JOptionPane.showMessageDialog(myOwner,
									"There was a problem with your request : "+s,
									"ERROR!",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
		result.add(accept, BorderLayout.LINE_END);

		JButton cancel=new JButton(" Cancel ");
		cancel.addActionListener(
				new ActionListener(){ 
					public void actionPerformed (ActionEvent evt){
						myOwner.dispose();
					}
				}); 
		result.add(cancel, BorderLayout.LINE_START);
		result.add(ViewHelper.makeNewPanel(), BorderLayout.PAGE_END);
		return result;
	}

	//Actions
	/**
	 * Runs when AcceptButton is activated
	 */
	private int addTurtle(){ 
		name = textBox.getText();
		file = imageSelect.getFile();
		pen = pathSelect.getPen();
		arena.addTurtle(name, file, pen);
		return 1;
	}
}
