package com.game.SkillBoost.util;

public class Pattern {
    public static void printSolidRectangle(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printHollowRectangle(int rows, int columns) {
        for (int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if (j==0 || i==0 || i==rows-1 || j==columns-1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void printHalfPyramid(int row) {
        for (int i=1; i<=row; i++) {
            for (int j=1; j<=i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
