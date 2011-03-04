package slogo.model.test;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.arena.turtle.qualities.positioning.Position;
import slogo.util.Line;

public class TurtleFrameTest
{

    private static final Dimension DEFAULT_DIM = new Dimension(500,500);

    /**
     * @param args
     */
    public static void main (String[] args)
    {
        List<Line> lines = new ArrayList<Line>();
        
        lines.add(new Line(new Point(10,10), new Point(400,400)));
        lines.add(new Line(new Point(400,400), new Point(400,900)));
        lines.add(new Line(new Point(400,900), new Point(10,10)));
        
        JFrame frame = new JFrame();
        frame.setBackground(Color.white);
        
        Turtle turtle =createTestTurtle ();
        Arena a = new Arena(turtle);
        TurtleTestPanel panel = new TurtleTestPanel(a);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        panel.setPreferredSize(DEFAULT_DIM);
        panel.paintComponent(panel.getGraphics());
        frame.pack();

    }

    private static Turtle createTestTurtle ()
    {
        Turtle turtle = new Turtle("1");
        
        basicSunMove(turtle, 100, new Point(250,250));
        
        return turtle;
    }

    private static void basicArcMove (Turtle turtle, int r)
    {
        for (int i = 0; i < r/3; i++  ){
            turtle.move(r/75);
            turtle.rotate(-360/r);
        }
        
    }

    private static void basicCircleMove (Turtle turtle, int r)
    {
        turtle.getPen().putUp();
        turtle.moveInvisible(new Point((int) turtle.getPosition().getX() - r, (int) turtle.getPosition().getY()));
        turtle.getPen().putDown();

        for (int i = 0; i < r*2; i++  ){
            turtle.move(r/30);
            turtle.rotate(-360/r);
        }
        
    }

    private static void basicSquareMove (Turtle turtle, double d)
    {
        
        turtle.rotate(90);
        turtle.move(d);
        turtle.rotate(90);
        turtle.move(d);
        turtle.rotate(90);
        turtle.move(d);
        turtle.rotate(90);
        turtle.move(d);
        
    }
    
    private static void basicCubeMove (Turtle turtle, double d, double e)
    {
        moveAndReturn(turtle, e, e);
        turtle.rotate(90);
        turtle.move(d);
        moveAndReturn(turtle, e, e);
        turtle.rotate(90);
        turtle.move(d);
        moveAndReturn(turtle, e, e);
        turtle.rotate(90);
        turtle.move(d);
        moveAndReturn(turtle, e, e);
        turtle.rotate(90);
        turtle.move(d);
        turtle.moveInvisible(new Point2D.Double(turtle.getPosition().getX()+e, turtle.getPosition().getY()+e));
        basicSquareMove ( turtle,  d);
    }
    
    private static void moveAndReturn(Turtle turtle, double x, double y){
        turtle.move(new Point2D.Double(turtle.getPosition().getX()+x, turtle.getPosition().getY()+y));
        turtle.move(new Point2D.Double(turtle.getPosition().getX()-x, turtle.getPosition().getY()-y));
    }
    
    private static void basicCrossMove (Turtle turtle, double d)
    {
        turtle.move(100*d);
        turtle.rotate(90);
        turtle.move(40*d);
        turtle.rotate(90);
        turtle.move(100*d);
        turtle.rotate(-90);
        turtle.move(100*d);
        turtle.rotate(90);
        turtle.move(40*d);
        turtle.rotate(90);
        turtle.move(100*d);
        turtle.rotate(-90);
        turtle.move(100*d);
        turtle.rotate(90);
        turtle.move(40*d);
        turtle.rotate(90);
        turtle.move(100*d);
        turtle.rotate(-90);
        turtle.move(200*d);
        turtle.rotate(90);
        turtle.move(40*d);
        turtle.rotate(90);
        turtle.move(200*d);
        turtle.rotate(-90);
        System.out.println(turtle.getPosition().getLocation());
    }

    private static void basicSunMove (Turtle turtle, int r, Point center)
    {
        turtle.getPen().putUp();
        turtle.moveInvisible(new Point((int) center.getX() - r, (int) center.getY()));
        turtle.getPen().putDown();

        for (int i = 0; i < r*2; i++  ){
            turtle.move(r/30);
            
            turtle.rotate(90);
            turtle.move(r/3);
//            basicSquareMove(turtle, 500);
            turtle.rotate(-180);
            turtle.move(r/3);
            turtle.rotate(90);
            turtle.rotate(-360/r);
        }
        
        turtle.moveInvisible(new Point((int)center.getX()-r/3, (int)center.getY()-r/3));
        basicCircleMove(turtle, r/10);
        turtle.moveInvisible(new Point((int)center.getX()+r/3, (int)center.getY()-r/3));
        basicCircleMove(turtle, r/10);
        turtle.moveInvisible(new Point((int)center.getX()-r/3, (int)center.getY()+r/3));    
        turtle.setHeading(45);
        basicArcMove(turtle, r);
        
    }
}