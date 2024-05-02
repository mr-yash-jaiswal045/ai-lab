import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final char USER_SYMBOL = 'X';
    private static final char COMPUTER_SYMBOL = 'O';

    private static char[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printBoard();
        while (true) {
            userMove();
            printBoard();
            if (checkWin(USER_SYMBOL)) {
                System.out.println("Congratulations! You win!");
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            computerMove();
            printBoard();
            if (checkWin(COMPUTER_SYMBOL)) {
                System.out.println("Computer wins! Better luck next time.");
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void userMove() {
        int row, col;
        do {
            System.out.print("Enter your move (row[1-3] and column[1-3] separated by space): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));

        board[row][col] = USER_SYMBOL;
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != EMPTY) {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        return true;
    }

    private static void computerMove() {
        System.out.println("Computer's move:");
        int[] move = findBestMove();
        board[move[0]][move[1]] = COMPUTER_SYMBOL;
    }

    private static int[] findBestMove() {
        // A simple AI strategy: Randomly choose an available empty spot
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != EMPTY);
        return new int[]{row, col};
    }

    private static boolean checkWin(char symbol) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                    (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private static boolean isBoardFull() {
        // Check if the board is full (a draw)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
