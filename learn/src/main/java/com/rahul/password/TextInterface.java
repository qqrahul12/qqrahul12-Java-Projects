package com.rahul.password;

import java.util.Scanner;

public class TextInterface implements UserInterface {
    private final Scanner scanner;

    public TextInterface() {
        this.scanner = new Scanner(System.in);
    }
    public int getLength() {
        System.out.print("Enter the length of the password: ");
        int length;
        try {
            length = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            length = getLength();
        }
        return length;
    }

    public boolean useUpperCase() {
        System.out.print("Do you want to use uppercase characters? (y/n): ");
        return scanner.next().equalsIgnoreCase("y");
    }

    public boolean useNumbers() {
        System.out.print("Do you want to use numerical characters? (y/n): ");
        return scanner.next().equalsIgnoreCase("y");
    }

    public boolean useSpecialCharacters() {
        System.out.print("Do you want to use special characters? (y/n): ");
        return scanner.next().equalsIgnoreCase("y");
    }

    public boolean useLowerCase() {
        System.out.print("Do you want to use lowercase characters? (y/n): ");
        return scanner.next().equalsIgnoreCase("y");
    }

    public void displayPassword(String password) {
        System.out.println("Generated password: " + password);
    }
}
