/**
 * 
 */
package util.parser;

/**
 * @author Michael Ansel
 */
public interface IResultHandler {
	public ParserResult handleResult(ParserResult result)
			throws ParserException;
}
