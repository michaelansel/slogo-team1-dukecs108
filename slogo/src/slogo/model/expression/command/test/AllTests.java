package slogo.model.expression.command.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests
{

    public static Test suite ()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(AskTest.class);
        suite.addTestSuite(XCorTest.class);
        suite.addTestSuite(HomeTest.class);
        suite.addTestSuite(ToTest.class);
        suite.addTestSuite(LeftTest.class);
        suite.addTestSuite(RepeatTest.class);
        suite.addTestSuite(PenDownPTest.class);
        suite.addTestSuite(ClearScreenTest.class);
        suite.addTestSuite(IfElseTest.class);
        suite.addTestSuite(IfTest.class);
        suite.addTestSuite(PenDownTest.class);
        suite.addTestSuite(SetXYTest.class);
        suite.addTestSuite(RandomTest.class);
        suite.addTestSuite(ShowTurtleTest.class);
        suite.addTestSuite(HeadingTest.class);
        suite.addTestSuite(YCorTest.class);
        suite.addTestSuite(ForwardTest.class);
        suite.addTestSuite(PenUpTest.class);
        suite.addTestSuite(SetHeadingTest.class);
        suite.addTestSuite(BackwardTest.class);
        suite.addTestSuite(RightTest.class);
        suite.addTestSuite(HideTurtleTest.class);
        suite.addTestSuite(TellTest.class);
        //$JUnit-END$
        return suite;
    }

}
