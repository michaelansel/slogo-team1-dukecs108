/**
 * 
 */
package slogo.util;

import java.awt.Color;


/**
 * @author Michael Ansel
 */
public class Pen implements Cloneable
{
    public enum Pattern
    {
        DASHED, DOTTED, DOUBLE, SOLID
    }

    public Color color;
    public Pattern pattern;
    public int thickness;


    public Pen ()
    {
        color = Color.BLACK;
        pattern = Pattern.SOLID;
        thickness = 1;
    }


    public Pen (Color color, Pattern pattern, int thickness)
    {
        this.color = color;
        this.pattern = pattern;
        this.thickness = thickness;
    }


    @Override
    public Pen clone ()
    {
        return new Pen(color, pattern, thickness);
    }
}
