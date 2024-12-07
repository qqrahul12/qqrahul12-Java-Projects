package com.rahul.password;

public class util {
    public static String getCharSequence(char from, char to) {
        StringBuilder letters = new StringBuilder();
        for (char letter = from; letter <= to; letter++) {
            letters.append(letter);
        }
        return letters.toString();
    }

    public static String getCharSequence(char[] from, char[] to) {
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < from.length; i++) {
            letters.append(getCharSequence(from[i], to[i]));
        }
        return letters.toString();
    }

    public static String getCharSequence(char[] from, char[] to, String specialChars) {
        return getCharSequence(from, to) + specialChars;
    }
}
