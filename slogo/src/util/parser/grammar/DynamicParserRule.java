package util.parser.grammar;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.parser.AbstractParserRule;
import util.parser.ITokenRule;
import util.parser.ParserException;


final class DynamicParserRule extends AbstractParserRule
{
    private static final Logger logger =
        Logger.getLogger(DynamicParserRule.class.getName());
    private List<ParseTreeNode> children;
    private Map<String, AbstractParserRule> definedRules;
    private boolean initializing;
    private String ruleName;
    private Map<String, ITokenRule> tokenRules;


    DynamicParserRule (String ruleName,
                       List<ParseTreeNode> children,
                       Map<String, ITokenRule> tokenRules,
                       Map<String, AbstractParserRule> definedRules)
    {
        this.ruleName = ruleName;
        this.children = children;
        this.tokenRules = tokenRules;
        this.definedRules = definedRules;
    }


    private AbstractParserRule createParserRule (String ruleName,
                                                 AbstractParserRule[] paramsArray)
    {
        ResourceBundle bundle =
            ResourceBundle.getBundle("util.parser.rule.Rules");
        String exception = "";
        if (bundle.containsKey(ruleName))
        {
            try
            {
                @SuppressWarnings("unchecked")
                Class<? extends AbstractParserRule> klass =
                    (Class<? extends AbstractParserRule>) Class.forName(bundle.getString(ruleName));
                Constructor<? extends AbstractParserRule> konstructor =
                    klass.getConstructor(Object[].class);
                return (AbstractParserRule) konstructor.newInstance((Object) paramsArray);
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
                exception = e.toString();
            }
            catch (SecurityException e)
            {
                e.printStackTrace();
                exception = e.toString();
            }
            catch (NoSuchMethodException e)
            {
                e.printStackTrace();
                exception = e.toString();
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
                exception = e.toString();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
                exception = e.toString();
            }
            catch (InvocationTargetException e)
            {
                e.printStackTrace();
                exception = e.toString();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
                exception = e.toString();
            }
        }
        throw new RuntimeException("Error building ParserRules: " + ruleName +
                                   " :: " +
                                   Arrays.asList(paramsArray).toString() + " " +
                                   exception);
    }


    @Override
    public void initializeRule ()
    {
        if (initialized()) return;
        if (initializing) return;
        initializing = true;
        if (logger.isLoggable(Level.FINER)) logger.finer("Initializing ParserRule: " +
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
                params.add(child.toParserRule(tokenRules, definedRules));
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

        setRule(createParserRule(this.ruleName, paramsArray));
    }
}
