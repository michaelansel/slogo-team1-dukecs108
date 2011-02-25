/**
 * 
 */
package util.parser;

import java.util.List;


/**
 * @author Michael Ansel
 */
public class DummyLexer extends AbstractLexer
{

    public DummyLexer (List<Token> tokens)
    {
        super(tokens);
    }


    @SuppressWarnings("unchecked")
    @Override
    protected List<Token> getInput ()
    {
        return (List<Token>) super.getInput();
    }


    @Override
    public List<Token> tokenize ()
    {
        return getInput();
    }

}
