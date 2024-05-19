package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);

        System.out.println("Provide game options or press enter: ");
        String[] options;
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            options = new String[]{"rock", "paper", "scissors"};
        } else {
            options = input.split(",");
        }

        Player human = new Player(name);
        Bot bot = new Bot("Computer");

        Game game = new Game(human, bot, options, scanner);
        game.start();

        scanner.close();
    }
}

class Game {
    private final String[] options;
    private final Player human;
    private final Bot computer;
    private final Scanner scanner;

    Game(Player human, Bot computer, String[] options, Scanner scanner) {
        this.human = human;
        this.computer = computer;
        this.options = options;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Okay, let's start");
        while (true) {
            human.setChoice(this.scanner, this.options);

            if (human.getChoice().equals("!exit")) {
                System.out.println("Bye!");
                break;
            }

            this.computer.setChoice(this.scanner, this.options);
            checkWinner(this.human.getChoice(), this.computer.getChoice(), this.human);
        }
    }

    private void checkWinner(String humanChoice, String computerChoice, Player humanPlayer) {
        int breakPoint = this.options.length / 2;
        int humanIndex = Arrays.asList(this.options).indexOf(humanChoice);
        int computerIndex = Arrays.asList(this.options).indexOf(computerChoice);

        if (humanChoice.equals("!rating")) {
            System.out.println("Your rating: " + humanPlayer.getRating());
        } else if (humanChoice.equals(computerChoice)) {
            System.out.println("There is a draw (" + humanChoice + ")");
            humanPlayer.incrementRating(50);
        } else {
            if (Math.abs(humanIndex - computerIndex) <= breakPoint && humanIndex < computerIndex
                    || Math.abs(humanIndex - computerIndex) > breakPoint && humanIndex > computerIndex) {
                System.out.println("Sorry, but the computer chose " + computerChoice);
            } else {
                System.out.println("Well done. The computer chose " + computerChoice + " and lost");
                humanPlayer.incrementRating(100);
            }
        }
    }
}

class Player {
    private final String name;
    private String choice;
    private int rating;

    Player(String name) {
        this.name = name;
        this.rating = 0;
        this.setInitialRating();
    }

    public String getName() {
        return this.name;
    }

    public void setChoice(Scanner scanner, String[] options) {
        String input = scanner.nextLine();
        String[] commands = new String[]{"!exit", "!rating"};

        if (Arrays.asList(options).contains(input) || Arrays.asList(commands).contains(input)) {
            this.choice = input;
        } else {
            this.choice = "";
            System.out.println("Invalid input");
        }
    }

    public void setChoice(String choice) {
        // Overloaded method used by the Bot subclass.
        this.choice = choice;
    }

    public String getChoice() {
        return this.choice;
    }

    public int getRating() {
        return this.rating;
    }

    public void incrementRating(int rating) {
        this.rating += rating;
    }

    private void setInitialRating() {
        File file = new File("./rating.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split(" ");
                if (parts[0].equals(this.getName())) {
                    this.rating = Integer.parseInt(parts[1]);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            // OK to ignore
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class Bot extends Player {

    Bot(String name) {
        super(name);
    }

    @Override
    public void setChoice(Scanner scanner, String[] options) {
        // We're not using scanner here, but the signatures must match for method override
        Random random = new Random();
        int index = random.nextInt(options.length);
        super.setChoice(options[index]);
    }
}
