import java.util.Random;

/**
 * class that can be used to play a game of rock paper scissors in the console, utilizing the Game class framework
 */
public class RPS extends Game {
    /* 'Game setting' variables - remain the same from round to round */
    private Random rng;  // for randomly generating moves
    private int requiredWins;  // number of rounds the player must win in order to beat the AI
    private int maxLosses;  // number of rounds the player must lose for the AI to beat the player

    /* 'Gameplay' member variables - are reset from game to game */
    private int userWins;
    private int aiWins;

    /**
     * instantiate an RPS object
     * @param rng a Random number generator to randomly generate moves
     * @param requiredWins the number of rounds the user must win to win the set
     * @param maxLosses the number of rounds the AI must win to win the set
     */
    public RPS(Random rng, int requiredWins, int maxLosses) {
        this.rng = rng;
        this.requiredWins = requiredWins;
        this.maxLosses = maxLosses;
    }

    /**
     * prepare an RPS object to play a new set
     * @return a String representing a message to display to the user
     */
    @Override
    protected String prepToPlay() {
        userWins = 0;
        aiWins = 0;
        return "Enter rock, paper, or scissors. Beat me " + requiredWins +
                " times before I win " + maxLosses + " times!";
    }

    /**
     * check if a game of RPS is over in the given state
     * @return a boolean: true if the game is over, false if it is not
     */
    @Override
    protected boolean isOver() {
        return userWins >= requiredWins || aiWins >= maxLosses;
    }

    /**
     * check if a given user entered move is valid
     * @param move a String representing the inputted move from the user
     * @return a boolean: true if the move is valid, false if it is invalid
     */
    @Override
    protected boolean isValid(String move) {
        return move.equals("rock") || move.equals("paper") || move.equals("scissors");
    }

    /**
     * process a given user entered move
     * @param move a String representing the inputted move from the user - assumes the move is valid
     * @return a String representing a message to be displayed to the user
     */
    @Override
    protected String processMove(String move) {
        // randomly choosing AI move
        int randNum = rng.nextInt(3);  // 0-rock, 1-paper, 2-scissors

        String retString = "";  // message to print to display

        // determining if player won
        if (randNum == 0) {  // AI chooses rock
            retString += "rock vs. " + move;
            if (move.equals("rock")) {  // tie
                retString += ": Tie";
            } else if (move.equals("paper"))  {  // user wins
                userWins++;
                retString += ": You Win";
            } else {  // AI wins
                aiWins++;
                retString += ": You Lose";
            }
        } else if (randNum == 1) {  // AI chooses paper
            retString += "paper vs. " + move;
            if (move.equals("rock")) {  // AI wins
                retString += ": You Lose";
                aiWins++;
            } else if (move.equals("paper"))  {  // tie
                retString += ": Tie";
            } else {  // user wins
                userWins++;
                retString += ": You Win";
            }
        } else {  // AI chooses scissors
            retString += "scissors vs. " + move;
            if (move.equals("rock")) {  // user wins
                userWins++;
                retString += ": You Win";
            } else if (move.equals("paper"))  {  // AI wins
                aiWins++;
                retString += ": You Lose";
            } else {  // tie
                retString += ": Tie";
            }
        }
        return retString;
    }

    /**
     * get the final message to display to the user, after a set of RPS
     * @return a String representing the final message to print to the user
     */
    @Override
    protected String finalMessage() {
        if (userWins >= requiredWins) {
            return "You win the set!";
        } else {
            return "You lose the set...";
        }
    }

    /**
     * get the name of RPS
     * @return a String: "Rock Paper Scissors"
     */
    @Override
    public String getName() {
        return "Rock Paper Scissors";
    }
}
