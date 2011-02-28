package slogo.model.arena.turtle.qualities.behavior;

import java.util.Random;
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
