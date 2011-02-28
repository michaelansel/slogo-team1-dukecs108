package slogo.view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;

import slogo.model.arena.turtle.Turtle;
/**
 * @author Austin Benesh
 *
 */
public class SelectPanel extends JPanel
{
	private ArrayList<Turtle> turtleSelection;
	private final int BORDER_WIDTH = 2;
	//TODO: Make this not hard-coded, i.e. FIGURE OUT how to get border width from BorderFactory
	
	private JList nameList = new JList();
	
	public SelectPanel()
	{
		super();
	}
	public void updateSelection()
	{
		turtleSelection = slogo.model.arena.Arena.getTurtleList();
		if(turtleSelection == null)
			turtleSelection = new ArrayList<Turtle>();
	}
	private void drawSelectionBox()
	{
		updateSelection();
		String[] names = new String[turtleSelection.size()];
		int selectedIndex = 0;
		for(int i=0; i<turtleSelection.size(); i++)
		{
			names[i] = turtleSelection.get(i).getName();
			if(i==slogo.model.arena.Arena.getCurrentIndex())
				selectedIndex = i;
		}
		nameList.setListData(names);
		
		nameList.setFixedCellHeight((getHeight()-(BORDER_WIDTH*2))/7);
		nameList.setFixedCellWidth(getWidth()-(BORDER_WIDTH*2));
		
		nameList.setSelectedIndex(selectedIndex);
		
		this.add(nameList);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawSelectionBox();
	}
}