package com.rahul.files;

import java.io.File;

public class FileManager {
    public void listFiles(String path) {
        File folder = new File(path);
        if(folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for(File file : files) {
                    System.out.println( file.isFile() ? "File - " : "Directory - " + file.getName());
                }
            } else {
                System.out.println("Folder is empty");
            }
        } else {
            System.out.println("Folder does not exist or is not a directory");
        }
    }

    public void createDirectory(String path) {
        File folder = new File(path);
        if(folder.mkdir()) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Failed to create directory");
        }
    }

    public void deleteDirectory(String path) {
        File folder = new File(path);
        if(folder.exists() && folder.isDirectory()) {
            if(folder.delete()) {
                System.out.println("Directory deleted successfully");
            } else {
                System.out.println("Failed to delete directory");
            }
        } else {
            System.out.println("Directory does not exist or is not a directory");
        }
    }
}
