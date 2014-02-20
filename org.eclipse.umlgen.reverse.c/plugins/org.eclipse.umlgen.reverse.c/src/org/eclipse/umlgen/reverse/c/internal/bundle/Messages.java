package org.eclipse.umlgen.reverse.c.internal.bundle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages
{
    private static final String BUNDLE_NAME = "org.eclipse.umlgen.reverse.c.internal.bundle.messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages()
    {
    }

    public static String getString(String key)
    {
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key + '!';
        }
    }
}
