package util.reflection;

/**
 * A general exception that represents all possible Java Reflection exceptions.
 * 
 * @author Robert C. Duvall
 */
@SuppressWarnings("serial")
public final class ReflectionException extends RuntimeException {
	/**
	 * Create exception with given message
	 * 
	 * @param message
	 *            explanation of problem
	 */
	public ReflectionException(String message, Object... values) {
		super(String.format(message, values));
	}
}
