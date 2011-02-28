/**
 * 
 */
package util.parser.grammar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ParserResult;

/**
 * @author Michael Ansel
 */
public class AnonymousRule {
	private List<String> myParameters;
	private ParseTreeNode myTemplate;
	private String myName;

	public AnonymousRule(List<String> parameters, ParseTreeNode template) {
		myParameters = parameters;
		myTemplate = template;
	}

	public AbstractParserRule toParserRule(AbstractParser parser,
			Map<String, AbstractParserRule> rules,
			List<AbstractParserRule> params) {
		if (myParameters.size() != params.size())
			throw new RuntimeException("Parameter mismatch! Expected: "
					+ myParameters.size() + " Got: " + params.size());
		Map<String, AbstractParserRule> localRules = new HashMap<String, AbstractParserRule>(
				rules);
		for (int i = 0; i < myParameters.size(); i++) {
			localRules.put(myParameters.get(i), params.get(i));
		}
		ParseTreeNode template = myTemplate.clone();
		// template.setRules(localRules);
		System.out.println("Creating AnonymousRule: " + template);
		// AbstractParserRule rule = template.toParserRule(parser);
		// rule.setRuleName(String.format("AnonymousRule(%s:%s::%s)",
		// template.getName(),
		// params.toString(),
		// template.toString()));
		// System.out.println("New AnonymousRule: " + rule);
		// return rule;
		return null;
	}

	public ParseTreeNode expandParameters(List<ParseTreeNode> parameters) {
		Map<String, ParseTreeNode> rules = new HashMap<String, ParseTreeNode>();
		for (int i = 0; i < parameters.size(); i++) {
			return null;
		}
		return null;
	}

	public void setName(String ruleName) {
		myName = ruleName;
	}
}
