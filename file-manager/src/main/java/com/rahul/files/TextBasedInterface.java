package com.rahul.files;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TextBasedInterface implements UserInterface {
    private EventHandler handler = null;

    public void start() {
        System.out.println("Welcome to the file manager!");
        System.out.println("Type 'list' to list the files in the current directory.");
        System.out.println("Type 'create <filename>' to create a new file.");
        System.out.println("Type 'delete <filename>' to delete a file.");
        System.out.println("Type 'exit' to exit the file manager.");
        try {
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "list":
                    System.out.println("Please enter the path to list files");
                    String path = scanner.nextLine();
                    handler.onList(path);
                    break;
                case "create":
                    System.out.println("Please enter the name of the directory to create");
                    String createFileName = scanner.nextLine();
                    handler.onCreate(createFileName);
                    break;
                case "delete":
                    System.out.println("Please enter the name of the directory to delete");
                    String deleteFileName = scanner.nextLine();
                    handler.onDelete(deleteFileName);
                    break;
                case "exit":
                    System.out.println("Exiting the file manager");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid input. Please enter a valid choice");
                    scanner.close();
                    start();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Please enter a valid choice");
            start();
        }
    }

    public void subscribe(EventHandler handler) {
        if(this.handler == null) {
            this.handler = handler;
        }
    }

    public void display(String message) {
        System.out.println(message);
    }
}
