/**
 * 
 */
package slogo.model.expression.binary;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;
import slogo.model.arena.Arena;
import slogo.model.expression.Expression;
import util.parser.IResultHandler;
import util.parser.ParserException;
import util.parser.ParserResult;
import util.parser.Token;


/**
 * @author Michael Ansel
 */
public abstract class BinaryExpression extends Expression
{

    // TODO Needs some serious refactoring (but the interface shouldn't need to change)
    private static class BinaryOperator implements Comparable<BinaryOperator>
    {
        final static Map<String, Class<? extends BinaryExpression>> TOKEN_CLASS_MAP;

        final static List<String> TOKEN_CLASS_NAMES;
        static
        {
            // TODO Needs some serious refactoring
            Map<String, Class<? extends BinaryExpression>> tokenClassMap =
                new HashMap<String, Class<? extends BinaryExpression>>();

            List<String> tokenClassNames =
                Arrays.asList(new String[] {
                        "slogo.model.expression.binary.Exponent",
                        "slogo.model.expression.binary.Multiply",
                        "slogo.model.expression.binary.Divide",
                        "slogo.model.expression.binary.Modulus",
                        "slogo.model.expression.binary.Add",
                        "slogo.model.expression.binary.Subtract" });
            Collections.reverse(tokenClassNames);

            for (String tokenClassName : tokenClassNames)
            {
                try
                {
                    Class<? extends BinaryExpression> tokenClass =
                        (Class<? extends BinaryExpression>) Class.forName(tokenClassName);
                    String tokenRegex =
                        (String) tokenClass.getField("TOKEN_REGEX").get(null);
                    tokenClassMap.put(tokenRegex, tokenClass);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    throw new RuntimeException("Failed to initialize BinaryOperator class: " +
                                               e.toString());
                }
            }
            TOKEN_CLASS_NAMES = tokenClassNames;
            TOKEN_CLASS_MAP = tokenClassMap;
        }

        private Class<? extends BinaryExpression> myClass;
        private Integer myPrecedence;
        private String myRegex;
        private String myToken;


        public BinaryOperator (String token)
        {
            myToken = token;
            myRegex = null;
            myClass = null;
            myPrecedence = null;
            for (String regex : TOKEN_CLASS_MAP.keySet())
            {
                if (Pattern.matches(regex, token))
                {
                    myRegex = regex;
                    myClass = TOKEN_CLASS_MAP.get(regex);
                    myPrecedence = TOKEN_CLASS_NAMES.indexOf(myClass.getName());
                }
            }
            if (myClass == null) throw new RuntimeException("Invalid operator: " +
                                                            token);
        }


        @Override
        public int compareTo (BinaryOperator other)
        {
            return myPrecedence.compareTo(other.myPrecedence);
        }


        public Expression newExpression (Expression a, Expression b)
        {
            try
            {
                Constructor<? extends BinaryExpression> constructor =
                    myClass.getConstructor(Expression.class, Expression.class);
                return constructor.newInstance(a, b);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            throw new RuntimeException("Unable to create Expression instance");
        }


        @Override
        public String toString ()
        {
            return "BinaryOperator(" + myToken + ")";
        }
    }

    private static final class ParseResultHandler implements IResultHandler
    {
        /**
         * @see util.parser.IResultHandler#handleResult(util.parser.ParserResult)
         * @see slogo.model.expression.Expression#getParserResultHandler()
         */
        @Override
        public ParserResult handleResult (ParserResult result)
            throws ParserException
        {
            return new ParserResult(postfixToExpression(infixToPostfix(result.getList())));
        }


        /**
         * @param list
         * @return
         */
        private List<Object> infixToPostfix (List<Object> list)
        {
            Stack<BinaryOperator> stack = new Stack<BinaryOperator>();
            List<Object> output = new ArrayList<Object>();
            for (Object token : list)
            {
                if (token instanceof Expression) output.add((Expression) token);
                else if (token instanceof Token)
                {
                    BinaryOperator o1 =
                        new BinaryOperator((String) ((Token) token).value);
                    while (!stack.isEmpty() && o1.compareTo(stack.peek()) < 0)
                    {
                        output.add(stack.pop());
                    }
                    stack.push(o1);
                }
                else throw new RuntimeException("Invalid list entry: " +
                                                token.toString());
            }
            while (!stack.isEmpty())
                output.add(stack.pop());
            assert (stack.isEmpty());
            return output;
        }


        /**
         * @param postfix
         * @return
         */
        private Expression postfixToExpression (List<Object> postfix)
        {
            Stack<Expression> stack = new Stack<Expression>();
            while (!postfix.isEmpty())
            {
                while (!postfix.isEmpty() &&
                       postfix.get(0) instanceof Expression)
                    stack.push((Expression) postfix.remove(0));
                if (postfix.isEmpty()) break;
                if (!(postfix.get(0) instanceof BinaryOperator)) throw new RuntimeException("WTF? Must be either an Expression or a BinaryOperator...");
                if (stack.size() < 2) throw new RuntimeException("RPN stack too small! It appears we can't generate good postfix...");
                Expression a = (Expression) stack.pop();
                Expression b = (Expression) stack.pop();
                BinaryOperator op = (BinaryOperator) postfix.remove(0);
                Expression result = op.newExpression(b, a); // operands reversed on stack, so "un-reverse"
                stack.push(result);
            }
            assert (stack.size() == 1);
            return stack.pop();
        }

    }

    private static IResultHandler myParseResultHandlerInstance;


    /**
     * Return the singleton for processing ParserResults into Expression trees
     * 
     * @return IParseResultHandler singleton
     */
    public static IResultHandler getParserResultHandler ()
    {
        if (myParseResultHandlerInstance == null)
        {
            myParseResultHandlerInstance = new ParseResultHandler();
        }
        return myParseResultHandlerInstance;
    }

    private Expression mySubExpressionA;
    private Expression mySubExpressionB;


    public BinaryExpression (Expression subExpressionA,
                             Expression subExpressionB)
    {
        mySubExpressionA = subExpressionA;
        mySubExpressionB = subExpressionB;
    }


    @Override
    public int evaluate (Arena arena)
    {
        return evaluate(arena, mySubExpressionA, mySubExpressionB);
    }


    protected abstract int evaluate (Arena arena,
                                     Expression expressionA,
                                     Expression expressionB);


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] {
                mySubExpressionA,
                mySubExpressionB });
    }


    @Override
    public abstract String toString ();


    protected String toString (char operator)
    {
        return String.format("(%s%c%s)",
                             mySubExpressionA.toString(),
                             operator,
                             mySubExpressionB.toString());
    }

}
