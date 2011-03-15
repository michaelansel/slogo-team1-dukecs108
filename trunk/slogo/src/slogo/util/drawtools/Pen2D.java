package slogo.util.drawtools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * The class responsible for carrying the qualities of the turtle's path
 * @author Julian Genkins
 *
 */
public class Pen2D extends DrawTool2D 
{

    Stroke myStroke;
    
    
   
    public Pen2D(){
        this(new BasicStroke(2), Color.BLACK);
    }
  
    public Pen2D (Stroke stroke, Color color)
    {
        this(stroke, color, true);
    }


    public Pen2D (Stroke stroke, Color color, boolean state)
    {
        setStroke(stroke);
        setColor(color);
        setState(state);
    }

   
    
    
    

    public void setStroke (Stroke myStroke)
    {
        this.myStroke = myStroke;
    }

    public Stroke getStroke ()
    {
        return myStroke;
    }

    @Override
	public int compareTo(DrawTool pen) {
		if(this.myColor.equals(((Pen2D) pen).myColor))
			    return 0;
		return 1;
	}

	public DrawTool clone(){
        return new Pen2D(myStroke, myColor, this.isDown());
	    
	}

    /* (non-Javadoc)
     * @see slogo.util.IScribe#applyGraphics(java.awt.Graphics2D)
     */
    @Override
    public Graphics2D applyGraphics(Graphics2D g2d){
        if (this.isDown()){
            g2d.setStroke(this.getStroke());
            g2d.setColor(this.getColor());
        }
        return g2d;
    }

}
