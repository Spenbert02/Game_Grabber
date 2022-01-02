import java.util.Random;

/**
 * class to play a Number Guessing game in the console, utilizing the Game class framework
 */
public class NumberGuesser extends Game {
    /* 'Game setting' member variables */
    private Random rng;  // random number generator
    private int maxNumber;  // maximum value for a given randomly generated number
    private int maxGuesses;  // maximum number of guesses until a player loses

    /* 'Gameplay' variables */
    int guessesLeft;  // the number of guesses left for the user
    int secretNumber;  // the number the user is trying to guess
    int currGuess;  // the current guess of the user

    /**
     * instantiate an object of the NumberGuesser class
     * @param rng a Random object to randomly generate numbers
     * @param maxNumber an int representing the largest random number than can be generated
     * @param maxGuesses an int representing the max number of guesses the user can make before they lose
     */
    public NumberGuesser(Random rng, int maxNumber, int maxGuesses) {
        this.rng = rng;
        this.maxNumber = maxNumber;
        this.maxGuesses = maxGuesses;
    }

    /**
     * prepare the NumberGuesser object to play a new game - resetting internal member variables
     * @return a String representing a message to print to the user
     */
    @Override
    protected String prepToPlay() {
        guessesLeft = maxGuesses;
        secretNumber = rng.nextInt(maxNumber) + 1;  // getting random number from 1 to maxNumber
        currGuess = -1;
        return "I've picked a number 1 to " + maxNumber + ". You get " + maxGuesses + " guesses to guess it";
    }

    /**
     * check if a game of NumberGuesser is over in its current state
     * @return a boolean: true if the game is over, false if it is not
     */
    @Override
    protected boolean isOver() {
        return guessesLeft == 0 || currGuess == secretNumber;  // if no guesses left or number was guessed, game over
    }

    /**
     * check if a given user entered move is valid - ie. is an integer
     * @param move a String representing the inputted move from the user
     * @return a boolean: true if the given move is valid, false if it is invalid
     */
    @Override
    protected boolean isValid(String move) {
        boolean allDigits = true;
        for (int i = 0; i < move.length(); i++) {  // iterating through each character in move
            int currChar = move.charAt(i);
            if (currChar < 48 || currChar > 57) {  // character is not a digit
                allDigits = false;
            }
        }
        return allDigits;
    }

    /**
     * process a given user entered move and update the game state. Assumes given move is valid
     * @param move a String representing the inputted move from the user - assumes the move is valid
     * @return
     */
    @Override
    protected String processMove(String move) {
        currGuess = Integer.parseInt(move);  // parsing String into int
        guessesLeft--;  // decrementing guesses
        if (currGuess < secretNumber) {  // guess is too low
            return "Too Low";
        } else if (currGuess > secretNumber) {  // guess is too high
            return "Too High";
        } else {  // guess is correct
            return "That's it!";
        }
    }

    /**
     * get the final message to print after the game of NumberGuesser is over
     * @return a String representing the final message
     */
    @Override
    protected String finalMessage() {
        return "The number was: " + secretNumber;
    }

    /**
     * get the name the game
     * @return a String: "Number Guesser"
     */
    @Override
    public String getName() {
        return "Number Guesser";
    }
}
