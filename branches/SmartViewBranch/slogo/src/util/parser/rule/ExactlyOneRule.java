package util.parser.rule;

import java.util.logging.Level;
import util.parser.AbstractParserRule;
import util.parser.ITokenRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.Token;
import util.parser.TokenManager;


/**
 * @author Michael Ansel
 */
public class ExactlyOneRule extends AbstractParserRule
{
    public static AbstractParserRule create (Object object)
    {
        if (object instanceof AbstractParserRule) return (AbstractParserRule) object;
        else if (object instanceof ITokenRule) return new ExactlyOneRule((ITokenRule) object);
        else throw new IllegalArgumentException("Must provide either a parser rule or a token rule.");
    }

    private AbstractParserRule myRule;
    private ITokenRule myTokenRule;


    public ExactlyOneRule (ITokenRule tokenRule)
    {
        myTokenRule = tokenRule;
        myRule = null;
    }


    public ExactlyOneRule (Object ... objects)
    {
        if (objects[0] instanceof ITokenRule) myTokenRule =
            (ITokenRule) objects[0];
        else if (objects[0] instanceof AbstractParserRule) myRule =
            (AbstractParserRule) objects[0];
        else throw new IllegalArgumentException("Unknown object type: " +
                                                objects[0].toString());
    }


    @Override
    public ParserResult evaluate (TokenManager tokenManager)
        throws ParserException
    {
        if (myRule != null) return myRule.evaluate(tokenManager);

        ParserResult result = new ParserResult();
        Token nextToken = null;
        if (tokenManager.hasNextToken() &&
            (nextToken = tokenManager.peekNextToken()).tokenRule == myTokenRule)
        {
            result.add(tokenManager.consumeNextToken());
            if (logger.isLoggable(Level.FINER)) logger.finer("ExactlyOne returning: " +
                                                             result.toString());
            return processResult(result);
        }
        if (logger.isLoggable(Level.FINER)) logger.finer(String.format("ExactlyOne Token Mismatch! Expected: %s Got: %s",
                                                                       myTokenRule,
                                                                       nextToken));
        matchFailed("Token Mismatch!");
        return null;
    }


    @Override
    public void initializeRule ()
    {}


    @Override
    public void setRule (AbstractParserRule rule)
    {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString ()
    {
        return "ExactlyOneRule(" + myTokenRule.toString() + ")";
    }
}
