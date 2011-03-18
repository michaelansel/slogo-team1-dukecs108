/**
 * 
 */
package util.parser;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Michael Ansel
 */
public abstract class AbstractParserRule
{
    protected Logger logger =
        Logger.getLogger(AbstractParserRule.class.getName());
    private IResultHandler myResultHandler = null;
    private AbstractParserRule myRule = null;

    private String myRuleName = "?";


    public ParserResult evaluate (TokenManager tokenManager)
        throws ParserException
    {
        if (!initialized()) throw new RuntimeException("Uninitialized Rule: " +
                                                       toString());
        return processResult(myRule.evaluate(tokenManager));
    }


    public boolean initialized ()
    {
        return myRule != null;
    }


    public void initializeRule ()
    {
        if (initialized()) return;
        throw new UnsupportedOperationException("Abstract implementation not overridden");
    }


    protected void matchFailed (String message) throws MatchFailedException
    {
        if (!message.isEmpty()) message += "\n";
        logger.log(Level.FINEST, "Match Failed: {0}", message);
        throw new MatchFailedException(message);
    }


    protected void parseError (String message) throws ParserException
    {
        if (!message.isEmpty()) message += "\n";
        logger.log(Level.WARNING, "Parse Error: {0}", message);
        throw new ParserException(message);
    }


    protected ParserResult processResult (ParserResult result)
        throws ParserException
    {
        if (myResultHandler != null) return myResultHandler.handleResult(result);
        return result;
    }


    public void setHandler (IResultHandler handler)
    {
        myResultHandler = handler;
    }


    public void setRule (AbstractParserRule rule)
    {
        myRule = rule;
    }


    public void setRuleName (String ruleName)
    {
        myRuleName = ruleName;
    }


    @Override
    public String toString ()
    {
        return "AbstractParserRule<" + myRuleName + ">";
    }
}
