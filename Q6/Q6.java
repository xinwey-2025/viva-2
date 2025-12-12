import java.util.Random;
import java.util.Scanner;

public class Q6 {
    public static void main(String[] args) {
        int[][] board = getGameBoard();
        String movement;

        while (true) {
            movement = getMovement();
            if (movement.equals("up") ||
                movement.equals("down") ||
                movement.equals("left") ||
                movement.equals("right")) {
                break;
            }
            System.out.println("Invalid movement. Only up, down, left, right is accepted. Reenter: ");
        }

        switch (movement) {
            case "up": up(board); break;
            case "down": down(board); break;
            case "left": left(board); break;
            case "right": right(board); break;
        }

        addNewRandomElement(board);

        System.out.println();
        printBoard(board);

    }

    private static int[][] getGameBoard() {
        System.out.println("Enter 4x4 game board: ");

        int[][] board = new int[4][4];
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = input.nextInt();
            }
        }

        return board;
    }

    private static String getMovement() {
        System.out.print("\nEnter movement: ");
        Scanner input = new Scanner(System.in);
        return input.next().toLowerCase();
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    /* general rule for all movements

       shift cells to clear out zeroes, perform merging, then shift cells to clear zeroes again

       check for zeros/shift cells and merge cells against the direction of movement
       e.g. movement is left, perform operation from left to right
       (moving towards right, as opposed to moving to left)

       shift cells three times, because
       the maximum movement to shift let's say bottom element to top by swapping two elements one
       at a time is three (within four cells)
     */

    private static void left(int[][] board) {   // <-
        for (int row = 0; row < 4; row++) {  // perform action for all rows
            // shift cells to clear out zeroes. left to right  ->
            for (int i = 0; i < 3; i++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == 0) {
                        swap(board, row, col, row, col + 1);
                    }
                }
            }

            // perform merge. left to right  ->
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == board[row][col + 1]) {
                    board[row][col] *= 2;
                    board[row][col + 1] = 0;
                }
            }

            // shift cells again. left to right  ->
            for (int i = 0; i < 3; i++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == 0) {
                        swap(board, row, col, row, col + 1);
                    }
                }
            }
        }
    }

    private static void right(int[][] board) {  // >
        for (int row = 0; row < 4; row++) {  // perform action for all rows
            // shift cells to clear out zeroes. right to left  <-
            for (int i = 0; i < 3; i++) {
                for (int col = 3; col > 0; col--) {
                    if (board[row][col] == 0) {
                        swap(board, row, col, row, col - 1);
                    }
                }
            }

            // perform merge. right to left <-
            for (int col = 3; col > 0; col--) {
                if (board[row][col] == board[row][col - 1]) {
                    board[row][col] *= 2;
                    board[row][col - 1] = 0;
                }
            }

            // shift cells again. right to left  <-
            for (int i = 0; i < 3; i++) {
                for (int col = 3; col > 0; col--) {
                    if (board[row][col] == 0) {
                        swap(board, row, col, row, col - 1);
                    }
                }
            }
        }
    }

    private static void up(int[][] board) {  // ^
        for (int col = 0; col < 4; col ++) {  // perform action for all columns
            for (int i = 0; i < 3; i++) {
                // check from bottom to top, clear out zeroes by swapping elements  ^
                for (int row = 3; row > 0; row--) {
                    if (board[row - 1][col] == 0)
                        swap(board, row, col, row - 1, col);
                }
            }

            // perform merging from top to bottom  ^'
            for (int row = 0; row < 3; row++) {
                if (board[row][col] == board[row + 1][col]) {
                    board[row][col] *= 2;
                    board[row + 1][col] = 0;
                }
            }

            // perform swapping again. bottom to top  ^
            for (int i = 0; i < 3; i++) {
                for (int row = 3; row > 0; row--) {
                    if (board[row - 1][col] == 0)
                        swap(board, row, col, row - 1, col);
                }
            }
        }
    }

    private static void down(int[][] board) {
        for (int col = 0; col < 4; col ++) {  // perform action for all columns
            for (int i = 0; i < 3; i++) {
                // check from top to bottom, clear out zeroes by swapping elements  ^'
                for (int row = 0; row < 3; row++) {
                    if (board[row + 1][col] == 0)
                        swap(board, row, col, row + 1, col);
                }
            }

            // perform merging from top to bottom  ^'
            for (int row = 3; row > 0; row--) {
                if (board[row][col] == board[row - 1][col]) {
                    board[row][col] *= 2;
                    board[row - 1][col] = 0;
                }
            }

            // perform swapping again. top to bottom  ^'
            for (int i = 0; i < 3; i++) {
                // check from top to bottom, clear out zeroes by swapping elements  ^'
                for (int row = 0; row < 3; row++) {
                    if (board[row + 1][col] == 0)
                        swap(board, row, col, row + 1, col);
                }
            }
        }
    }

    private static void addNewRandomElement(int[][] board) {
        boolean hasEmpty = false;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == 0) {
                    hasEmpty = true;
                }
            }
        }

        if (!hasEmpty) {
            System.out.println("\nBoard is full. Game over.");
            return;
        }

        Random random = new Random();
        while (true) {
            int row = random.nextInt(4);
            int col = random.nextInt(4);
            if (board[row][col] == 0) {
                board[row][col] = new int[] {2, 4}[random.nextInt(2)];
                System.out.printf("\nNew element %d at position %d, %d\n", board[row][col], row, col);
                return;
            }
        }
    }

    private static void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }
}