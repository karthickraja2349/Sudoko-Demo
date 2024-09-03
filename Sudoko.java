/** example :
  | 5  7  1 | 8  6  3 | 2  4  9 |
| 2  9  8 | 1  4  6 | 3  7  5 |
| 8  6  4 | 7  2  5 | 6  9  1 |
+---------+---------+---------+
| 6  3  2 | 9  7  8 | 4  1  5 |
| 7  1  9 | 2  4  6 | 8  3  5 |
| 4  8  5 | 1  3  2 | 7  6  9 |
+---------+---------+---------+
| 1  3  7 | 4  5  9 | 9  2  8 |
| 9  2  6 | 3  8  7 | 1  5  4 |
| 5  4  8 | 6  1  2 | 3  8  7 |
*/
import java.util.Scanner;
class Sudoko {
    private char[][] gameBoard;
    private Scanner input = new Scanner(System.in);

    public Sudoko() {
        gameBoard = new char[9][9];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                gameBoard[i][j] =' ';
            }
        }
        numberInitialization();
        printBoard();
        userInput();
    }

    private void printBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            if (i % 3 == 0 && i != 0) {
                printHorizontalBorder();
            }
            System.out.print("|");
            for (int j = 0; j < gameBoard[0].length; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                System.out.print(" " + (gameBoard[i][j] == ' ' ? " " : gameBoard[i][j]) + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        printHorizontalBorder();
    }

    private void printHorizontalBorder() {
        System.out.print("+");
        for (int i = 0; i < gameBoard[0].length; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.print("+");
            }
            System.out.print("---");
        }
        System.out.print("+");
        System.out.println();
    }

    public static void main(String[] args) {
        new Sudoko();
    }

    private void numberInitialization() {
        gameBoard[0][1] = '5';
        gameBoard[1][1] = '9';
        gameBoard[2][0] = '8';
        gameBoard[2][2] = '4';
        gameBoard[0][4] = '6';
        gameBoard[2][5] = '5';
        gameBoard[2][6] = '6';
        gameBoard[2][7] = '9';
        gameBoard[1][6] = '3';
        gameBoard[1][7] = '7';
        gameBoard[0][6] = '2';
        gameBoard[4][0] = '7';
        gameBoard[4][1] = '1';
        gameBoard[4][2] = '9';
        gameBoard[4][3] = '2';
        gameBoard[4][4] = '4';
        gameBoard[4][5] = '6';
        gameBoard[4][6] = '8';
        gameBoard[4][7] = '3';
        gameBoard[4][8] = '5';
        gameBoard[3][5] = '8';
        gameBoard[5][3] = '1';
        gameBoard[6][1] = '3';
        gameBoard[6][2] = '5';
        gameBoard[6][3] = '4';
        gameBoard[6][6] = '1';
        gameBoard[6][8] = '9';
        gameBoard[7][0] = '9';
        gameBoard[7][2] = '6';
        gameBoard[7][7] = '2';
        gameBoard[8][2] = '7';
        gameBoard[8][5] = '2';
        gameBoard[8][7] = '8';
    }

    private void userInitialization(int row, int column, char element) {
        if (gameBoard[row][column] != ' ') {
            System.out.println("You can't modify the predefined values");
        } 
        else {
            if (isValidPlacement(row, column, element)) {
                gameBoard[row][column] = element;
                printBoard();
            } else {
                System.out.println("Invalid placement! Try again.");
            }
        }
        userInput();
    }

    private void userInput() {
        System.out.println("Enter the row number (0-8) that you want to fill:");
        int row = input.nextInt();
        System.out.println("Enter the column number (0-8) that you want to fill:");
        int column = input.nextInt();
        try {
            System.out.println("Enter the element (1-9):");
            char element = input.next().charAt(0);
            if (element >= '1' && element <= '9') {
                userInitialization(row, column, element);
            } else {
                System.out.println("Element is invalid. Please provide a number between 1 and 9.");
                userInput();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Row or column number out of bounds. Please provide a number between 0 and 8.");
            userInput();
        }
    }

    private boolean isValidPlacement(int row, int column, char element) {
        return !isInRow(row, element) && !isInColumn(column, element) && !isInSubGrid(row, column, element);
    }

    private boolean isInRow(int row, char element) {
        for (int i = 0; i < gameBoard[row].length; i++) {
            if (gameBoard[row][i] == element) {
                return true;
            }
        }
        return false;
    }

    private boolean isInColumn(int column, char element) {
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i][column] == element) {
                return true;
            }
        }
        return false;
    }

    private boolean isInSubGrid(int row, int column, char element) {
        int subGridRowStart = (row / 3) * 3;
        int subGridColStart = (column / 3) * 3;
        for (int i = subGridRowStart; i < subGridRowStart + 3; i++) {
            for (int j = subGridColStart; j < subGridColStart + 3; j++) {
                if (gameBoard[i][j] == element) {
                    return true;
                }
            }
        }
        return false;
    }
}


