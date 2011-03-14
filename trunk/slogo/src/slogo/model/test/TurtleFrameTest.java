package slogo.model.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import slogo.util.drawables2D.Line;
import util.parser.ParserException;


public class TurtleFrameTest
{

    private static final Dimension DEFAULT_DIM = new Dimension(500, 500);


    /**
     * @param args
     */
    public static void main (String[] args)
    {
        List<Line> lines = new ArrayList<Line>();

        lines.add(new Line(new Point(10, 10), new Point(400, 400)));
        lines.add(new Line(new Point(400, 400), new Point(400, 900)));
        lines.add(new Line(new Point(400, 900), new Point(10, 10)));

        JFrame frame = new JFrame();
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Turtle turtle = createTestTurtle();
        Arena a = new Arena(turtle);
        TurtleTestPanel panel = new TurtleTestPanel(a);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        panel.setPreferredSize(DEFAULT_DIM);
        panel.paintComponent(panel.getGraphics());
        frame.pack();

        System.out.println("Running parser...");
        runTurtle(a, panel);
        System.out.println("done!");
        long totalTime =
            slogo.ParserTimer.createFromFactory + slogo.ParserTimer.run +
                    slogo.ParserTimer.createCommandFromFactory +
                    slogo.ParserTimer.runCommand;
        System.out.println("Create parser from factory: " +
                           slogo.ParserTimer.createFromFactory + " " + 100 *
                           slogo.ParserTimer.createFromFactory / totalTime +
                           "%");
        System.out.println("Run parser: " + slogo.ParserTimer.run + " " + 100 *
                           slogo.ParserTimer.run / totalTime + "%");
        System.out.println("Create command parser from factory: " +
                           slogo.ParserTimer.createCommandFromFactory + " " +
                           100 * slogo.ParserTimer.createCommandFromFactory /
                           totalTime + "%");
        System.out.println("Run command parser: " +
                           slogo.ParserTimer.runCommand + " " + 100 *
                           slogo.ParserTimer.runCommand / totalTime + "%");
        System.out.println("Expressions parsed: " +
                           slogo.ParserTimer.expressionCount);
    }


    private static void runTurtle (Arena a, TurtleTestPanel panel)
    {
        tryParseAndEvaluate(a, "seth 90"); // TODO shouldn't 0 be straight up/north?
        tryParseAndEvaluate(a, "setxy 250 250"); // TODO 0,0 should be in the center of the canvas
        panel.paintComponent(panel.getGraphics());

        for (int i = 0; i < 3; i++)
        {
            parsedCubeMove(a, 100, 50);
            tryParseAndEvaluate(a, "rt 90");
        }
        panel.paintComponent(panel.getGraphics());

        // star
        tryParseAndEvaluate(a, "seth 90"); // TODO shouldn't 0 be straight up/north?
        tryParseAndEvaluate(a, "setxy 100 100"); // TODO 0,0 should be in the center of the canvas
        tryParseAndEvaluate(a,
                            "repeat 5 [ fd 100 lt 90 fd 33 rt 90 repeat 5 [ fd 100 rt 144 ] rt 90 fd 33 lt 90 rt 144 ]");
        panel.paintComponent(panel.getGraphics());
    }


    private static Turtle createTestTurtle ()
    {
        Turtle turtle = new Turtle("1");

        basicSunMove(turtle, 100, new Point(250, 250));

        return turtle;
    }


    private static void basicArcMove (Turtle turtle, int r)
    {
        for (int i = 0; i < r / 3; i++)
        {
            turtle.move(r / 75);
            turtle.rotate(-360 / r);
        }

    }


