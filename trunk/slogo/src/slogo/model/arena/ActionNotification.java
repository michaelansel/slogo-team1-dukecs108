package slogo.model.arena;

import slogo.model.action.Action;
import slogo.model.arena.turtle.Turtle;

/**
 * @author Michael Ansel
 *
 */
public class ActionNotification
{
    public Action myAction;
    public Turtle myTurtle;

    public ActionNotification (Action action, Turtle turtle)
    {
        myAction = action;
        myTurtle = turtle;
    }
}
