package util.reflection;

import java.lang.reflect.*;


/**
 * Provides methods for dynamically creating instances and calling methods.
 * 
 * This convenience class simplifies Java's reflection API and fixes some issues.
 * 
 * @author Robert C. Duvall
 */
public class Reflection
{
    private static final String NO_CLASS = "Incorrectly named class %s";
    private static final String NO_DEFAULT_CONSTRUCTOR = "No public default constructor for %s";
    private static final String NO_MATCHING_CONSTRUCTOR = "No matching public constructor for %s";
    private static final String NO_MATCHING_METHOD = "No matching public method %s for %s";
    private static final String NO_MATCHING_FIELD = "No matching public instance variable for %s";

    
    /**
     * Create instance from class's name.
     * 
     * Given fully qualified class name, returns initialized instance 
     * of the corresponding class using default constructor.
     */
    public static Object createInstance (String className)
        throws ReflectionException
    {
        try
        {
            return Class.forName(className).newInstance();
        }
        catch (ClassNotFoundException e)
        {
            throw new ReflectionException(NO_CLASS, className);
        }
        catch (Exception e)
        {
            throw new ReflectionException(NO_DEFAULT_CONSTRUCTOR, className);
        }
    }


    /**
     * Create instance from class's name and associated arguments.
     * 
     * Given fully qualified class name, returns initialized instance 
     * of the corresponding class using closest matching constructor.  
     */
    public static Object createInstance (String className, Object ... values)
        throws ReflectionException
    {
        try
        {
            Class<?> c = Class.forName(className);
            for (Constructor<?> current : c.getDeclaredConstructors())
            {
                Class<?>[] formals = current.getParameterTypes();
                if (typesMatch(current, formals, values))
                {
                    return current.newInstance(convertArgs(current, formals, values));
                }
            }
            throw new ReflectionException(NO_MATCHING_CONSTRUCTOR, className);
        }
        catch (ClassNotFoundException e)
        {
            throw new ReflectionException(NO_CLASS, className);
        }
        catch (Exception e)
        {
            throw new ReflectionException(NO_MATCHING_CONSTRUCTOR, className);
        }
    }


    /**
     * Call method on given object by its name.
     * 
     * Given target object with no argument method of the given name, 
     * calls named method on that object and returns its result. If method's 
     * return type is void, null is returned.
     */
    public static Object callMethod (Object target, String methodName)
        throws ReflectionException
    {
        try
        {
            Method toCall = target.getClass().getDeclaredMethod(methodName, new Class[0]);
            return toCall.invoke(target, new Object[0]);
        }
        catch (Exception e)
        {
            throw new ReflectionException(NO_MATCHING_METHOD, 
                                          methodName, target.getClass().getName());
        }
    }


    /**
     * Call method on given object by its name and arguments.
     *
     * Given target object with a method of the given name that takes the
     * given actual parameters, calls named method on that object and returns
     * its result. If method's return type is void, null is returned.
     */
    public static Object callMethod (Object target, String methodName, Object ... values)
        throws ReflectionException
    {
        try
        {
            for (Method current : target.getClass().getDeclaredMethods())
            {
                if (methodName.equals(current.getName()))
                {
                    Class<?>[] formals = current.getParameterTypes();
                    if (typesMatch(current, formals, values))
                    {
                        return current.invoke(target, convertArgs(current, formals, values));
                    }
                }
            }
            throw new ReflectionException(NO_MATCHING_METHOD,
                                          methodName, target.getClass().getName());
        }
        catch (Exception e)
        {
            throw new ReflectionException(NO_MATCHING_METHOD,
                                          methodName, target.getClass().getName());
        }
    }


    /**
     * Get value of instance variable of given object by its name.
     * 
     * Given target object with an instance variable with the given name,
     * returns value of the named variable of that object.
     */
    public static Object getFieldValue (Object target, String fieldName)
        throws ReflectionException
    {
        try
        {
            return target.getClass().getDeclaredField(fieldName).get(target);
        }
        catch (Exception e)
        {
            throw new ReflectionException(NO_MATCHING_FIELD, target.getClass().getName());
        }
    }


    /**
     * Given an array of Objects, returns their corresponding Classes.
     */
    public static Class<?>[] toClasses (Object[] values)
    {
        Class<?>[] results = new Class[values.length];
        for (int k = 0; k < values.length; k++)
        {
            results[k] = values[k].getClass();
        }
        return results;
    }

    
    // are parameters of compatible types and in same order?
    private static boolean typesMatch (Member function,
                                       Class<?>[] formals, Object[] actuals)
    {
        if ((actuals.length == formals.length) || 
            (actuals.length >= formals.length && isVarArgs(function)))
        {
            int idx = 0;
            // check each parameter individually
            for ( ; idx < formals.length - 1; idx++)
            {
                if (! isInstance(formals[idx], actuals[idx]))
                {
                    return false;
                }
            }
            // check each of the last actual args to see if they can be one of varargs
            Class<?> type = (formals[idx].isArray()) ? formals[idx].getComponentType() : formals[idx];
            for ( ; idx < actuals.length; idx++)
            {
                if (! isInstance(type, actuals[idx]))
                {
                    return false;
                }
            }
            // it was possible, and nothing else returned false, so
            return true;
        }
        // sizes don't match
        return false;
    }

    // if necessary, convert parameters into varArg array that Java expects
    private static Object[] convertArgs (Member function, 
                                         Class<?>[] formals, Object[] actuals)
    {
        Object[] results = actuals;
        if (isVarArgs(function))
        {
            results = new Object[formals.length];
            int idx = 0;
            for ( ; idx < formals.length - 1; idx++)
            {
                // simply copy the basic parameters
                results[idx] = actuals[idx];
            }
            Class<?> type = formals[idx].getComponentType();
            Object varArgs = Array.newInstance(type, actuals.length - formals.length + 1);
            for ( ; idx < actuals.length; idx++)
            {
                // fill the varArg array with the remaining parameters
                Array.set(varArgs, idx - formals.length + 1, actuals[idx]);
            }
            results[results.length - 1] = varArgs;
        }
        return results;
    }

    // Java should implement this correctly, but alas ...
    private static boolean isInstance (Class<?> clss, Object instance)
    {
        final String TYPE = "TYPE";

        try
        {
            // handle primitives specially
            if (clss.isPrimitive())
            {
                Class<?> thePrimitive = (Class<?>)getFieldValue(instance, TYPE);
                if (! isAssignableFrom(clss, thePrimitive))
                {
                    // primitives are not exactly the same
                    return false;
                }
            }
            else if (! clss.isInstance(instance))
            {
                // not an instance of class or its sub-classes
                return false;
            }
            return true;
        }
        catch (Exception e)
        {
            // tried to compare primitive to non-primitive
            return false;
        }
    }

    // Java should implement this correctly, but alas ...
    // isVarArgs is a method of both constructors and methods, 
    //   but not to any of their common super-types
    private static boolean isVarArgs (Member function)
    {
        // BUGBUG: should call isVarArgs directly
        return Modifier.isTransient(function.getModifiers());
    }

    // Java should implement this correctly, but alas ...
    // right now, no added functionality, because of potential ambiguities
    private static boolean isAssignableFrom (Class<?> formal, Class<?> arg)
    {
        return formal.isAssignableFrom(arg);
    }
}
