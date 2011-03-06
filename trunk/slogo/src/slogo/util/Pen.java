package slogo.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

/**
 * The class responsible for carrying the qualities of the turtle's path
 * @author Julian Genkins
 *
 */
public class Pen
{

    Stroke myStroke;
    Color myColor;
    private boolean amDown;
   
    public Pen(){
        this(new BasicStroke(2), Color.RED);
    }
  
    public Pen (Stroke stroke, Color color)
    {
        setStroke(stroke);
        setColor(color);
        amDown = true;
    }


    public Pen (Stroke stroke, Color color, boolean state)
    {
        setStroke(stroke);
        setColor(color);
        amDown = state;
    }

    public boolean isUp ()
    {
        return !amDown;
    }
    
    public boolean isDown ()
    {
        return amDown;
    }

    public boolean putDown ()
    {
        amDown = true;
        return amDown;
    }

    public boolean putUp ()
    {
        amDown = false;
        return amDown;
    }
    
    public void setColor (Color myColor)
    {
        this.myColor = myColor;
    }

    public Color getColor ()
    {
        return myColor;
    }

    public void setStroke (Stroke myStroke)
    {
        this.myStroke = myStroke;
    }

    public Stroke getStroke ()
    {
        return myStroke;
    }

	public int compareTo(Pen pen) {
		if(this.myColor.equals(pen.myColor))
			    return 0;
		return 1;
	}

	public Pen clone(){
        return new Pen(myStroke, myColor, amDown);
	    
	}

    
    
}
