package com.rahul.password;

public class PasswordGenerationApplication {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        UserInterface ui = new TextInterface();
        int length = ui.getLength();
        boolean useUpperCase = ui.useUpperCase();
        boolean useNumbers = ui.useNumbers();
        boolean useSpecialCharacters = ui.useSpecialCharacters();
        boolean useLowerCase = ui.useLowerCase();
        String password = PasswordGenerator.generatePassword(length, useUpperCase, useNumbers, useSpecialCharacters, useLowerCase);
        ui.displayPassword(password);
    }
}
