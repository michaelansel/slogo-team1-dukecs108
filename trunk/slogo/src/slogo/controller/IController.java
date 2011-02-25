package slogo.controller;

/**
 * 
 */

/**
 * @author Michael Ansel
 */
public interface IController
{
    /**
     * Parse and evaluate the provided expression. The view is automatically
     * updated when the model state changes.
     * 
     * @param expression Expression to parse and evaluate
     */
    public void evaluateExpression (String expression);
}
