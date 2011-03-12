package util.parser;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Michael Ansel
 */
public class TokenManager
{
    public static class Checkpoint
    {
        public List<Token> myTokens;


        public Checkpoint (List<Token> tokens)
        {
            myTokens = new ArrayList<Token>(tokens);
        }
    }

    public List<Token> myTokens;


    public TokenManager (List<Token> results)
    {
        myTokens = new ArrayList<Token>(results);
    }


    public Token consumeNextToken ()
    {
        return myTokens.remove(0);
    }


    public List<Token> getTokens ()
    {
        return myTokens;
    }


    public boolean hasNextToken ()
    {
        return (myTokens.size() > 0);
    }


    public Token peekNextToken ()
    {
        return myTokens.get(0);
    }


    public void restoreCheckpoint (Checkpoint checkpoint)
    {
        myTokens = checkpoint.myTokens;
    }


    public Checkpoint storeCheckpoint ()
    {
        return new Checkpoint(myTokens);
    }
}
