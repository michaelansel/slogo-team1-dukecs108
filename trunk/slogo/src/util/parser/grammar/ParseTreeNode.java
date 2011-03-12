/**
 * 
 */
package util.parser.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.parser.AbstractParserRule;
import util.parser.ITokenRule;
import util.parser.ParserException;
import util.parser.rule.ExactlyOneRule;


/**
 * @author Michael Ansel
 */
public class ParseTreeNode implements Cloneable
{
    public enum Type
    {
        IndirectCall, Token

    }

    private String myName;
    List<ParseTreeNode> myParameters; // TODO change visibility
    private AbstractParserRule myParserRule;
    String myToken; // TODO change visibility
    private Type myType;


    private ParseTreeNode ()
    {
        myName = "";
        myParameters = null;
        myParserRule = null;
        myToken = "";
        myType = null;
    }


    public ParseTreeNode (String ruleName)
    {
        this();
        myName = ruleName;
        myType = Type.IndirectCall;
    }


    public ParseTreeNode (String name, List<ParseTreeNode> parameters)
    {
        this();
        myName = name;
        myParameters = parameters;
        myType = Type.IndirectCall;
    }


    public ParseTreeNode (String name, String token)
    {
        this();
        myName = name;
        myToken = token;
        myType = Type.Token;
    }


    /**
     * @return A deep-clone of this ParseTreeNode
     */
    @Override
    public ParseTreeNode clone ()
    {
        ParseTreeNode node = new ParseTreeNode();
        node.myName = myName;
        if (myParameters != null)
        {
            node.myParameters = new ArrayList<ParseTreeNode>();
            for (ParseTreeNode param : myParameters)
                node.myParameters.add(param.clone());
        }
        else
        {
            node.myParameters = null;
        }
        node.myParserRule = myParserRule;
        node.myToken = myToken;
        node.myType = myType;
        return node;
    }


    public String getName ()
    {
        return myName;
    }


    public AbstractParserRule toParserRule (Map<String, ITokenRule> tokenRules,
                                            Map<String, AbstractParserRule> definedRules)
        throws ParserException
    {
        if (myParserRule != null) return myParserRule; // Short-circuit for already-resolved rules

        if (myType == Type.Token)
        {
            ITokenRule tokenRule = tokenRules.get(myToken);
            if (tokenRule == null) throw new ParserException("Couldn't find token in lexer: " +
                                                             myToken);
            return (myParserRule = ExactlyOneRule.create(tokenRule));
        }
        if (myType == Type.IndirectCall &&
            definedRules.containsKey(getName()) &&
            definedRules.get(getName()) != null)
        {
            // rule already resolved
            return definedRules.get(getName());
        }
        return (myParserRule =
            new DynamicParserRule(getName(),
                                  myParameters,
                                  tokenRules,
                                  definedRules));
    }


    @Override
    public String toString ()
    {
        if (myType == Type.Token) return String.format("ParseTreeNode(%s(%s))",
                                                       getName(),
                                                       myToken);
        else if (myParameters != null) return String.format("ParseTreeNode<%s(%s)>",
                                                            getName(),
                                                            myParameters.toString());
        else return String.format("ParseTreeNode<%s()>", getName());
    }
}
