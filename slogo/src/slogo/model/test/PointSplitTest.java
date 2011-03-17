package slogo.model.test;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;
import slogo.util.drawables2D.Line;
import util.parser.ParserException;
import junit.framework.TestCase;

public class PointSplitTest extends TestCase
{

private Line testLine;
    

    @Before
    public void setUp () throws Exception
    {
        testLine = new Line(0, 0, 4, 4);
    }
    
    @Test
    public final void test()
    {
        Collection<Point2D> expected = new TreeSet<Point2D>();
        
        expected.add(new Point2D.Double(0,0));
        expected.add(new Point2D.Double(1,1));
        expected.add(new Point2D.Double(2,2));
        expected.add(new Point2D.Double(3,3));
        expected.add(new Point2D.Double(4,4));

        System.out.println(testLine.splitToPoints());
    }
    
}
