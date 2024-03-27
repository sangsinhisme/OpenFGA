package vn.fpt.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {

    public static String getKeyWithResourceBundle(String bundleName, Locale locale,  String key) {

        if(locale == null)
            return ResourceBundle.getBundle(bundleName, Locale.getDefault()).getString(key);
        else return ResourceBundle.getBundle(bundleName, locale).getString(key);
    }
}
