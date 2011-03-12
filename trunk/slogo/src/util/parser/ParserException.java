package util.parser;

import java.util.ArrayList;
import java.util.List;


public class ParserException extends Exception
{
    private static final long serialVersionUID = 1L;
    private List<Token> myTokens;


    public ParserException (String message)
    {
        super(message);
        myTokens = new ArrayList<Token>();
    }


    public ParserException (String message, List<Token> tokens)
    {
        myTokens = new ArrayList<Token>(tokens);
    }


    public ParserException (String message, Throwable throwable)
    {
        super(message, throwable);
        myTokens = new ArrayList<Token>();
    }


    public List<Token> getTokens ()
    {
        return myTokens;
    }


    @Override
    public String toString ()
    {
        if (!myTokens.isEmpty()) return super.toString() +
                                        "\n Remaining Tokens: " +
                                        myTokens.toString();
        else return super.toString();
    }

}
