/**
 * 
 */
package util.parser.grammar;

import java.util.ArrayList;
import java.util.List;
import util.parser.AbstractLexer;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.Token;


/**
 * @author Michael Ansel
 */
public class GrammarParser extends AbstractParser
{
//    public AbstractParserRule AnonymousPrototype = new AbstractParserRule()
//    {
//        @Override
//        public void initializeRule ()
//        {
//            setRule(Sequence(GrammarLexer.Token.Anonymous,
//                             GrammarLexer.Token.BeginGroup,
//                             GrammarLexer.Token.Constant,
//                             ZeroOrMore(Optional(GrammarLexer.Token.Whitespace),
//                                        GrammarLexer.Token.Delimiter,
//                                        Optional(GrammarLexer.Token.Whitespace),
//                                        GrammarLexer.Token.Constant),
//                             GrammarLexer.Token.EndGroup,
//                             GrammarLexer.Token.Whitespace,
//                             Expression));
//        }
//
//
//        @Override
//        protected ParserResult processResult (ParserResult result)
//            throws ParserException
//        {
//            List<String> parameters = new ArrayList<String>();
//            List<Object> matchList = result.getList();
//            matchList.remove(0); // Anonymous
//            matchList.remove(0); // BeginGroup
//            Object o = matchList.remove(0); // First Constant
//            while (o instanceof Token)
//            {
//                Token tok = (Token) o;
//                if (tok.tokenRule == GrammarLexer.Token.Constant) parameters.add((String) tok.value);
//                if (tok.tokenRule == GrammarLexer.Token.Whitespace ||
//                    tok.tokenRule == GrammarLexer.Token.Delimiter) break;
//                o = matchList.remove(0);
//            }
//            if (matchList.get(0) instanceof Token &&
//                ((Token) matchList.get(0)).tokenRule == GrammarLexer.Token.Whitespace) matchList.remove(0);
//            if (!(matchList.size() == 1 && matchList.get(0) instanceof ParseTreeNode)) throw new ParserException("Invalid input to result processor. Remaining: " +
//                                                                                                                 matchList.toString());
//            return new ParserResult(new AnonymousRule(parameters,
//                                                      (ParseTreeNode) matchList.get(0)));
//        }
//    };
    public AbstractParserRule Constant = new AbstractParserRule()
    {
        @Override
        public void initializeRule ()
        {
            setRule(ExactlyOne(GrammarLexer.Token.Constant));
        }


        @Override
        protected ParserResult processResult (ParserResult result)
            throws ParserException
        {
            if (result.getList().size() != 1) parseError("Invalid parse result!");
            return new ParserResult(new ParseTreeNode((String) ((Token) (result.getList().get(0))).value));
        }
    };

    public AbstractParserRule Expression = new AbstractParserRule()
    {
        @Override
        public void initializeRule ()
        {
            setRule(FirstOf(SimpleRule, Token, Constant));
        }
    };

    public AbstractParserRule Root = new AbstractParserRule()
    {
        @Override
        public void initializeRule ()
        {
//            setRule(FirstOf(AnonymousPrototype, Expression));
            setRule(ExactlyOne(Expression));
        }
    };

    public AbstractParserRule SimpleRule = new AbstractParserRule()
    {
        @Override
        public void initializeRule ()
        {
            setRule(Sequence(GrammarLexer.Token.Constant,
                             GrammarLexer.Token.BeginGroup,
                             Expression,
                             ZeroOrMore(Optional(GrammarLexer.Token.Whitespace),
                                        GrammarLexer.Token.Delimiter,
                                        Optional(GrammarLexer.Token.Whitespace),
                                        Expression),
                             Optional(GrammarLexer.Token.Whitespace),
                             GrammarLexer.Token.EndGroup));
        }


        @Override
        protected ParserResult processResult (ParserResult result)
            throws ParserException
        {
            List<Object> list = result.getList();
            List<ParseTreeNode> params = new ArrayList<ParseTreeNode>();
            if (list.size() < 4) throw new RuntimeException("Invalid parse result!");
            String name = (String) ((Token) (list.remove(0))).value; // Constant == Rule Name
            list.remove(0); // BeginGroup
            list.remove(list.size() - 1); // EndGroup
            for (Object o : list)
                if (o instanceof ParseTreeNode) params.add((ParseTreeNode) o);
                else if (o instanceof Token &&
                         ((((Token) o).tokenRule == GrammarLexer.Token.Delimiter) || (((Token) o).tokenRule == GrammarLexer.Token.Whitespace))) continue;
                else throw new RuntimeException("Bad result object: " +
                                                o.toString() + "\n  " +
                                                result.toString() + " :: " +
                                                result.getList().toString());
            return new ParserResult(new ParseTreeNode(name, params));
        }
    };

    public AbstractParserRule Token = new AbstractParserRule()
    {
        @Override
        public void initializeRule ()
        {
            setRule(Sequence(GrammarLexer.Token.BeginToken,
                             GrammarLexer.Token.Constant,
                             GrammarLexer.Token.EndToken));
        }


        @Override
        protected ParserResult processResult (ParserResult result)
            throws ParserException
        {
            if (result.getList().size() != 3) throw new RuntimeException("Invalid parse result!");
            return new ParserResult(new ParseTreeNode("ExactlyOne",
                                                      (String) ((Token) (result.getList().get(1))).value));
        }
    };


    public GrammarParser (AbstractLexer lexer)
    {
        super(lexer);
    }
}
