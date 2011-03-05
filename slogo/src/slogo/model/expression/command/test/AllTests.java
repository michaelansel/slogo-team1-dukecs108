package slogo.model.expression.command.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests
{

    public static Test suite ()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(ForwardTest.class);
        suite.addTestSuite(RightTest.class);
        //$JUnit-END$
        return suite;
    }

}
