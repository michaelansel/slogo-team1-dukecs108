package util.parser.rule;

import java.util.logging.Level;
import util.parser.AbstractParserRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.TokenManager;


public class OptionalRule extends AbstractParserRule
{
    private AbstractParserRule myRule;


    public OptionalRule (Object ... objects)
    {
        myRule = ExactlyOneRule.create(objects[0]);
    }


    @Override
    public ParserResult evaluate (TokenManager tokenManager)
        throws ParserException
    {
        TokenManager.Checkpoint checkpoint = null;
        ParserResult result = new ParserResult();
        checkpoint = tokenManager.storeCheckpoint();
        try
        {
            result.merge(myRule.evaluate(tokenManager));
        }
        catch (ParserException e)
        {
            tokenManager.restoreCheckpoint(checkpoint);
        }
        if (logger.isLoggable(Level.FINER)) logger.finer("Optional returning: " +
                                                         result.toString());
        return processResult(result);
    }


    @Override
    public void setRule (AbstractParserRule rule)
    {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString ()
    {
        return "OptionalRule(" + myRule.getClass().toString() + ")";
    }
}
