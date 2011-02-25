package slogo.model.turtle;

import java.util.ArrayList;
import java.util.List;
import slogo.util.Line;


public class PolarMorph implements Morph
{

    public double dAngle;
    public double dRadius;
    private List<Line> lines;
    private IMorphable morphable;


    public PolarMorph ()
    {
        this(0, 0);
    }


    public PolarMorph (double dRadius, double dAngle)
    {
        this(dRadius, dAngle, null);
    }


    public PolarMorph (double dRadius, double dAngle, IMorphable morphable)
    {
        this(dRadius, dAngle, morphable, new ArrayList<Line>());
    }


    public PolarMorph (double dRadius,
                       double dAngle,
                       IMorphable morphable,
                       List<Line> lines)
    {
        this.dRadius = dRadius;
        this.dAngle = dAngle;
        this.morphable = morphable;
        this.lines = lines;
    }


    @Override
    public List<Line> getLines ()
    {
        return this.lines;
    }


    @Override
    public IMorphable getMorphable ()
    {
        return this.morphable;
    }


    @Override
    public PolarMorph toPolar ()
    {
        return this;
    }


    @Override
    public RectangularMorph toRectangular ()
    {
        return new RectangularMorph(dRadius * Math.cos(Math.toRadians(dAngle)),
                                    dRadius * Math.sin(Math.toRadians(dAngle)),
                                    morphable,
                                    lines);
    }

}
