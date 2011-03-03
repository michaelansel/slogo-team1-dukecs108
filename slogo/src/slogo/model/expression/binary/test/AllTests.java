package slogo.model.expression.binary.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests
{

    public static Test suite ()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(SubtractTest.class);
        suite.addTestSuite(AddTest.class);
        //$JUnit-END$
        return suite;
    }

}
