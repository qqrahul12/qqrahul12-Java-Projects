package com.game.dice.model;

public class Dice {
    static int sides = 6;
    public static void changeSides(int newSides) {
        if (newSides > 0) {
            sides = newSides;
        } else {
            System.out.println("Invalid number of sides. Must be greater than 0.");
        }
    }
}