    private static void basicCircleMove (Turtle turtle, int r)
    {
        turtle.getPen().putUp();
        turtle.move(new Point((int) turtle.getPosition().getX() - r,
                              (int) turtle.getPosition().getY()));
        turtle.getPen().putDown();

        for (int i = 0; i < r * 2; i++)
        {
            turtle.move(r / 30);
            turtle.rotate(-360 / r);
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


    private static void tryParseAndEvaluate (Arena a, String expression)
    {
        slogo.ParserTimer.expressionCount++;
        try
        {
            ((Expression) (SlogoParser.parse(expression).getList().get(0))).evaluate(a);
        }
        catch (ParserException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse: " + e.toString());
        }
    }


    private static void parsedRotateAndMove (Arena a, double d)
    {
        tryParseAndEvaluate(a, "rt 90");
        tryParseAndEvaluate(a, "fd " + (int) Math.round(d));
    }


    private static void parsedSquareMove (Arena a, double d)
    {
        for (int i = 0; i < 4; i++)
            parsedRotateAndMove(a, d);
    }


    private static void parsedCubeMove (Arena a, double d, double e)
    {
        for (int i = 0; i < 4; i++)
        {
            parsedMove(a, e, e);
            parsedMove(a, -e, -e);
            parsedRotateAndMove(a, d);
        }
        parsedMove(a, e, e);
        parsedSquareMove(a, d);
    }


    private static void parsedMove (Arena a, double x, double y)
    {
        String expression =
            String.format("setxy (xcor + %d) (ycor + %d)",
                          (int) Math.round(x),
                          (int) Math.round(y));
        tryParseAndEvaluate(a, expression);
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
        turtle.move(new Point2D.Double(turtle.getPosition().getX() + e,
                                       turtle.getPosition().getY() + e));
        basicSquareMove(turtle, d);
    }


    private static void moveAndReturn (Turtle turtle, double x, double y)
    {
        turtle.move(new Point2D.Double(turtle.getPosition().getX() + x,
                                       turtle.getPosition().getY() + y));
        turtle.move(new Point2D.Double(turtle.getPosition().getX() - x,
                                       turtle.getPosition().getY() - y));
    }


    private static void basicCrossMove (Turtle turtle, double d)
    {
        turtle.move(100 * d);
        turtle.rotate(90);
        turtle.move(40 * d);
        turtle.rotate(90);
        turtle.move(100 * d);
        turtle.rotate(-90);
        turtle.move(100 * d);
        turtle.rotate(90);
        turtle.move(40 * d);
        turtle.rotate(90);
        turtle.move(100 * d);
        turtle.rotate(-90);
        turtle.move(100 * d);
        turtle.rotate(90);
        turtle.move(40 * d);
        turtle.rotate(90);
        turtle.move(100 * d);
        turtle.rotate(-90);
        turtle.move(200 * d);
        turtle.rotate(90);
        turtle.move(40 * d);
        turtle.rotate(90);
        turtle.move(200 * d);
        turtle.rotate(-90);
        System.out.println(turtle.getPosition().getLocation());
    }


    private static void basicSunMove (Turtle turtle, int r, Point center)
    {
        turtle.getPen().putUp();
        turtle.move(new Point((int) center.getX() - r, (int) center.getY()));
        turtle.getPen().putDown();

        for (int i = 0; i < r * 2; i++)
        {
            turtle.move(r / 30);

            turtle.rotate(90);
            turtle.move(r / 3);
//            basicSquareMove(turtle, 500);
            turtle.rotate(-180);
            turtle.move(r / 3);
            turtle.rotate(90);
            turtle.rotate(-360 / r);
        }

        turtle.move(new Point((int) center.getX() - r / 3, (int) center.getY() -
                                                           r / 3));
        basicCircleMove(turtle, r / 10);
        turtle.move(new Point((int) center.getX() + r / 3, (int) center.getY() -
                                                           r / 3));
        basicCircleMove(turtle, r / 10);
        turtle.move(new Point((int) center.getX() - r / 3, (int) center.getY() +
                                                           r / 3));
        turtle.setHeading(45);
        basicArcMove(turtle, r);

    }
}
