/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-3 下午9:38
 *
 */

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
