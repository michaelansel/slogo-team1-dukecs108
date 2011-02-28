package slogo.view.resources;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

/**
 * Renders list-cells with an image that can be rendered
 * by a swing JList
 * 
 * @author Dave
 *
 */
public class ImageListCellRenderer implements ListCellRenderer {
	
	@Override
	public Component getListCellRendererComponent(JList jlist, Object input, 
			int cellIndex, boolean isSelected, boolean cellHasFocus) 
	{
		Component component = (Component) input;
		component.setForeground (Color.lightGray);
		component.setBackground (cellHasFocus ? new Color(135, 206, 235) : new Color(200, 200, 200));
		return component;
	}

}
