package model.turtle.deprecated;

import java.awt.Color;

/**
 * A set of color enums which are able to be stored and contribute to the parsing as well by
 * mapping a string to the actually java color
 * @author Julian
 *
 */
public enum TraceColor {

	
	RED (Color.RED, "red"),
	BLUE (Color.BLUE, "blue"),
	YELLOW (Color.YELLOW, "yellow"), 
	BLACK (Color.BLACK, "black");
	
	private Color myColor;
	private String myString;
	
	TraceColor (Color color, String string){
		myColor = color;
		myString = string;
	}
}
