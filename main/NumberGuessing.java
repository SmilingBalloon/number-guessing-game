package main;

import java.util.Scanner;

public class NumberGuessing {

    static int readInt(String message, Scanner in) {
        System.out.print(message);
        while (!in.hasNextInt()) {
            System.err.println("[ERROR] Invalid input!\n");
            in.next(); // skip input
            System.out.print(message);
        }
        return in.nextInt();
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        GuessNumber game = new GuessNumber();
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("Please select the difficulty level:");
                System.out.println(" 1. Easy\n 2. Medium\n 3. Hard");
                int diff = readInt("Enter your choice: ", in);
                in.nextLine(); // skip '\n'

                Difficulty difficulty;
                try {
                    difficulty = Difficulty.getDifficulty(diff);
                } catch (IllegalArgumentException exception) {
                    System.err.println(exception.getMessage());
                    continue;
                }
                game.newGame(difficulty);

                System.out.println("You have " + difficulty.getChances() + " chances to guess the correct number!\n");
                long start = System.currentTimeMillis();
                for (int attempts = 1; attempts <= difficulty.getChances(); attempts++) {
                    int number = readInt("Enter your guess (" + attempts + "/" + difficulty.getChances() + "): ", in);
                    in.nextLine(); // skip '\n'

                    if (game.isMyNumber(number, attempts)) {
                        long end = System.currentTimeMillis();
                        double timeTaken = (end - start) / 1000.0;
                        System.out.println(
                                "Congratulations! You guessed the correct number in " + attempts + " attempts");
                        System.out.println("Time taken: " + timeTaken + " seconds\n");
                        game.updateHighScore(attempts, timeTaken);
                        break;
                    }

                    if (attempts == difficulty.getChances()) {
                        System.out.println("You have run out of chances, the number is: " + game.getMyNumber());
                    }
                }

                String option = null;
                do {
                    System.out.print("Do you want to try again (Y/N)? ");
                    option = in.nextLine().trim();
                } while (!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N"));

                if (option.equalsIgnoreCase("N")) {
                    game.saveHighScore();
                    break;
                }
            }
        }
    }
}
