package slogo.view.components;

import java.awt.BorderLayout; 
import java.awt.Container;

import javax.swing.JTextField;

public class LabeledTextBox extends Container {
	//State Variables
	private static final long serialVersionUID = 1L;
	public static final boolean LEFT=true;
	public static final boolean ABOVE=false;

	private JTextField textBox = new JTextField();
	private HeaderLabel myLabel;
	private boolean position=LEFT;
	
	/**
	 * Creates a "HeaderLabel" and "textArea" with one command.
	 * @param label The label you want to appear before the text box.
	 */
	public LabeledTextBox(String label) {
		myLabel=new HeaderLabel(label);
		addComponents();
	}
	/**
	 * Adds our components
	 */
	public void addComponents(){
		setLayout(new BorderLayout(4,4));
		add(textBox, BorderLayout.CENTER);
		if (position==LEFT){
			add(myLabel, BorderLayout.LINE_START);
		}
		if (position==ABOVE){
			add(myLabel, BorderLayout.PAGE_START);
		}
	}
	/**
	 * get our text from the textbox
	 * @return the text in the textbox
	 */
	public String getText(){
		String s=textBox.getText();
		return s;
	}
	/**
	 * set the textbox's string
	 * @param s - the string you want textbox to nwo contain
	 */
	public void setText(String s){
		textBox.setText(s);
	}
	/**
	 * Set label's position to LabeledTextBox.LEFT or LabeledTextBox.RIGHT
	 * @param pos - The position you want the Label to be drawn in, relative to the textBox
	 */
	public void setPosition(boolean pos){
		if(position!=pos){
			position=pos;
			addComponents();
		}
	}
}
