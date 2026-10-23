package main;

import java.util.Random;

public class GuessNumber {
    private int my_number;

    public void newGame() {
        Random random = new Random();
        my_number = random.nextInt(1, 101);
    }

    public int getMyNumber() {
        return my_number;
    }

    public boolean isMyNumber(int number) {
        boolean equal = number == my_number;
        if (!equal) {
            String hint = my_number > number ? " greater than " : " less than ";
            System.out.println("Incorrect! The number is" + hint + number);
        }
        return equal;
    }
}