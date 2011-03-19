package slogo.view.components;

import javax.swing.JLabel;

public class HeaderLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	HeaderLabel(String s){
		setText(s);
		setFont(getFont().deriveFont(14.0f));
	}
}
