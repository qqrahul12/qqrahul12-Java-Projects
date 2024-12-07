package com.skills.SkillBoost.service;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class SudokuService {

    private static final int SIZE = 9; // Size of the Sudoku board
    private static final int SUBGRID_SIZE = 3; // Size of the subgrid

    public int[][] generateSudoku() {
        int[][] board = new int[SIZE][SIZE]; // Initialize an empty board
        fillBoard(board); // Fill the board with valid Sudoku numbers
        removeCells(board, 20); // Remove cells to create the puzzle (20 cells removed as an example)
        return board; // Return the puzzle
    }

    private void fillBoard(int[][] board) {
        fillDiagonalSubgrids(board); // Fill the diagonal subgrids first
        fillRemainingCells(board, 0, SUBGRID_SIZE); // Fill the remaining cells
    }

    private void fillDiagonalSubgrids(int[][] board) {
        for (int i = 0; i < SIZE; i += SUBGRID_SIZE) {
            fillSubgrid(board, i, i); // Fill each diagonal subgrid
        }
    }

    private void fillSubgrid(int[][] board, int row, int col) {
        boolean[] used = new boolean[SIZE + 1]; // Track used numbers in the subgrid
        Random random = new Random();
        for (int i = 0; i < SUBGRID_SIZE; i++) {
            for (int j = 0; j < SUBGRID_SIZE; j++) {
                int num;
                do {
                    num = random.nextInt(SIZE) + 1; // Generate a random number
                } while (used[num]); // Ensure the number is not already used
                used[num] = true; // Mark the number as used
                board[row + i][col + j] = num; // Place the number in the subgrid
            }
        }
    }

    private boolean fillRemainingCells(int[][] board, int row, int col) {
        if (col >= SIZE && row < SIZE - 1) {
            row++;
            col = 0;
        }
        if (row >= SIZE && col >= SIZE) {
            return true; // All cells are filled
        }
        if (row < SUBGRID_SIZE) {
            if (col < SUBGRID_SIZE) {
                col = SUBGRID_SIZE; // Skip the first subgrid
            }
        } else if (row < SIZE - SUBGRID_SIZE) {
            if (col == (row / SUBGRID_SIZE) * SUBGRID_SIZE) {
                col += SUBGRID_SIZE; // Skip the subgrid
            }
        } else {
            if (col == SIZE - SUBGRID_SIZE) {
                row++;
                col = 0;
                if (row >= SIZE) {
                    return true; // All cells are filled
                }
            }
        }
        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num; // Place the number if it's safe
                if (fillRemainingCells(board, row, col + 1)) {
                    return true; // Continue to fill the next cell
                }
                board[row][col] = 0; // Reset the cell if no number fits
            }
        }
        return false; // No valid number found for this cell
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        return !usedInRow(board, row, num) &&
                !usedInCol(board, col, num) &&
                !usedInSubgrid(board, row - row % SUBGRID_SIZE, col - col % SUBGRID_SIZE, num);
    }

    private boolean usedInRow(int[][] board, int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (board[row][col] == num) {
                return true; // Number is already used in the row
            }
        }
        return false;
    }

    private boolean usedInCol(int[][] board, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][col] == num) {
                return true; // Number is already used in the column
            }
        }
        return false;
    }

    private boolean usedInSubgrid(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < SUBGRID_SIZE; row++) {
            for (int col = 0; col < SUBGRID_SIZE; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return true; // Number is already used in the subgrid
                }
            }
        }
        return false;
    }

    private void removeCells(int[][] board, int cellsToRemove) {
        Random random = new Random();
        for (int i = 0; i < cellsToRemove; i++) {
            int row, col;
            do {
                row = random.nextInt(SIZE);
                col = random.nextInt(SIZE);
            } while (board[row][col] == 0); // Ensure the cell is not already empty
            board[row][col] = 0; // Remove the cell
        }
    }
}