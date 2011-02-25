/**
 * 
 */
package util.parser.grammar;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ITokenRule;


/**
 * @author Michael Ansel
 */
public class GrammarParseTreeNode
{
    private String myName;
    private List<GrammarParseTreeNode> myParameters;
    private AbstractParserRule myParserRule;
    private Map<String, AbstractParserRule> myRules;
    private String myToken;
    private ITokenRule myTokenRule;


    public GrammarParseTreeNode (String name)
    {
        this(name, new ArrayList<GrammarParseTreeNode>());
    }


    public GrammarParseTreeNode (String name,
                                 List<GrammarParseTreeNode> parameters)
    {
        myName = name;
        myParameters = parameters;
        myParserRule = null;
        myRules = null;
    }


    public GrammarParseTreeNode (String name, String token)
    {
        myName = name;
        myParameters = null;
        myToken = token;
        myParserRule = null;
        myRules = null;
    }


    public String getName ()
    {
        return myName;
    }


    public void setRules (Map<String, AbstractParserRule> rules)
    {
        if (myRules != null) return;
        myRules = rules;
        if (myParameters != null) for (GrammarParseTreeNode param : myParameters)
            param.setRules(rules);
    }


    public AbstractParserRule toParserRule (final AbstractParser parser)
    {
        if (myParserRule != null) return myParserRule;
        if (myToken != null && myTokenRule == null && myParameters == null)
        {
            myTokenRule = parser.getLexer().getTokenRuleByName(myToken);
            if (myTokenRule == null)
            {
                throw new RuntimeException("Couldn't find token in lexer: " +
                                           myToken);
            }
        }
        if (myParameters == null && myTokenRule != null) return parser.ExactlyOne(myTokenRule);
        else if (myParameters != null && myTokenRule == null) if (parser.getRule(getName()) instanceof AnonymousRule) return null; // TODO
        myParserRule = new AbstractParserRule()
        {
            private boolean initializing;


            private Method getMethod (String name)
            {
                return getMethod(name, null);
            }


            private Method getMethod (String name, List<Object> parameters)
            {
                Method[] all_methods =
                    AbstractParser.class.getDeclaredMethods();
                ArrayList<Method> matching_methods = new ArrayList<Method>();
                for (Method method : all_methods)
                    if (method.getName().matches(name)) matching_methods.add(method);
                if (matching_methods.isEmpty()) throw new IllegalArgumentException("ParserRule not found: " +
                                                                                   name);
                if (matching_methods.size() == 1) return matching_methods.get(0);
                for (Method method : matching_methods)
                    if (method.isVarArgs()) return method;
                // TODO Pick the appropriate method based on the number of parameters passed...
                throw new UnsupportedOperationException("TODO: Pick the appropriate method based on the number of parameters passed...\n" +
                                                        matching_methods.toString());
            }


            @Override
            public void initializeRule ()
            {
                if (initialized()) return;
                if (initializing) return;
                initializing = true;
                System.out.println("Initializing ParserRule: " +
                                   this.toString() + " :: " +
                                   myParameters.toString());
                if (myRules.containsKey(getName()))
                {
                    AbstractParserRule rule = myRules.get(getName());
                    if (rule instanceof AnonymousRule)
                    {
                        rule =
                            ((AnonymousRule) rule).toParserRule(parser,
                                                                myParameters);
                    }
                    setRule(rule);
                    return;
                }
                List<AbstractParserRule> params =
                    new ArrayList<AbstractParserRule>();
                for (GrammarParseTreeNode param : myParameters)
                    if (myRules.containsKey(param.getName())) params.add(myRules.get(param.getName()));
                    else params.add(param.toParserRule(parser));
                for (AbstractParserRule param : params)
                    param.initializeRule();
                AbstractParserRule[] paramsArray =
                    new AbstractParserRule[params.size()];
                paramsArray = params.toArray(paramsArray);

                try
                {
                    Method m = getMethod(getName());
                    setRule((AbstractParserRule) m.invoke(parser,
                                                          m.getParameterTypes()[0].cast(paramsArray)));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    throw new RuntimeException("Error building ParserRules: " +
                                               getName() + " :: " +
                                               params.toString() + e.toString());
                }
                myRules = null;
            }
        };
        return myParserRule;
    }


    @Override
    public String toString ()
    {
        if (myParameters == null) return String.format("GrammarParseTreeNode<%s(%s)>",
                                                       getName(),
                                                       myToken.toString());
        else return String.format("GrammarParseTreeNode<%s(%s)>",
                                  getName(),
                                  myParameters.toString());
    }
}
