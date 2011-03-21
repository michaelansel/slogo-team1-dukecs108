package slogo.view.resources;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import util.reflection.Reflection;
import util.resources.ResourceManager;

/**
 * @author Austin Benesh
 * 
 */
public class PanelFactory {
        private static final String Bad_Input = "Bad Input";
        private static final String BAD_Props = "Error in properties file for ";
        private static ResourceManager resources;

        public static Component createPanel(String classNameString) {
                try {
                        resources = ResourceManager.getInstance();
                        resources.addResourcesFromFile("panel", "slogo.view.resources");
                        Class className = Class.forName(resources.getString(classNameString));
                        Component obj;
                        if(classNameString.equals("display"))
                        	obj = (JTabbedPane) className.newInstance();
                        else
                        	obj = (JPanel) className.newInstance();
                        return obj;
                } catch (NullPointerException e) {
                        showErrorMsg(classNameString, Bad_Input);
                        return null;
                } catch (ClassNotFoundException e) {
                        showErrorMsg(classNameString, Bad_Input);
                        return null;
                } catch (InstantiationException e) {
                        showErrorMsg(BAD_Props + classNameString, Bad_Input);
                        return null;
                } catch (IllegalAccessException e) {
                        showErrorMsg(classNameString, Bad_Input);
                        return null;
                }
        }
        public static String getBorderLayout(String borderString)
        {
		        try {
		                String str = (String) (BorderLayout.class).getField(resources.getString(borderString)).get(null);
		                return str;
		        } catch (IllegalArgumentException e) {
		                showErrorMsg(borderString, Bad_Input);
		                return null;
		        } catch (SecurityException e) {
		                showErrorMsg(borderString, Bad_Input);
		                return null;
		        } catch (IllegalAccessException e) {
		                showErrorMsg(borderString, Bad_Input);
		                return null;
		        } catch (NoSuchFieldException e) {
		                showErrorMsg(borderString, Bad_Input);
		                return null;
		        }
        }
     
        private static void showErrorMsg(String msg, String title) {
                JOptionPane.showMessageDialog(new JFrame(), msg, title,
                                JOptionPane.WARNING_MESSAGE);
        }
}
