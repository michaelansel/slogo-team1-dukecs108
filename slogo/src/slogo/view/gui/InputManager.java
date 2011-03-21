package slogo.view.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import slogo.controller.Controller;
import slogo.model.arena.Arena;
import slogo.view.components.PopupAddTurtle;
import slogo.view.gui.panel.ArenaPanel;
import slogo.view.gui.panel.DisplayPanel;
import util.parser.ParserException;
import slogo.view.gui.panel.TextPanel;

public class InputManager
{
	private Controller myController;
	public JTextField textbox;
	public JButton button;
	JTabbedPane display;
	
	public InputManager(Controller c, JTabbedPane disp)
	{
		textbox = makeInput();
		button = makeButton();
		display = disp;
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
	public void evaluateInput(){ 
		try {
			Arena a = getActiveArena();
			//tells Controller to evaluate it
			myController.evaluateExpression(textbox.getText(), a);
			//sets our textbox to blank 
			textbox.setText("");
		} catch (ParserException e) {
			JOptionPane.showMessageDialog(new JPanel(),
					e.getMessage(),
					"Input Error!",
					JOptionPane.ERROR_MESSAGE);
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

}
