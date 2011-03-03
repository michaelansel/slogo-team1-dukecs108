package util.parser.grammar;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ParserException;


final class DynamicParserRule extends AbstractParserRule
{
    private List<ParseTreeNode> children;
    private Map<String, AbstractParserRule> definedRules;
    private boolean initializing;
    private final AbstractParser parser;
    private String ruleName;


    DynamicParserRule (AbstractParser parser,
                       String ruleName,
                       List<ParseTreeNode> children,
                       Map<String, AbstractParserRule> definedRules)
    {
        this.parser = parser;
        this.ruleName = ruleName;
        this.children = children;
        this.definedRules = definedRules;
    }


    private Method getMethod (String name)
    {
        return getMethod(name, null);
    }


    private Method getMethod (String name, List<Object> parameters)
    {
        Method[] all_methods = AbstractParser.class.getDeclaredMethods();
        ArrayList<Method> matching_methods = new ArrayList<Method>();
        for (Method method : all_methods)
            if (method.getName().matches(name)) matching_methods.add(method);
        if (matching_methods.isEmpty()) throw new IllegalArgumentException("ParserRule not found: " +
                                                                           name);
        if (matching_methods.size() == 1) return matching_methods.get(0);
        for (Method method : matching_methods)
            if (method.isVarArgs()) return method;
        // TODO Pick the appropriate method based on the number of parameters
        // passed...
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
                           this.toString() +
                           " :: " +
                           this.ruleName +
                           " " +
                           (this.children != null ? this.children.toString()
                                                 : "(no children)"));
        if (this.definedRules.containsKey(this.ruleName))
        {
            AbstractParserRule rule = this.definedRules.get(this.ruleName);
            if (rule == null) throw new RuntimeException("rule == null??");
            setRule(rule);
            return;
        }
        List<AbstractParserRule> params = new ArrayList<AbstractParserRule>();
        for (ParseTreeNode child : this.children)
            if (this.definedRules.containsKey(child.getName())) params.add(this.definedRules.get(child.getName()));
            else try
            {
                params.add(child.toParserRule(parser, definedRules));
            }
            catch (ParserException e)
            {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize rule: " +
                                           e.toString());
            }
        for (AbstractParserRule param : params)
            param.initializeRule();
        AbstractParserRule[] paramsArray =
            new AbstractParserRule[params.size()];
        paramsArray = params.toArray(paramsArray);

        try
        {
            Method m = getMethod(this.ruleName);
            setRule((AbstractParserRule) m.invoke(parser,
                                                  m.getParameterTypes()[0].cast(paramsArray)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error building ParserRules: " +
                                       this.ruleName + " :: " +
                                       params.toString() + e.toString());
        }
    }
}
