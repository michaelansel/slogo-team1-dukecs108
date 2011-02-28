package model.turtle.qualities.behavior;

import java.awt.Point;
import java.util.Collection;
import java.util.Random;
import model.line.Line;
import model.turtle.qualities.positioning.IPosition;
import model.turtle.qualities.trace.Trace;


public class DrunkenBehavior extends BehaviorDecorator {

    @Override
    public Line applyBehavior (Line line)
    {
        
        inebriate(line);
        
        return myDecoratedBehavior.applyBehavior(line);
    }

    private void inebriate (Line line)
    {
        Random randomGenerator = new Random();
        
        
        
        
        
    }

    
    
   
}
