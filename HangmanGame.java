import java.util.*;

public class HangmanGame {
    private static final String[] WORDS = {"computer", "java", "programming", "algorithm", "openai"};
    private static final int MAX_TRIES = 6;

    private String secretWord;
    private StringBuilder guessedWord;
    private Set<Character> guessedLetters;
    private int triesLeft;

    public HangmanGame() {
        secretWord = WORDS[(int) (Math.random() * WORDS.length)];
        guessedWord = new StringBuilder("-".repeat(secretWord.length()));
        guessedLetters = new HashSet<>();
        triesLeft = MAX_TRIES;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        System.out.println("Try to guess the word:");
        System.out.println(guessedWord);

        while (triesLeft > 0 && guessedWord.indexOf("-") != -1) {
            System.out.println("Guess a letter:");
            char guess = scanner.next().charAt(0);
            if (!Character.isLetter(guess)) {
                System.out.println("Please enter a valid letter.");
                continue;
            }

            guessLetter(guess);
            System.out.println("Word: " + guessedWord);
            System.out.println("Tries left: " + triesLeft);
        }

        if (guessedWord.indexOf("-") == -1) {
            System.out.println("Congratulations! You guessed the word: " + secretWord);
        } else {
            System.out.println("Sorry, you ran out of tries. The word was: " + secretWord);
        }
    }

    private void guessLetter(char guess) {
        if (guessedLetters.contains(guess)) {
            System.out.println("You've already guessed this letter.");
            return;
        }

        guessedLetters.add(guess);

        if (secretWord.indexOf(guess) != -1) {
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess) {
                    guessedWord.setCharAt(i, guess);
                }
            }
        } else {
            triesLeft--;
            System.out.println("Incorrect guess!");
        }
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        game.play();
    }
}
