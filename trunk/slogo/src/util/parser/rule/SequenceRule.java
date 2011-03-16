package util.parser.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import util.parser.AbstractParserRule;
import util.parser.MatchFailedException;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.TokenManager;


public class SequenceRule extends AbstractParserRule
{
    private List<AbstractParserRule> myRules =
        new ArrayList<AbstractParserRule>();


    public SequenceRule (Object[] objects)
    {
        for (Object o : objects)
            myRules.add(ExactlyOneRule.create(o));
    }


    public ParserResult evaluate (TokenManager tokenManager)
        throws ParserException
    {
        ParserResult result = new ParserResult();
        if (logger.isLoggable(Level.FINER)) logger.finer(myRules.toString());
        for (AbstractParserRule rule : myRules)
        {
            try
            {
                result.merge(rule.evaluate(tokenManager));
            }
            catch (MatchFailedException e)
            {
                throw new MatchFailedException(/*
                                                * String.format(
                                                * "Sequence rule failed while parsing %s.\nRemaining Tokens: %s"
                                                * , rule.toString(),
                                                * myTokens.toString())
                                                */"Sequence rule match failure.", e);
            }
        }
        if (logger.isLoggable(Level.FINER)) logger.finer("Sequence returning: " +
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
        return "SequenceRule";
    }
}
