import java.util.Random;

/**
 * class that can be used to play a game of Word Jumble in the console, utilizing the Game class framework.
 */
public class WordJumble extends Game{
    /* Game setting variables */
    private WordsList wordsList;  // list of words to choose word from
    private Random rng;  // random number generator
    private int minWordLength;  // minimum random word length
    private int maxWordLength;  // maximum random word length
    private int maxGuesses;  // maximum number of guesses

    /* Gameplay variables */
    private int guessesLeft;  // current number of guesses the user has made this game
    private String currGuess;  // current guess from the user
    private String secretWord;  // current secret word the user is trying to guess
    private String jumbledWord;  // jumbled up word the user can see

    public WordJumble(WordsList wordsList, Random rng, int minWordLength, int maxWordLength, int maxGuesses) {
        this.wordsList = wordsList;
        this.rng = rng;
        this.minWordLength = minWordLength;
        this.maxWordLength = maxWordLength;
        this.maxGuesses = maxGuesses;
    }

    /**
     * prepare a WordJumble object to play a game, by resetting internal variables
     * @return a String representing the 'initialization' message to print to the console
     */
    @Override
    protected String prepToPlay() {
        guessesLeft = maxGuesses;
        secretWord = wordsList.getWord(minWordLength, maxWordLength);
        currGuess = "";

        // jumbling the word:
        jumbledWord = "";
        for (int i = 0; i < secretWord.length(); i++) {  // making jumbled word have same length as secretWord, all spaces
            jumbledWord += ' ';
        }
        for (int i = 0; i < secretWord.length(); i++) {
            int randLoc = rng.nextInt(secretWord.length());
            while (jumbledWord.charAt(randLoc) != ' ') {  // while the randomly generated location is already filled
                randLoc = rng.nextInt(secretWord.length());
            }
            jumbledWord = jumbledWord.substring(0, randLoc) + secretWord.charAt(i) + jumbledWord.substring(randLoc + 1);
        }

        return "The following is a jumbled up word: " + jumbledWord +
                " You get " + maxGuesses + " guesses to guess it";
    }

    /**
     * check the current state of the game to see if the game is over (ie. check to see if the player is out of guesses
     * or if the player has correctly guessed the word
     * @return a boolean: true if the game is over, and false if it is not
     */
    @Override
    protected boolean isOver() {
        return currGuess.equals(secretWord) || guessesLeft == 0;
    }

    /**
     * check a given move to see if it is valid - for WordJumble objects, this will always return true. This function is
     * necessary to utilize the Game framework
     * @param move a String representing the inputted move from the user
     * @return true
     */
    @Override
    protected boolean isValid(String move) {
        return true;
    }

    /**
     * process a given move and update the game state - ie. take the user's input and see if it is the word they are
     * trying to unscramble
     * @param move a String representing the inputted move from the user - assumes the move is valid
     * @return a String representing the current game state to be printed
     */
    @Override
    protected String processMove(String move) {
        // copying move into currGuess
        currGuess = "";
        for (int i = 0; i < move.length(); i++) {
            currGuess += move.charAt(i);
        }

        if (move.equals(secretWord)) {
            return null;
        } else {
            guessesLeft--;
            return "That's not it";
        }
    }

    /**
     * get the final message to print to the console after the game is over
     * @return a String representing the final message to print
     */
    @Override
    protected String finalMessage() {
        return "The word was " + secretWord;
    }

    /**
     * get the name of the current game - "Word jumble"
     * @return a String: " "Word jumble"
     */
    @Override
    public String getName() {
        return "Word jumble";
    }
}
