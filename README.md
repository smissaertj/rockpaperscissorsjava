# Rock Paper Scissors Game in Java
This is a Java implementation of the classic game "Rock, Paper, Scissors" with some custom game options added.

## How It Works
When you start the game, it will:
1. Prompt you to enter your name.
2. Ask you to provide game options or to press enter to use the default game options(rock, paper, scissors).
3. Start the game and play a series of rounds until you decide to exit (`!exit`).

In each round of the game, it:
* Prompts the player for their choice.
* Determines and displays the computer's choice.
* Evaluates and announces the winner, or a draw.
* Updates the player's score (posting `!rating` will display the current score).
* Continues with the next round.

## How to Run the Game
To run the game, you need to have Java installed on your system. Compile and run the Main class, which is the game's entry point.

## Classes and Responsibilities
The game consists of several classes, each with its responsibilities:  
1. Main: The main entry-point for the application. Initializes the game and starts the game loop.
2. Game: This class keeps track of the game options and handles the game logic.
3. Player: Represents a player in the game. Keeps track of the player's choice and rating. The player may set their choice interactively from the console.
4. Bot: A subclass of Player. Represents the computer player, where the choice is randomly set from the game options.

## Custom Game Options
The game can take a string input with a sequence of game options, separated by commas. The options should be arranged clockwise discovered. Each option in the game wins against the previous half of options (exclude the option itself) and loses against the next half. For example, an option list like fire,rock,paper,scissors means rock loses to fire and wins against paper and scissors.

## Extending the Game
The game is designed in a modular fashion, which makes it relatively easy to add new features or game options. Simply add the new options to the list and modify the game logic in the Game and Player classes if needed.
