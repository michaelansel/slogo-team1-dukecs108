package slogo.model.artists.qualities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import slogo.model.ModelException;

public class Pen extends ArtistQuality
{
    boolean amDown;
    Color myColor;
    BasicStroke myStroke;


    public Pen ()
    {
        super();
    }
    
    public Pen (BasicStroke stroke, Color color)
    {
        this(stroke, color, true);
    }


    public Pen (BasicStroke stroke, Color color, boolean state)
    {
        super(Pen.class);
        setStroke(stroke);
        setColor(color);
        setState(state);
    }
    
    


//    @Override
//    public int compareTo (ArtistQuality o)
//    {
//        
//        if(this.myColor.equals(((Pen) o).getColor()))
//            return 0;
//        return 1;
//    }


    @Override
    public Object setTo (ArtistQuality aq) throws ModelException
    {
        setStroke(((Pen) aq).getStroke());
        setColor(((Pen) aq).getColor());
        setState(((Pen) aq).isDown());
        return null;
    }

    @Override
    boolean equals (ArtistQuality aq) throws ModelException
    {
        return false;
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

    public void setState(boolean state){
        amDown = state;
    }
    
    public int setColor (Color color)
    {
        this.myColor = color;
        
        return color.getRGB();
        
    }

    public Color getColor ()
    {
        return myColor;
    }
    
    public int setStroke (BasicStroke stroke)
    {
        this.myStroke = stroke;
        return (int) ((BasicStroke)stroke).getLineWidth();
    }

    public BasicStroke getStroke ()
    {
        return myStroke;
    }
    
    public Pen clone(){
        
        return new Pen(myStroke, myColor, amDown);
        
    }

    public void apply (Graphics2D g2d)
    {
        g2d.setColor(myColor);
        g2d.setStroke(myStroke);
    }
    
    
    
}