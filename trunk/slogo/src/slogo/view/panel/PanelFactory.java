package slogo.view.panel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.reflection.Reflection;
import util.resources.ResourceManager;

/**
 * @author Austin Benesh
 *
 */
public class PanelFactory
{
	private static final String Bad_Input = "Bad Input";
	private static final String BAD_Props  = "Error in properties file for ";
	private static ResourceManager resources;
	
	public static JPanel createPanel(String classNameString)
	{
		try{
			resources = ResourceManager.getInstance();
			resources.addResourcesFromFile("panel");
			Class className = Class.forName(resources.getString(classNameString));
			JPanel obj= (JPanel) className.newInstance();
			
			//Create panel
			int[] size = resources.getIntegerArray(classNameString+"Size", "x");
			int[] location = resources.getIntegerArray(classNameString+"Location");
			String[] border = resources.getStringArray(classNameString+"Border");
			
			obj.setBounds(location[0],location[1],size[0],size[1]);
			obj.setBorder(BorderFactory.createLineBorder(getColorByName(border[1]),Integer.parseInt(border[0])));
			obj.setBackground(getColorByName(resources.getString(classNameString+"BkgColor")));
			
			return obj;
		}
		catch (NullPointerException e){
			showErrorMsg(Bad_Input,Bad_Input);
			return null;
		} 
		catch (ClassNotFoundException e){
			showErrorMsg(Bad_Input,Bad_Input);
			return null;
		} catch (InstantiationException e)
		{
			showErrorMsg(BAD_Props + classNameString,Bad_Input);
			return null;
		} catch (IllegalAccessException e)
		{
			showErrorMsg(Bad_Input,Bad_Input);
			return null;
		}
	}
	private static Color getColorByName(String fieldNameString)
	{
			try
			{
				Color col = (Color) (Color.class).getField(fieldNameString).get(null);
				return col;
			} catch (IllegalArgumentException e)
			{
				showErrorMsg(Bad_Input,Bad_Input);
				return null;
			} catch (SecurityException e)
			{
				showErrorMsg(Bad_Input,Bad_Input);
				return null;
			} catch (IllegalAccessException e)
			{
				showErrorMsg(Bad_Input,Bad_Input);
				return null;
			} catch (NoSuchFieldException e)
			{
				showErrorMsg(Bad_Input,Bad_Input);
				return null;
			}
	}
	private static void showErrorMsg(String msg, String title) 
	{
		JOptionPane.showMessageDialog(new JFrame(),
			    msg, 
			    title,JOptionPane.WARNING_MESSAGE);		
	}
}
