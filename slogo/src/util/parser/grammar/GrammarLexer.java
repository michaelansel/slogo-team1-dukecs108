/**
 * 
 */
package util.parser.grammar;

import util.parser.AbstractLexer;
import util.parser.ITokenRule;
import util.parser.StringLexer;


/**
 * @author Michael Ansel
 */
public class GrammarLexer extends StringLexer
{
    public static final class Token
    {
        public static ITokenRule Anonymous;
        public static ITokenRule BeginGroup;
        public static ITokenRule BeginToken;
        public static ITokenRule Constant;
        public static ITokenRule Delimiter;
        public static ITokenRule EndGroup;
        public static ITokenRule EndToken;
        public static ITokenRule[] rules;
        public static ITokenRule Whitespace;
        static
        {
            rules = AbstractLexer.getRulesArray(Token.class);
        }
    };


    /**
     * @param input
     */
    public GrammarLexer (String input)
    {
        super(input);
        setTokenRules(Token.rules);
    }

}
