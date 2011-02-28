package slogo.view.panel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.reflection.Reflection;

public class PanelFactory
{
	private static final String Bad_Input = "Bad Input";
	private static final String BAD_Props  = "Error in properties file for ";
	public static JPanel createPanel(String classNameString)
	{
		try{
			System.out.println(classNameString);
			Class className = Class.forName(classNameString);
			return getPanel(className);
		}
		catch (NullPointerException e){
			showErrorMsg(Bad_Input,Bad_Input);
			return null;
		} 
		catch (ClassNotFoundException e){
			showErrorMsg(Bad_Input,Bad_Input);
			return null;
		}
	}
	private static JPanel getPanel(Class className)
	{
		try{
			JPanel obj= (JPanel) className.newInstance();
			return obj;
		}catch(InstantiationException e){
			showErrorMsg(BAD_Props + className.getName(),Bad_Input);
			return null;
		} catch (IllegalAccessException e) {
			showErrorMsg(Bad_Input,Bad_Input);
			return null;
		}catch (NullPointerException e) {
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
