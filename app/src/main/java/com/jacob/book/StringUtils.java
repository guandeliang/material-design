package com.jacob.book;

public class StringUtils {
    public static boolean isBlankString(String str){
        boolean result = false;
        if (str == null || "".equals(str.trim())) {
            result = true;
        }
        return result;
    }
}
