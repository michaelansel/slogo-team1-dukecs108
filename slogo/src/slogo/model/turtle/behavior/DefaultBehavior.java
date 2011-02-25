package slogo.model.turtle.behavior;

import slogo.model.turtle.Morph;
import slogo.util.Line;


public class DefaultBehavior implements IBehavior
{

    @Override
    public Morph evaluate (Morph morph)
    {
        morph.getLines().add(new Line(morph));
        return morph;
    }

}
