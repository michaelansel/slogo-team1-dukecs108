package slogo.model.arena.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AllTests extends TestCase
{

    public static Test suite ()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(ArenaTurtleTest.class);
        suite.addTestSuite(ArenaTest.class);
        //$JUnit-END$
        return suite;
    }

}
