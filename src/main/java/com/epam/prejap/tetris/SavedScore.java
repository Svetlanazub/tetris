package com.epam.prejap.tetris;

import java.util.List;
import java.util.Random;


/**
 * Keeps players scores in JSON file and print
 * 25 maximum score in order from the highest to lowest
 *
 * @author Svetlana_Zubkova
 */
public class SavedScore {
    private List<ScoreRecord> scoresList;
    private final static int MAX_AMOUNT_OF_ENTRIES = 25;
    private String path;

    public SavedScore(String filePath) {
        path = filePath;
        scoresList = SavedScoreIOUtility.readSavedScore(filePath);
    }

    /**
     * Writes a new score to the JSON file if it is in the range of 25 max scores.
     *
     * @param currentPoints score received in the current game
     */
    public void writeSavedScore(int currentPoints) {
        SavedScoreIOUtility.writeSavedScoreToFile(path, scoresList, currentPoints);
    }

    public void writeSavedScore(int currentPoints, String name) {
        SavedScoreIOUtility.writeSavedScoreToFile(path, scoresList, currentPoints, name);
    }

    /**
     * Prints 25 highest score with players' names
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < MAX_AMOUNT_OF_ENTRIES && i < scoresList.size(); i++) {
            output.append(scoresList.get(i).getName()).append(" ").append(scoresList.get(i).getValueOfScore()).append("\n");
        }
        return output.toString();
    }

    /**
     * Generates name of current player that consists of 3 characters
     *
     * @return String
     */
    public static String generateName() {
        final String alphabet = "ABCDEFGHIJKLMNOPRQSTUXYZ";
        int n = alphabet.length();
        Random r = new Random();
        StringBuilder input = new StringBuilder();
        for (int i = 0; i <= 2; i++) {
            input.append(alphabet.charAt(r.nextInt(n)));
        }
        return input.toString();
    }

    /**
     * Creates record with player's names and scores
     */
    static class ScoreRecord implements Comparable<ScoreRecord> {
        private final String name;
        private final Integer valueOfScore;

        ScoreRecord(String name, Integer valueOfScore) {
            this.name = name;
            this.valueOfScore = valueOfScore;
        }

        Integer getValueOfScore() {
            return valueOfScore;
        }

        private String getName() {
            return name;
        }

        @Override
        public int compareTo(ScoreRecord r) {
            return r.getValueOfScore().compareTo(this.getValueOfScore());
        }
    }
}
