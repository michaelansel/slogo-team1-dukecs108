package slogo;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests
{

    public static Test suite ()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        suite.addTest(slogo.model.arena.test.AllTests.suite());
        suite.addTest(slogo.model.arena.turtle.test.AllTests.suite());
        suite.addTest(slogo.model.expression.binary.test.AllTests.suite());
        suite.addTest(slogo.model.expression.command.test.AllTests.suite());
        suite.addTest(slogo.model.expression.test.AllTests.suite());
        suite.addTest(slogo.model.parser.test.AllTests.suite());
        suite.addTest(util.parser.grammar.test.AllTests.suite());
        //$JUnit-BEGIN$

        //$JUnit-END$
        return suite;
    }

}
