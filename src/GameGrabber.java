import java.util.Random;
import java.util.Scanner;

/**
 * class that provides a framework for a game grabber user interface. This interface consists of choosing a game to
 * play, playing that game, then either choosing a new game to play or quitting, repeating this process until the
 * user decides to quit.
 */
public class GameGrabber {
    private Scanner user;  // where to get info from during interaction with the user
    private Game[] games;  // an array of game objects representing different options in the play menu
    int lastSelection;  // int representing user's last selection

    /**
     * print all game options to the screen, numbered and in order
     */
    private void showOptions() {
        for (int i = 0; i < games.length; i++) {
            System.out.println(i + ") " + games[i].getName());
        }
        System.out.println(games.length + ") Quit");
    }

    /**
     * instantiate on object of the GameGrabber class
     * @param games an array of Game objects, each representing a game that will be displayed in the options menu
     * @param user a Scanner object for getting user input
     */
    public GameGrabber(Game[] games, Scanner user) {
        this.user = user;
        this.games = games;
        this.lastSelection = 0;  // defaulting to first game in games[]
    }

    /**
     * check if the user wants to quit, based on the last user input for what game to play
     * @return true if the user wants to quit, false if the user doesn't
     */
    public boolean userWantsToQuit() {
        return lastSelection == games.length;
    }


    /**
     * get a valid game to play from the user by iteratively printing a list of games and getting user input
     */
    public void doMenu() {
        while (!userWantsToQuit()) {  // until the user wants to quit
            String prompt = "Pick a game (0-" + (games.length) + ")> ";  // message to print to the user

            showOptions();  // printing all user options to the console
            System.out.print(prompt);  // printing prompt

            int userInput = user.nextInt();  // getting the selection from the user
            while (userInput < 0 || userInput > games.length) {  // while user input isn't numeric
                System.out.print(prompt);
                userInput = user.nextInt();
            }

            this.lastSelection = userInput;  // once userInput is valid, update the last input

            if (userInput != games.length) {
                games[userInput].play(user);
            } else {
                System.out.println("Goodbye...");
            }
        }
    }

    public static void main(String[] args) {
        Game[] games = new Game[4];  // array of game objects

        WordsList wordsList = new WordsList(new Random());  // to be used by the Hangman and WordJumble games

        // creating and adding a Hangman game object to the games array
        games[0] = new Hangman(wordsList, 0, 20, 10);

        // creating and adding a NumberGuesser game object to the games array
        games[1] = new NumberGuesser(new Random(), 32768, 15);

        // creating and adding an RPS Game object to the games array
        games[2] = new RPS(new Random(), 3, 3);

        // creating and adding a WordJumble object to the games array
        games[3] = new WordJumble(wordsList, new Random(), 0, 20, 10);

        Scanner scanner = new Scanner(System.in);

        GameGrabber grabber = new GameGrabber(games, scanner);

        grabber.doMenu();
    }
}
