package slogo.model.turtle.qualities.behavior;

import java.awt.Point;
import java.util.Collection;
import java.util.Random;
import slogo.model.turtle.qualities.positioning.IPosition;
import slogo.util.Trace.Trace;
import slogo.util.line.Line;


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
