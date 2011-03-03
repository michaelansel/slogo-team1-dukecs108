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
        public static ITokenRule Add;
        public static ITokenRule And;
        public static ITokenRule AssignmentOperator;
        public static ITokenRule BeginExpressionGroup;
        public static ITokenRule BeginParameterGroup;
        public static ITokenRule CommandName;
        public static ITokenRule Constant;
        public static ITokenRule Divide;
        public static ITokenRule EndExpressionGroup;
        public static ITokenRule EndParameterGroup;
        public static ITokenRule Exponent;
        public static ITokenRule GreaterThan;
        public static ITokenRule LessThan;
        public static ITokenRule Modulus;
        public static ITokenRule Multiply;
        public static ITokenRule Not;
        public static ITokenRule Or;
        public static ITokenRule[] rules;
        public static ITokenRule Subtract;
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
