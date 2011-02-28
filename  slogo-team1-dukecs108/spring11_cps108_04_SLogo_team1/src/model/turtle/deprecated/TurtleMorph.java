package model.turtle.deprecated;
import model.line.Line;
import model.turtle.IMorphable;

  /**
     * Carries the changes associated with each movement Used to update the
     * turtles current position based on the mighty morphin qualities.
     * 
     * @author Julian
     */
    public class TurtleMorph
    {
        private double dX;
        private double dY;
        private double dAngle;
        private IMorphable myTurtle;
        public Line myLine;


        public TurtleMorph (double dx, double dy, double dangle)
        {
            dX = dx;
            dY = dy;
            dAngle = dangle;
        }

    }