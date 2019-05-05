package com.objectia.utils;

public final class StringUtils {

    private StringUtils() {}

    /**
     * Join all elements in a string array into a string
     * @param list array of string
     * @param delimiter delimiter
     * @return a string
     */
    static public String join(String list[], String delimiter)
    {
       StringBuilder sb = new StringBuilder();
       for (String item : list)
       {
          if (sb.length() > 0) {
            sb.append(delimiter);
        }
          sb.append(item);
       }
       return sb.toString();
    }
}