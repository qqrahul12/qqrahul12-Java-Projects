package com.rahul.password;

import java.util.Random;

public class PasswordGenerator {

    private static String generatePassword(String chars, int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    public static boolean isValidPassword(String password, int minLength) {
        return password.length() >= minLength;
    }

    private static boolean hasCharacter(String password, String chars) {
        for (int i = 0; i < password.length(); i++) {
            if (chars.indexOf(password.charAt(i)) != -1) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidPassword(String password, int minLength, boolean useUpperCase, boolean useNumbers, boolean useSpecialCharacters, boolean useLowerCase) {
        if(!isValidPassword(password, minLength)) {
            return false;
        }
        if(useUpperCase && !hasCharacter(password, util.getCharSequence('A', 'Z'))) {
            return false;
        }
        if(useNumbers && !hasCharacter(password, util.getCharSequence('0', '9'))) {
            return false;
        }
        if(useSpecialCharacters && !hasCharacter(password, "!@#$%&*+?")) {
            return false;
        }
        return !useLowerCase || hasCharacter(password, util.getCharSequence('a', 'z'));
    }

    public static String generatePassword() {
        return generatePassword(8);
    }

    public static String generatePassword(int length) {
        String chars = util.getCharSequence(new char[]{'a', 'A', '0'}, new char[]{'z', 'Z', '9'}, "!@#$%&*+?");
        return generatePassword(chars, length);
    }

    public static String generatePassword(int length, boolean useUpperCase, boolean useNumbers, boolean useSpecialCharacters, boolean useLowerCase) {
        String lowerCaseChars = util.getCharSequence('a', 'z');
        String upperCaseChars = util.getCharSequence('A', 'Z');
        String numberChars = util.getCharSequence('0', '9');
        String specialChars = "!@#$%&*+?";
        StringBuilder passwordChars = new StringBuilder(100);
        if (useLowerCase) {
            passwordChars.append(lowerCaseChars);
        }
        if (useUpperCase) {
            passwordChars.append(upperCaseChars);
        }
        if (useNumbers) {
            passwordChars.append(numberChars);
        }
        if (useSpecialCharacters) {
            passwordChars.append(specialChars);
        }
        String password = generatePassword(passwordChars.toString(), length);
        while (!isValidPassword(password, length, useUpperCase, useNumbers, useSpecialCharacters, useLowerCase)) {
            password = generatePassword(passwordChars.toString(), length);
        }
        return password;
    }
}
