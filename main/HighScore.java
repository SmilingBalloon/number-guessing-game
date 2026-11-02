package main;

public class HighScore {
    private String difficulty;
    private int attempts;
    private double timeTaken;

    public HighScore(String difficulty, int attempts, double timeTaken) {
        this.difficulty = difficulty;
        this.attempts = attempts;
        this.timeTaken = timeTaken;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(double timeTaken) {
        this.timeTaken = timeTaken;
    }
}
