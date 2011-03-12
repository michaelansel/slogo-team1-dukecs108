package util.parser.rule;

import java.util.logging.Level;
import util.parser.AbstractParserRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.TokenManager;


public class ZeroOrMoreRule extends AbstractParserRule
{
    private AbstractParserRule mySequence;


    public ZeroOrMoreRule (Object[] objects)
    {
        mySequence = new SequenceRule(objects);
    }


    @Override
    public ParserResult evaluate (TokenManager tokenManager)
        throws ParserException
    {
        TokenManager.Checkpoint checkpoint = null;
        ParserResult result = new ParserResult();
        while (true)
        {
            checkpoint = tokenManager.storeCheckpoint();
            try
            {
                result.merge(mySequence.evaluate(tokenManager));
            }
            catch (ParserException e)
            {
                tokenManager.restoreCheckpoint(checkpoint);
                break;
            }
        }
        if (logger.isLoggable(Level.FINER)) logger.finer("ZeroOrMore returning: " +
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
        return "ZeroOrMoreRule";
    }
}
