package slogo.view.components;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

	//Constructor
	public PopupAddTurtle(Arena a, JFrame frame){
		setLayout(new BorderLayout(8,8));
		//setPreferredSize(new Dimension(200,200));
		
		arena=a;
		myOwner=frame;
		textBox=new LabeledTextBox("Name:");
		imageSelect=new ImageSelectPanel();
		pathSelect=new PathSelectPanel();
		
		addSpacers();
		
		JPanel j = newPanel();
		j.add(textBox, BorderLayout.PAGE_START);
		j.add(imageSelect, BorderLayout.CENTER);
		j.add(pathSelect, BorderLayout.PAGE_END);
		
		add(j, BorderLayout.CENTER);
		add(makeButtons(), BorderLayout.PAGE_END);
		
	}


	//Actions
	/**Runs when AcceptButton executes*/
	private int addTurtle(){ 
		name = textBox.getText();
		file = imageSelect.getFile();
		pen = pathSelect.getPen();
		System.out.println("Step 1");
		arena.addTurtle(name, file, pen);
		System.out.println("Step 2");
		return 1;
	}

	
	private void addSpacers(){
		add(newPanel(), BorderLayout.PAGE_START);
		add(newPanel(), BorderLayout.LINE_START);
		add(newPanel(), BorderLayout.LINE_END);
	}
	private JPanel makeButtons(){
		JPanel result=newPanel();
		
		JButton accept=makeButton(" Accept ");
		accept.addActionListener(
				new ActionListener(){ 
					public void actionPerformed (ActionEvent evt){
						try{
							addTurtle();
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
		
		JButton cancel=makeButton(" Cancel ");
		cancel.addActionListener(
				new ActionListener(){ 
					public void actionPerformed (ActionEvent evt){
						myOwner.dispose();
					}
				});
		result.add(cancel, BorderLayout.LINE_START);
		return result;
	}
	private JButton makeButton(String s){ 
		JButton result = new javax.swing.JButton(s);
		return result;
	}
	
	private JPanel newPanel(){
		return new JPanel(new BorderLayout(8,8));
	}
}
