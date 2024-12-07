package com.rahul.files;

public class FileApp implements EventHandler {
    FileManager fileManager;

    public FileApp() {
        this.fileManager = new FileManager();
    }
    public static void main(String[] args) {
        UserInterface ui = new TextBasedInterface();
        ui.subscribe(new FileApp());
        ui.start();
    }

    @Override
    public void onList(String path) {
        this.fileManager.listFiles(path);
    }

    @Override
    public void onCreate(String path) {
        this.fileManager.createDirectory(path);
    }

    @Override
    public void onDelete(String path) {
        this.fileManager.deleteDirectory(path);
    }
}
