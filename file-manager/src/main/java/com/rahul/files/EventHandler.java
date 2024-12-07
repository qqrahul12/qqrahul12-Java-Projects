package com.rahul.files;

public interface EventHandler {
    public void onList(String path);
    public void onCreate(String path);
    public void onDelete(String path);
}
