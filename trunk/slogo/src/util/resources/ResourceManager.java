package util.resources;

import java.net.URL;
import java.util.*;


/**
 * A singleton class that represents a global ResourceBundle.
 * 
 * Provides utility methods for retrieving resources of different types.
 * 
 * By making this class an single instance rather than a bunch of static
 * methods, it allows this class to be extended.
 * 
 * Note, this currently supports only one "namespace", so property names 
 * must be unique across all resource files.
 * 
 * @author Robert C. Duvall
 */
public class ResourceManager implements Iterable<String>
{
    public static final String DEFAULT_SUBPACKAGE = "resources";
    public static final String DEFAULT_LANGUAGE = "English";
    public static final String DEFAULT_DELIMITER = ",";
    private static final String MISSING_RESOURCE = "Missing resource file: %s.%s";
    private static ResourceManager ourInstance;
    private ResourceBundle myResources;


    // do not allow others to instantiate this class
    private ResourceManager ()
    {
        // TODO BUGBUG: should project be required to have resources?
        // addResourcesFromFile(DEFAULT_LANGUAGE);
    }


    /**
     * Get access to all resources for this project through the single instance of this class.
     */
    public static ResourceManager getInstance ()
    {
        if (ourInstance == null)
        {
            ourInstance = new ResourceManager();
        }
        return ourInstance;
    }

    
    /**
     * Returns value associated with given key as a boolean value.
     */
    public boolean getBoolean (String key)
    {
        return getString(key).equalsIgnoreCase("true");
    }


    /**
     * Returns value associated with given key as a integer value.
     */
    public int getInteger (String key)
    {
        return Integer.parseInt(getString(key));
    }


    /**
     * Returns value associated with given key as a double value.
     */
    public double getDouble (String key)
    {
        return Double.parseDouble(getString(key));
    }


    /**
     * Returns value associated with given key as a string value.
     */
    public String getString (String key)
    {
    	return myResources.getString(key);
    }


    /**
     * Returns value associated with given key as a formatted string, 
     * with the given values filling in the format arguments.
     */
    public String getFormattedString (String key, Object ... values)
    {
        return String.format(myResources.getString(key), values);
    }
    
    
    /**
     * Returns value associated with given key as an array of boolean values,
     * using DEFAULT_DELIMITER as the separator between properties.
     */
    public boolean[] getBooleanArray (String key)
    {
        return getBooleanArray(key, DEFAULT_DELIMITER);
    }


    /**
     * Returns value associated with given key as an array of boolean values,
     * using given delimiter as the separator between properties.
     */
    public boolean[] getBooleanArray (String key, String delimeter)
    {
        String[] properties = getString(key).split(delimeter);
        boolean[] results = new boolean[properties.length];
        for (int k = 0; k < results.length; k++)
        {
            results[k] = properties[k].equalsIgnoreCase("true");
        }
        return results;
    }


    /**
     * Returns value associated with given key as an array of integer values,
     * using DEFAULT_DELIMITER as the separator between properties.
     */
    public int[] getIntegerArray (String key)
    {
        return getIntegerArray(key, DEFAULT_DELIMITER);
    }


    /**
     * Returns value associated with given key as an array of integer values,
     * using given delimiter as the separator between properties.
     */
    public int[] getIntegerArray (String key, String delimeter)
    {
        String[] properties = getString(key).split(delimeter);
        int[] results = new int[properties.length];
        for (int k = 0; k < results.length; k++)
        {
            results[k] = Integer.parseInt(properties[k]);
        }
        return results;
    }


    /**
     * Returns value associated with given key as an array of double values,
     * using DEFAULT_DELIMITER as the separator between properties.
     */
    public double[] getDoubleArray (String key)
    {
        return getDoubleArray(key, DEFAULT_DELIMITER);
    }


    /**
     * Returns value associated with given key as an array of double values,
     * using given delimiter as the separator between properties.
     */
    public double[] getDoubleArray (String key, String delimeter)
    {
        String[] properties = getString(key).split(delimeter);
        double[] results = new double[properties.length];
        for (int k = 0; k < results.length; k++)
        {
            results[k] = Double.parseDouble(properties[k]);
        }
        return results;
    }


    /**
     * Returns value associated with given key as an array of string values,
     * using DEFAULT_DELIMITER as the separator between properties.
     */
    public String[] getStringArray (String key)
    {
        return getStringArray(key, DEFAULT_DELIMITER);
    }


    /**
     * Returns value associated with given key as an array of string values,
     * using given delimiter as the separator between properties.
     */
    public String[] getStringArray (String key, String delimeter)
    {
        return getString(key).split(delimeter);
    }


    /**
     * Iterate through all resources in this bundle.
     */
    public Iterator<String> iterator ()
    {
        return myResources.keySet().iterator();
    }


    /**
     * Change resource values to those represented by this property file.
     * 
     * Given property file is assumed to be in the sub-package DEFAULT_SUBPACKAGE
     * and have the extension ".properties".
     */
    public void addResourcesFromFile (String language)
    {
        addResourcesFromFile(language, DEFAULT_SUBPACKAGE);
    }


    /**
     * Add resource values from given property file.
     * 
     * Note, if the property names in the given file are the same as any 
     * current names, these properties will replace the current ones. 
     * 
     * Given property file is assumed to be in the sub-package DEFAULT_SUBPACKAGE
     * and have the extension ".properties".
     */
    public void addResourcesFromFile (String language, String subpackage)
    {
        try
        {
            myResources = ResourceBundle.getBundle(subpackage + "." + language);
        }
        catch (MissingResourceException e)
        {
            throw new ResourceException(MISSING_RESOURCE, subpackage, language);
        }
    }


    /**
     * Add resource values from property file relative to given object.
     *
     * Change the resource file this bundle represents to one named 
     * after the given object.
     */
    public void addResourcesFromClass (Object target)
    {
        addResourcesFromFile(getClassName(target));
    }


    /**
     * Get a file name based on the location of the given object.
     */
    public URL getFile (Object root, String path)
    {
        return root.getClass().getResource(path);
    }
    
    
    /**
     * Returns the name of the class to use in determining which resource 
     * file to use.
     */
    protected String getClassName (Object target)
    {
        return target.getClass().getSimpleName();
    }
}
