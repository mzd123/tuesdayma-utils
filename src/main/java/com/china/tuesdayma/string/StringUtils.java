package com.china.tuesdayma.string;

/**
 * @Author: mzd
 * @Date: 2019/10/15 13:49
 */
public class StringUtils {
    public static boolean isEmpty(String string) {
        if (string == null || string.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
}
