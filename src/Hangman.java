import java.util.Random;
import java.util.Scanner;

/**
 * class that can be used to play a game of Hangman in the console, utilizing the abstract Game class framework
 */
public class Hangman extends Game {
    /* 'Game Setting' member variables */
    int minWordLength;  // length of the shortest allowable word
    int maxWordLength;  // length of the longest allowable word
    int maxGuesses;  // maximum number of guesses the user can enter before losing
    WordsList words;  // WordsList object to get words from

    /* 'Gameplay' member variables: */
    private int guessCount;  // current number of letters guessed
    private String secretWord;  // word the user is trying to guess
    private String currWord;  // current word, with unguessed characters as '_'

    /**
     * instantiate an object of class Hangman
     * @param words a WordsList object from which random words will be pulled for hangman games
     * @param minWordLen an int representing the minimum length of a word that will be used for a game of Hangman
     * @param maxWordLen an int representing the maximum length of a word that will be used for a game of Hangman
     * @param maxGuesses the maximum number of incorrect guesses a user can make before losing
     */
    public Hangman(WordsList words, int minWordLen, int maxWordLen, int maxGuesses) {
        this.minWordLength = minWordLen;
        this.maxWordLength = maxWordLen;
        this.maxGuesses = maxGuesses;
        this.words = words;
    }

    /**
     * prepare a game of Hangman by clearing old game and generating a new random word
     * @return a String representing the initialization message to show to the user
     */
    @Override
    protected String prepToPlay() {
        secretWord = words.getWord(minWordLength, maxWordLength);  // generating and setting random word

        guessCount = 0;
        currWord = "";
        for (int i = 0; i < secretWord.length(); i++) {
            currWord += "_";
        }

        return "I've picked a " + secretWord.length() +  // returning update message
                " letter word. Guess letters you think are in the word. You get "
                + maxGuesses + " guesses.";
    }

    /**
     * check if a game of Hangman is over. A game is considered over if the user has guessed the word or if the
     * user exceeds the maximum incorrect guess limit
     * @return
     */
    @Override
    protected boolean isOver() {
        if (guessCount >= maxGuesses) {  // if maxed out guesses, game is over
            return true;
        } else {
            for (int i = 0; i < secretWord.length(); i++) {  // if any character is '_', game isn't over
                if (currWord.charAt(i) == '_') {
                    return false;
                }
            }
            return true;  // if none of the characters are '_', every letter has been guessed (won)
        }
    }

    /**
     * check whether a given move is valid. A move is considered valid if it consists of a one character String,
     * where the one character is a letter.
     * @param move a String representing the inputted move from the user
     * @return a boolean: true if the given move is valid, and false if it is invalid
     */
    @Override
    protected boolean isValid(String move) {
        if (move.length() == 1) {  // is a single character
            char entryChar = move.charAt(0);  // turn one character string into character
            if (entryChar >= 97 && entryChar <= 122) {  // character is in alphabet
                return true;
            }
        }
        return false;
    }

    /**
     * process a given move by updating the guess counter and guessed letters, and return a String
     * representing the new game state
     * @param move a String representing the inputted move from the user - assumes the move is valid
     * @return a String representing the game state. Ex: for guessed letters (a, l) and the word 'apple',
     *         the processMove function would return the String "a__l_e".
     */
    @Override
    protected String processMove(String move) {
        char guessChar = move.toLowerCase().charAt(0);  // converting move String into char
        boolean correctGuess = false;  // variable to track if guessed letter is correct
        for (int i = 0; i < secretWord.length(); i++) {  // checking every letter to see if has changed
            if (secretWord.charAt(i) == guessChar) {
                if (currWord.charAt(i) == '_') {  // correct letter guessed
                    currWord = currWord.substring(0, i) + guessChar + currWord.substring(i + 1);
                    correctGuess = true;
                }
            }
        }
        guessCount++;
        return currWord;
    }

    /**
     * get a String which reveals the secret word
     * @return a String of the form: "the word was <word>"
     */
    @Override
    protected String finalMessage() {
        return "The word was: " + secretWord;
    }

    /**
     * get the name of the game: Hangman
     * @return a String: "Hangman"
     */
    @Override
    public String getName() {
        return "Hangman";
    }

    public static void main(String[] args) {
        Game hangman = new Hangman(new WordsList(new Random()), 0, 20, 10);
        hangman.play(new Scanner(System.in));
    }
}
