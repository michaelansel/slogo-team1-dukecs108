package slogo.model.expression.command.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests
{

    public static Test suite ()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(XCorTest.class);
        suite.addTestSuite(HomeTest.class);
        suite.addTestSuite(SetXYTest.class);
        suite.addTestSuite(LeftTest.class);
        suite.addTestSuite(YCorTest.class);
        suite.addTestSuite(ForwardTest.class);
        suite.addTestSuite(BackwardTest.class);
        suite.addTestSuite(RightTest.class);
        //$JUnit-END$
        return suite;
    }

}
