package util.parser.grammar.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests
{

    public static Test suite ()
    {
        TestSuite suite = new TestSuite("Test for util.parser.grammar.test");
        //$JUnit-BEGIN$
        suite.addTestSuite(GrammarLexerTest.class);
        //$JUnit-END$
        return suite;
    }

}
