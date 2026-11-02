package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GuessNumber {
    private Map<String, HighScore> highScores = new HashMap<>();
    private int my_number;
    private int step;
    private int range;
    private int hint_count;
    private String diff;
    Random random = new Random();

    public GuessNumber() {
        readHighScore();
    }

    public void newGame(Difficulty difficulty) {
        my_number = random.nextInt(1, 101); // [1, 101)
        hint_count = difficulty.getTotalHintCount();
        step = (difficulty.getChances() - 1) / hint_count;
        range = difficulty.getHintRange();
        diff = difficulty.getLabel();
    }

    public int getMyNumber() {
        return my_number;
    }

    private void showHint(int number) {
        if (hint_count < 0)
            return;
        int outer = range * hint_count;
        int inner = random.nextInt(1, 2) * hint_count;

        int lower = random.nextInt(my_number - outer, my_number - inner);
        if (number < my_number && number > lower) {
            lower = number;
        }

        int upper = random.nextInt(my_number + inner, my_number + outer);
        if (number > my_number && number < upper) {
            upper = number;
        }

        lower = lower < 1 ? 1 : lower;
        upper = upper > 100 ? 100 : upper;

        System.out.println("[HINT] The number is in range of [" + lower + ", " + upper + "]");
        hint_count--;
    }

    public boolean isMyNumber(int number, int attempts) {
        boolean equal = number == my_number;
        if (!equal) {
            String hint = my_number > number ? " greater than " : " less than ";
            System.out.println("Incorrect! The number is" + hint + number);

            if (attempts % step == 0) {
                showHint(number);
            }
        }

        return equal;
    }

    public void readHighScore() {
        try (BufferedReader in = new BufferedReader(new FileReader("highscore.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(",");
                String difficulty = parts[0].split(":")[1];
                int attempts = Integer.parseInt(parts[1].split(":")[1]);
                double timeTaken = Double.parseDouble(parts[2].split(":")[1]);
                highScores.put(difficulty, new HighScore(difficulty, attempts, timeTaken));
            }
        } catch (IOException exception) {
            System.err.println("[ERROR] Error while reading file: " + exception.getMessage());
        }
    }

    public void updateHighScore(int attempts, double timeTaken) {
        HighScore currentDiff = highScores.get(diff);
        if (currentDiff == null) {
            highScores.put(diff, new HighScore(diff, attempts, timeTaken));
        } else if (attempts <= currentDiff.getAttempts()) {
            currentDiff.setAttempts(attempts);
            currentDiff.setTimeTaken(timeTaken);
        }
    }

    public void saveHighScore() {
        StringBuilder builder = new StringBuilder();
        for (HighScore highScore : highScores.values()) {
            builder.append("difficulty:");
            builder.append(highScore.getDifficulty());    
            builder.append(",attempts:");
            builder.append(highScore.getAttempts());
            builder.append(",timeTaken:");
            builder.append(highScore.getTimeTaken());
            builder.append("\n");
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter("highscore.txt"))) {
            out.write(builder.toString());
        } catch (IOException exception) {
            System.err.println("[ERROR] Error while reading file: " + exception.getMessage());
        }
    }
}