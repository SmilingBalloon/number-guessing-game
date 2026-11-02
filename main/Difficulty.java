package main;

public enum Difficulty {
    EASY(15, 4, 3, "EASY"),
    MEDIUM(10, 3, 4, "MEDIUM"),
    HARD(5, 2, 5, "HARD");

    private int chances;
    private int total_hint_count;
    private int hint_range;
    private String label;
    Difficulty(int chances, int total_hint_count, int hint_range, String label) {
        this.chances = chances;
        this.total_hint_count = total_hint_count;
        this.hint_range = hint_range;
        this.label = label;
    }

    public int getChances() {
        return chances;
    }

    public int getTotalHintCount() {
        return total_hint_count;
    }

    public int getHintRange() {
        return hint_range;
    }

    public String getLabel() {
        return label;
    }

    public static Difficulty getDifficulty(int diff) {
        return switch (diff) {
            case 1 -> EASY;
            case 2 -> MEDIUM;
            case 3 -> HARD;
            default -> throw new IllegalArgumentException("[ERROR] Invalid difficulty level");
        };
    }
}
