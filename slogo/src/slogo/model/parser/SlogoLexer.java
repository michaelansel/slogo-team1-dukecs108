/**
 * 
 */
package slogo.model.parser;

import util.parser.AbstractLexer;
import util.parser.ITokenRule;
import util.parser.StringLexer;


/**
 * @author Michael Ansel
 */
public class SlogoLexer extends StringLexer
{
    public static final class Token
    {
        public static ITokenRule ArithmeticOperator;
        public static ITokenRule AssignmentOperator;
        public static ITokenRule BeginExpressionGroup;
        public static ITokenRule BeginParameterGroup;
        public static ITokenRule BooleanOperator;
        public static ITokenRule Command;
        public static ITokenRule Constant;
        public static ITokenRule EndExpressionGroup;
        public static ITokenRule EndParameterGroup;
        public static ITokenRule[] rules;
        public static ITokenRule Variable;
        public static ITokenRule Whitespace;
        static
        {
            rules = AbstractLexer.getRulesArray(Token.class);
        }
    };


    /**
     * @param input
     */
    public SlogoLexer (String input)
    {
        super(input);
        setTokenRules(Token.rules);
    }

}
