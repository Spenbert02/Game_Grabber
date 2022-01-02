# Game Grabber
The 'GameGrabber' java class (GameGrabber.java) is a menu-driven
console app that allows the user to play a variety of games. Each
game is represented in its own class, and all game classes extend
the abstract 'Game' class. This inheritance structure enables
use of polymorphism in the main GameGrabber class. This polymorphic
setup makes creating and implementing new games quick and easy.

## Included Games
Already written games include Hangman, RPS (rock paper
scissors), WordJumble, and NumberGuesser. Each game object
in GameGrabber is instantiated with game setting parameters
(outlined for each below), which allow the user to change the
difficulty of each game.

The WordsList class
is used for generating random words in games like Hangman.

### Hangman
Regular ol' hangman. Game parameters:
- minimum and maximum word length
- maximum number of guesses
- list of words to draw from

### RPS
Rock paper scissors, played against the computer. Game parameters:
- number of rounds the player has to win to beat the computer
- number of rounds the player has to lose for the computer to beat the player

### Number Guesser
The computer randomly generates a number, the player has
a set number of tries to guess it. Game parameters:
- range of number values to generate
- maximum number of guesses

### Word Jumble
The computer scrambles a word, the player has to guess
what word was scrambled. Game parameters:
- list of words to draw from
- minimum and maximum word length
- maximum number of guesses

### Adding New Games
New games can be easily added by creating a new class that
extends the 'Game' class, and instantiating an object
of that class where indicated in the GameGrabber class.

## Sample Usage with Provided Games
```
0) Hangman
1) Number Guesser
2) Rock Paper Scissors
3) Word jumble
4) Quit
Pick a game (0-4)>> 0

I've picked a 4 letter word. Guess letters you think are in the word. You get 10 guesses.
Enter Your Move or 'quit' to quit>> a
____
Enter Your Move or 'quit' to quit>> e
____
Enter Your Move or 'quit' to quit>> i
____
Enter Your Move or 'quit' to quit>> o
_o__
Enter Your Move or 'quit' to quit>> u
_o__
Enter Your Move or 'quit' to quit>> n
_o__
Enter Your Move or 'quit' to quit>> r
_o__
Enter Your Move or 'quit' to quit>> s
_o__
Enter Your Move or 'quit' to quit>> t
_o__
Enter Your Move or 'quit' to quit>> w
_o__
The word was: lock

0) Hangman
1) Number Guesser
2) Rock Paper Scissors
3) Word jumble
4) Quit
Pick a game (0-4)>> 1

I've picked a number 1 to 1024. You get 10 guesses to guess it
Enter Your Move or 'quit' to quit>> 512
Too High
Enter Your Move or 'quit' to quit>> 256
Too High
Enter Your Move or 'quit' to quit>> 128
Too Low
Enter Your Move or 'quit' to quit>> 192
Too High
Enter Your Move or 'quit' to quit>> 160
Too High
Enter Your Move or 'quit' to quit>> 144
Too High
Enter Your Move or 'quit' to quit>> 136
Too Low
Enter Your Move or 'quit' to quit>> 140
Too High
Enter Your Move or 'quit' to quit>> 138
Too High
Enter Your Move or 'quit' to quit>> 137
That's it!
The number was: 137

0) Hangman
1) Number Guesser
2) Rock Paper Scissors
3) Word jumble
4) Quit
Pick a game (0-4)>> 2

Enter rock, paper, or scissors. Beat me 3 times before I win 3 times!
Enter Your Move or 'quit' to quit>> rock
rock vs. rock: Tie
Enter Your Move or 'quit' to quit>> rock
scissors vs. rock: You Win
Enter Your Move or 'quit' to quit>> rock
scissors vs. rock: You Win
Enter Your Move or 'quit' to quit>> rock
rock vs. rock: Tie
Enter Your Move or 'quit' to quit>> paper
scissors vs. paper: You Lose
Enter Your Move or 'quit' to quit>> rock
paper vs. rock: You Lose
Enter Your Move or 'quit' to quit>> paper
paper vs. paper: Tie
Enter Your Move or 'quit' to quit>> scissors
paper vs. scissors: You Win
You win the set!

0) Hangman
1) Number Guesser
2) Rock Paper Scissors
3) Word jumble
4) Quit
Pick a game (0-4)>> 3

The following is a jumbled up word: knec You get 10 guesses to guess it
Enter Your Move or 'quit' to quit>> neck
The word was neck

0) Hangman
1) Number Guesser
2) Rock Paper Scissors
3) Word jumble
4) Quit
Pick a game (0-4)>> 4
Goodbye...
```