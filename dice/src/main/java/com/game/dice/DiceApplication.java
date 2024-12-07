package com.game.dice;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class DiceApplication
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        rollDice();
    }

    static String displayDiceValue(int diceValue) {
        return switch (diceValue) {
            case 1 -> "---------\n|       |\n|   o   |\n|       |\n---------";
            case 2 -> "---------\n| o     |\n|       |\n|     o |\n---------";
            case 3 -> "---------\n| o     |\n|   o   |\n|     o |\n---------";
            case 4 -> "---------\n| o   o |\n|       |\n| o   o |\n---------";
            case 5 -> "---------\n| o   o |\n|   o   |\n| o   o |\n---------";
            case 6 -> "---------\n| o   o |\n| o   o |\n| o   o |\n---------";
            default -> "Invalid dice value";
        };
    }

    static void rollDice() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("How many dice would you like to roll?");
            int numberOfDice = scanner.nextInt();

            for (int i = 0; i < numberOfDice; i++) {
                int diceValue = random.nextInt(6) + 1;
                System.out.println(displayDiceValue(diceValue));
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            rollDice();
        }
    }
}
