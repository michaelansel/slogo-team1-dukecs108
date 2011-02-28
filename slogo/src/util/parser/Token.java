/**
 * 
 */
package util.parser;

/**
 * @author Michael Ansel
 */
public class Token {
	public ITokenRule tokenRule;
	public Object value;

	public Token(ITokenRule t, Object v) {
		tokenRule = t;
		value = v;
	}

	@Override
	public String toString() {
		return String.format("Token(%s,\"%s\")", tokenRule.toString(), value);
	}
}
