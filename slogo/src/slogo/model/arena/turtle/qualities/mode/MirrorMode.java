package slogo.model.arena.turtle.qualities.mode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import slogo.model.arena.turtle.qualities.behavior.BehaviorDecorator;
import slogo.model.arena.turtle.qualities.positioning.IPosition;
import slogo.util.line.Line;


public class MirrorMode extends DrawModeDecorator
{

    @Override
    public List<Line> applyMode (Collection<Line> lines)
    {
        List<Line> mirrored = new ArrayList<Line>();
        for(Line line: lines){
            mirrored.add(line);
            mirrored.add(line.mirror());
        }
        
        return (List<Line>) myDecoratedMode.applyMode(mirrored);
    }

    



}