package slogo.model.parser.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests
{

    public static Test suite ()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(SlogoParserFactoryTest.class);
        suite.addTestSuite(SlogoLexerTest.class);
        suite.addTestSuite(SlogoParserTest.class);
        //$JUnit-END$
        return suite;
    }

}
