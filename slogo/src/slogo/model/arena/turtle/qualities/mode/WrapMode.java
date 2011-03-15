package slogo.model.arena.turtle.qualities.mode;

import java.util.List;
import slogo.util.drawables2D.IDraw2D;

public class WrapMode extends DrawModeDecorator
{

    @Override
    public List<IDraw2D> applyMode (List<IDraw2D> list)
    {
        //Implement wrapping function
        return list;
    }

}
