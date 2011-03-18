package slogo.view.panel;

import static org.junit.Assert.assertEquals; 
import slogo.Main; 

import slogo.controller.*;
import slogo.view.Frame;
import slogo.model.arena.*;
import slogo.model.expression.Expression;
import slogo.model.parser.CommandLexer;
import slogo.model.parser.SlogoLexer;
import slogo.model.parser.SlogoParser;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.parser.AbstractParser;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.grammar.GrammarParserFactory;

/**
 * @author Austin Benesh
 * @updated to be functional by David Crowe
 *
 */ 

public class TextPanel extends JPanel
{
	private final int BORDER_WIDTH = 1;
	//TODO: Make this not hard-coded, i.e. FIGURE OUT how to get border width from BorderFactory
	
	public JTextField textbox;
	
	public TextPanel()
	{
		super();
		drawTextBox();
	}
	public String parseInput()
	{
		String toRet = textbox.getText();
		drawTextBox();
		return toRet;
	}

    private GrammarParserFactory commandParserFactory;
	public void evaluateInput() {
		String stri = textbox.getText();
		try {
			ParserResult result = SlogoParser.parse(stri);		
	        Expression expression = (Expression) result.getList().get(0);
	        int back = slogo.model.arena.Arena.evaluateNode(expression);
	        assertEquals(50, back);
		} catch (ParserException e1) {
			System.out.println("Parser error!");
			e1.printStackTrace();
		}

		
		System.out.println("Should be updating panels...");
		Main.myFrame.updatePanel("turtle");
		Main.myFrame.updatePanel("select");
		Main.myFrame.updatePanel("history");
		Main.myFrame.updatePanel("text");
		Main.myFrame.updatePanel("go");
	}
	
	
	
	public void drawTextBox()
	{
		textbox = new JTextField();
		//textBox.setSize(getWidth()-(2*BORDER_WIDTH),getHeight()-(2*BORDER_WIDTH));
		textbox.setPreferredSize(new Dimension(378,38));
		textbox.addActionListener(
				new ActionListener(){ 
					public void actionPerformed (ActionEvent evt){
						evaluateInput();
			}
		});
		add(textbox);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}