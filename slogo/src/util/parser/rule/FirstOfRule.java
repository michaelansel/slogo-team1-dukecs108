package util.parser.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import util.parser.AbstractParserRule;
import util.parser.ITokenRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.TokenManager;


/**
 * @author Michael Ansel
 */
public class FirstOfRule extends AbstractParserRule
{
    private List<AbstractParserRule> myRules;


    public FirstOfRule (Object ... objects)
    {
        myRules = new ArrayList<AbstractParserRule>();
        for (Object o : objects)
        {
            if (o instanceof AbstractParserRule) myRules.add((AbstractParserRule) o);
            else if (o instanceof ITokenRule) myRules.add(new ExactlyOneRule((ITokenRule) o));
            else throw new RuntimeException("Invalid object: " + o.toString());
        }
    }


    @Override
    public ParserResult evaluate (TokenManager tokenManager)
        throws ParserException
    {
        TokenManager.Checkpoint checkpoint = null;
        if (logger.isLoggable(Level.FINER)) logger.finer("FirstOf: " +
                                                         myRules.toString());
        for (AbstractParserRule rule : myRules)
        {
            checkpoint = tokenManager.storeCheckpoint();
            if (logger.isLoggable(Level.FINER)) logger.finer("--Testing: " +
                                                             rule.toString());
            try
            {
                ParserResult result = rule.evaluate(tokenManager);
                if (logger.isLoggable(Level.FINER)) logger.finer("FirstOf returning: " +
                                                                 result.toString());
                return processResult(result);
            }
            catch (ParserException e)
            {
                if (logger.isLoggable(Level.FINER)) logger.finer("--Caught! Restoring from checkpoint... (" +
                                                                 e.toString()
                                                                  .replaceAll("\n",
                                                                              " | ") +
                                                                 ")");
                tokenManager.restoreCheckpoint(checkpoint);
                if (logger.isLoggable(Level.FINER)) logger.finer("Tokens: " +
                                                                 tokenManager.getTokens()
                                                                             .toString());
            }
        }
        parseError("FirstOf never matched!");
        return null;
    }


    @Override
    public void setRule (AbstractParserRule rule)
    {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString ()
    {
        return "FirstOfRule(" + myRules.toString() + ")";
    }
}
