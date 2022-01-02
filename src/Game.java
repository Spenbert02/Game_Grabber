import java.util.Scanner;

/**
 * abstract class that provides a structure for creating games to be played in the console
 */
public abstract class Game {

    /**
     * prepare a Game sub-class object to play a new game - resetting variables, choosing random starting points, etc.
     * @return a String representing the 'initialization' message to print to the console
     */
    protected abstract String prepToPlay();

    /**
     * check the current state of the game to see if the game is over (ie. the person has won, lost, or neither)
     * @return a boolean: true if the game is over, and false if it is not
     */
    protected abstract boolean isOver();

    /**
     * check a given move to see if it is valid
     * @param move a String representing the inputted move from the user
     * @return a boolean: true if the move is valid, false if it is not
     */
    protected abstract boolean isValid(String move);

    /**
     * process a given move and update the game state
     * @param move a String representing the inputted move from the user - assumes the move is valid
     * @return a String representing the current game state to be printed
     */
    protected abstract String processMove(String move);
    /*
    General Structure:  *note: isValid is used before, so move is valid
        1) update game state
        2) check for win
            a) print win message
                or
            b) print not win message
     */

    /**
     * get the final message to print to the console after the game is over
     * @return a String representing the final message to print
     */
    protected abstract String finalMessage();

    /**
     * get the name of the current game
     * @return a String representing the name of the game being played
     */
    public abstract String getName();

    /**
     * play a round of the game
     * @param user a Scanner object that user inputs will be taken from
     */
    public void play(Scanner user) {
        System.out.println(prepToPlay());  // prepping play and printing start up message
        String userInput = "";
        while (!isOver() && !userInput.equals("quit")) {  // game loop
            System.out.print("Enter Your Move or 'quit' to quit> ");  // getting user input for move
            userInput = user.next();
            while (!(isValid(userInput) || userInput.equals("quit"))) {
                System.out.print("Invalid Move! try again> ");
                userInput = user.next();
            }

            if (!userInput.equals("quit")) {
                String processedMoveMessage = processMove(userInput);  // processing user's move and printing message
                if (processedMoveMessage != null) {
                    System.out.println(processedMoveMessage);
                }
            }
        }
        System.out.println(finalMessage());  // printing final message
    }
}
