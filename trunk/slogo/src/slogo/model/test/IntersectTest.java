package slogo.model.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.awt.geom.Point2D;
import org.junit.Before;
import org.junit.Test;
import slogo.model.arena.Arena;
import slogo.model.arena.turtle.Turtle;
import slogo.model.expression.Expression;
import slogo.model.parser.SlogoParser;
import slogo.util.drawables2D.Line;
import util.parser.ParserException;
import util.parser.ParserResult;
import junit.framework.TestCase;

public class IntersectTest extends TestCase
{
    private Line testLine;
    

    @Before
    public void setUp () throws Exception
    {
        testLine = new Line(200,500, 500, 500);
    }


//    @Test
//    public final void testHorizonalIntersect () throws ParserException
//    {
//        simpleTest(new Line(200, 300 , 500, 300), new Point2D.Double(300,300));
//    }

    @Test
    public final void testVerticalIntersect () throws ParserException
    {
        simpleTest(new Line(300, 0 , 300, 500), new Point2D.Double(300,500));
    }
    

//    @Test
//    public final void testEndPointIntersect () throws ParserException
//    {
//        simpleTest(new Line(200, 200 , 300, 500), new Point2D.Double(200,200));
//    }


    private void simpleTest (Line test, Point2D expected) throws ParserException
    {
        assertTrue(testLine.intersectsLine(test));
        assertEquals(expected, testLine.findIntersect(test));
    }
}
