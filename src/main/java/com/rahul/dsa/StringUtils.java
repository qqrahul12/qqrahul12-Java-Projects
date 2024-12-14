package com.rahul.dsa;

import com.rahul.password.util;

public class StringUtils {
    public static boolean areAllUpperCase(String s) {
        return s.chars().allMatch(Character::isUpperCase);
    }

    public static boolean areAllLowerCase(String s) {
        return s.chars().allMatch(Character::isLowerCase);
    }

    public static boolean isComplexPassword(String password) {
        return util.isComplexPassword(password);
    }

    public static String reverse(String s) {
        if(s == null || s.isEmpty())  {
            return null;
        }
        return new StringBuilder(s).reverse().toString();
    }

    public static String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        String[] words = s.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (String word : words) {
            reversed.append(reverse(word)).append(" ");
        }
        reversed.trimToSize();
        return reversed.toString();
    }

    public static int hasSubstring(String s, String sub) {
        if(s == null || s.isEmpty() || sub == null || sub.isEmpty()) {
            return -1;
        }
        return s.indexOf(sub);
    }

}
