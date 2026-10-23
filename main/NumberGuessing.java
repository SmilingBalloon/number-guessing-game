package main;

import java.util.Scanner;

public class NumberGuessing {
    static final int EASY = 10;
    static final int MEDIUM = 5;
    static final int HARD = 3;
    public static void main(String[] args) {
        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        GuessNumber newGame = new GuessNumber();
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                int diff;
                newGame.newGame();
                System.out.println("Please select the difficulty level:");
                System.out.println("1. Easy\n2. Medium\n3. Hard");
                System.out.print("Enter your choice: ");
                if (!in.hasNextInt()) {
                    in.next(); // skip input
                    System.err.println("[ERROR] Invalid input!\n");
                    continue;
                }
                diff = in.nextInt();

                int chances = switch (diff) {
                    case 1 -> EASY;
                    case 2 -> MEDIUM;
                    case 3 -> HARD;
                    default -> -1;
                };
                if (chances == -1) {
                    System.err.println("[ERROR] Invalid input!\n");
                    continue;
                }
                System.out.println("You have " + chances + " chances to guess the correct number!\n");

                for (int attempts = 1; attempts <= chances; attempts++) {
                    int number;
                    System.out.print("Enter your guess (" + attempts + "/" + chances + "): ");
                    while (!in.hasNextInt()) {
                        in.next();
                        System.err.println("[ERROR] Enter an integer!\n");
                        System.out.print("Enter your guess (" + attempts + "/" + chances + "): ");
                    }
                    number = in.nextInt(); // input = integer\n
                    in.nextLine(); // skip "\n"

                    if (newGame.isMyNumber(number)) {
                        System.out.println(
                                "Congratulations! You guessed the correct number in " + attempts + " attempts\n");
                        break;
                    }
                    if (attempts == chances)
                        System.out.println("You have run out of chances, the number is: " + newGame.getMyNumber());
                }

                String option = null;
                do {
                    System.out.print("Do you want to try again (Y/N)? ");
                    option = in.nextLine().trim();
                } while (!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N"));

                if (option.equalsIgnoreCase("N"))
                    break;
            }
        }
    }
}
