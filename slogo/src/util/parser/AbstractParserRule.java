/**
 * 
 */
package util.parser;

/**
 * @author Michael Ansel
 */
public abstract class AbstractParserRule
{
    private IResultHandler myResultHandler = null;
    private AbstractParserRule myRule = null;
    private String myRuleName = null;


    public ParserResult evaluate () throws ParserException
    {
        if (!initialized()) throw new RuntimeException("Uninitialized Rule: " +
                                                       toString());
        return processResult(myRule.evaluate());
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
        return String.format("AbstractParserRule<%s>",
                             (myRuleName != null) ? myRuleName : "?");
    }
}
