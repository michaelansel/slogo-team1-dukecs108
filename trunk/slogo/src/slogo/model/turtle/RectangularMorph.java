/**
 * 
 */
package slogo.model.turtle;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import slogo.util.Line;


/**
 * @author Michael Ansel
 */
public class RectangularMorph implements Morph
{

    private double dX;
    private double dY;
    private List<Line> lines;
    private IMorphable morphable;


    public RectangularMorph ()
    {
        this(0, 0);
    }


    public RectangularMorph (double dX, double dY)
    {
        this(dX, dY, null);
    }


    public RectangularMorph (double dX, double dY, IMorphable morphable)
    {
        this(dX, dY, morphable, new ArrayList<Line>());
    }


    public RectangularMorph (double dX,
                             double dY,
                             IMorphable morphable,
                             List<Line> lines)
    {
        this.dX = dX;
        this.dY = dY;
        this.morphable = morphable;
        this.lines = lines;
    }


    @Override
    public List<Line> getLines ()
    {
        return lines;
    }


    @Override
    public IMorphable getMorphable ()
    {
        return morphable;
    }


    @Override
    public PolarMorph toPolar ()
    {
        double x = morphable.getPosition().getX();
        double y = morphable.getPosition().getY();
        return new PolarMorph(Math.toDegrees(Math.atan(dY / dX)),
                              Point.distance(x, y, x + dX, y + dY));
    }


    @Override
    public RectangularMorph toRectangular ()
    {
        return this;
    }
}
