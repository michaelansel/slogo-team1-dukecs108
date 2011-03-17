/**
 * 
 */
package slogo.model.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import slogo.model.expression.Expression;
import slogo.model.expression.Variable;
import slogo.model.expression.command.UserCommand;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.IResultHandler;
import util.parser.ITokenRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.TokenManager;
import util.parser.grammar.GrammarLexer;
import util.parser.grammar.GrammarParser;
import util.parser.grammar.GrammarParserFactory;
import util.parser.grammar.ParseTreeNode;
import util.parser.rule.FirstOfRule;


/**
 * @author Michael Ansel
 */
public class SlogoParserFactory extends GrammarParserFactory
{
    public class UserCommandEntry extends Object
    {
        public String commandName;
        public ParseTreeNode parseTreeNode;
        public Expression howToExpression;
        public List<Variable> parameters;


        public UserCommandEntry (String commandName,
                                 ParseTreeNode parseTreeNode,
                                 Expression howToExpression,
                                 List<Variable> myVariableList)
        {
            this.commandName = commandName;
            this.parseTreeNode = parseTreeNode;
            this.howToExpression = howToExpression;
            this.parameters = myVariableList;
        }
    }

    private Map<String, UserCommandEntry> myUserDefinedCommands;


    public SlogoParserFactory (ResourceBundle syntax) throws ParserException
    {
        super(syntax);
        myUserDefinedCommands = new HashMap<String, UserCommandEntry>();
    }


    @Override
    public AbstractParser create (TokenManager tokenManager,
                                  Map<String, ITokenRule> tokenRules)
        throws ParserException
    {
        AbstractParser parser = super.create(tokenManager, tokenRules);

        // Add user-defined command rules to parser ("UserCommandEntry" rule)
        List<AbstractParserRule> userCommandRules =
            new ArrayList<AbstractParserRule>();
        for (Map.Entry<String, UserCommandEntry> entry : myUserDefinedCommands.entrySet())
        {
            String name = entry.getKey();
            ParseTreeNode command = entry.getValue().parseTreeNode;
            final String commandName = entry.getValue().commandName;
            final Expression howToExpression = entry.getValue().howToExpression;
            final List<Variable> parameters = entry.getValue().parameters;

            AbstractParserRule rule =
                command.toParserRule(tokenRules, parser.getRules());
            rule.setRuleName(name);
            rule.setHandler(new IResultHandler()
            {
                @Override
                public ParserResult handleResult (ParserResult result)
                    throws ParserException
                {
                    if (parameters.size() == 0)
                    {
                        // <commandName>
                        return new ParserResult(new UserCommand(commandName,
                                                                howToExpression));
                    }
                    else
                    {
                        // <commandName>,<whitespace>,(IgnoreWhitespace,Expression)+
                        return new ParserResult(new UserCommand(commandName,
                                                                howToExpression,
                                                                parameters,
                                                                result.getList()
                                                                      .subList(2,
                                                                               result.getList()
                                                                                     .size())));
                    }
                }
            });

            userCommandRules.add(rule);
        }
        // initialize user commands
        for (AbstractParserRule rule : userCommandRules)
            rule.initializeRule();
        parser.getRule("UserCommand")
              .setRule(new FirstOfRule(userCommandRules.toArray()));

        List<ITokenRule> userCommandTokenRules = new ArrayList<ITokenRule>();
        SlogoLexer lexer = new SlogoLexer("");
        for (String commandName : myUserDefinedCommands.keySet())
            userCommandTokenRules.add(lexer.getTokenRuleByName(commandName));
        userCommandTokenRules.add(lexer.getTokenRuleByName("CommandName"));
        parser.getRule("UserCommandName")
              .setRule(new FirstOfRule(userCommandTokenRules.toArray()));

        return parser;
    }


    public void addUserDefinedCommand (String commandName,
                                       Expression howToExpression,
                                       List<Variable> myVariableList)
    {
        // Add to lexer
        SlogoLexer.addUserCommand(commandName);

        // Add to parser grammar
        String commandGrammar =
            constructUserCommandGrammar(commandName, myVariableList.size());
        ParserResult parsedGrammar;
        try
        {
            parsedGrammar =
                new GrammarParser(new GrammarLexer(commandGrammar).tokenize()).run();
        }
        catch (ParserException e)
        {
            // Failed to create user command
            e.printStackTrace();
            return;
        }
        ParseTreeNode parseTreeNode =
            (ParseTreeNode) parsedGrammar.getList().get(0);
        myUserDefinedCommands.put(commandName,
                                  new UserCommandEntry(commandName,
                                                       parseTreeNode,
                                                       howToExpression,
                                                       myVariableList));
    }


    private String constructUserCommandGrammar (String commandName,
                                                int parameterCount)
    {
        // match parameters parameters
        StringBuilder params = new StringBuilder();
        if (parameterCount > 0) params.append(",<Whitespace>,Expression");
        for (int i = 1; i < parameterCount; i++)
            params.append(",IgnoreWhitespace,Expression");
        return String.format("Sequence(<%s>%s)", commandName, params.toString());
    }
}
