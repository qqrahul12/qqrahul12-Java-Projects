package com.rahul.password;

public interface UserInterface {
    public int getLength();
    public boolean useUpperCase();
    public boolean useNumbers();
    public boolean useSpecialCharacters();
    public boolean useLowerCase();
    public void displayPassword(String password);
}
