import java.util.Scanner;

public class TwoPlayerTicTacToe {
    // Board size constant
    private static final int BOARD_SIZE = 3;
    // Empty cell marker
    private static final char EMPTY_CELL = '-';
    // Board representation
    private char[][] board;
    // Current player
    private char currentPlayer;

    // Constructor to initialize the game
    public TwoPlayerTicTacToe() {
        // Initialize the board
        board = new char[BOARD_SIZE][BOARD_SIZE];
        // Fill the board with empty cells
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
        // First player is 'X'
        currentPlayer = 'X';
    }

    // Method to display the current board
    public void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Method to switch players
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Method to check if the given cell is empty
    public boolean isEmpty(int row, int col) {
        return board[row][col] == EMPTY_CELL;
    }

    // Method to check if the game is over (win or draw)
    public boolean isGameOver() {
        return isWin() || isDraw();
    }

    // Method to check if there's a win
    private boolean isWin() {
        // Check rows, columns, and diagonals for a win
        return checkRows() || checkCols() || checkDiagonals();
    }

    // Helper method to check rows for a win
    private boolean checkRows() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] != EMPTY_CELL && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check columns for a win
    private boolean checkCols() {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] != EMPTY_CELL && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check diagonals for a win
    private boolean checkDiagonals() {
        return (board[0][0] != EMPTY_CELL && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] != EMPTY_CELL && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    // Method to check if the game is a draw
    private boolean isDraw() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false; // Found an empty cell, game is not a draw
                }
            }
        }
        return true; // All cells are filled, game is a draw
    }

    // Method to make a move
    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE || !isEmpty(row, col)) {
            return false; // Invalid move
        }
        board[row][col] = currentPlayer; // Place player's marker on the board
        return true;
    }

    // Method to play the game
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's play Tic Tac Toe!");
        displayBoard();
        while (!isGameOver()) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (makeMove(row, col)) {
                displayBoard();
                if (isWin()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (isDraw()) {
                    System.out.println("It's a draw!");
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        scanner.close();
    }

    // Main method to start the game
    public static void main(String[] args) {
        TwoPlayerTicTacToe game = new TwoPlayerTicTacToe();
        game.playGame();
    }
}
