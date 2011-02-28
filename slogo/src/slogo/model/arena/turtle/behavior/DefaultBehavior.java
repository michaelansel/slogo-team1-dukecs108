package slogo.model.arena.turtle.behavior;

import slogo.deprecated.Line;
import slogo.deprecated.Morph;


public class DefaultBehavior implements IBehavior
{

    @Override
    public Morph evaluate (Morph morph)
    {
        morph.getLines().add(new Line(morph));
        return morph;
    }

}
