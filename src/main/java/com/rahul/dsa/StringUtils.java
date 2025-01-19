package com.rahul.dsa;

import com.rahul.password.util;

import java.util.Stack;

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

    public static String repeat(String a, int count) {
        return String.valueOf(a).repeat(Math.max(0, count));
    }

    // https://leetcode.com/problems/decode-string/
    public static String decodeString(String s) {
        if(s.length()==1) return s;
        int i = 0;
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        while(i < n) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int y = 0;
                while(Character.isDigit(s.charAt(i))) {
                    int x = s.charAt(i) - '0';
                    y = y * 10 + x ;
                    i++;
                }
                i = i + 1;
                Stack<Character> st = new Stack<>();
                StringBuilder rs = new StringBuilder();
                st.push('[');
                st.push(s.charAt(i));
                rs.append(s.charAt(i));
                i++;
                while(i<n && !st.empty()) {
                    if(s.charAt(i) == ']') {
                        char z = st.peek();
                        while(!st.empty() && z != '[') {
                            st.pop();
                            if(!st.empty())
                                z = st.peek();
                        }
                        if(z=='[') st.pop();
                        i++;
                        rs.append(']');
                    } else {
                        st.push(s.charAt(i));
                        rs.append(s.charAt(i));
                        i++;
                    }
                }
                System.out.println(rs);
                String z = decodeString(rs.toString());
                sb.append(repeat(z, y));
            } else if(s.charAt(i) != ']') {
                sb.append(c);
                i++;
            } else {
                i++;
            }
        }
        return sb.toString();
    }

}
