package com.epam.prejap.tetris;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * Keeps players scores in JSON file and print
 * 25 maximum score in order from the highest to lowest
 *
 * @author Svetlana_Zubkova
 */
class SavedScore {
    private final static int MAX_AMOUNT_OF_ENTRIES = 25;
    private final String path;

    // should not be final, you should be able to update it!
    // but if you are not going to update it at all, keep it final
    private final List<ScoreRecord> scoresList;

    SavedScore(String filePath) {
        path = filePath;
        scoresList = SavedScoreIOUtility.readSavedScore(filePath);
    }

    /**
     * Writes a new score to the JSON file if it is in the range of 25 max scores.
     *
     * @param currentPoints score received in the current game
     */
    void writeSavedScore(int currentPoints) {
        SavedScoreIOUtility.writeSavedScoreToFile(path, scoresList, currentPoints);
    }

    void writeSavedScore(int currentPoints, String name) {
        SavedScoreIOUtility.writeSavedScoreToFile(path, scoresList, currentPoints, name);
    }

    /**
     * Prints 25 highest score with players' names
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < MAX_AMOUNT_OF_ENTRIES && i < scoresList.size(); i++) {
            output.append(scoresList.get(i).name()).append(" ").append(scoresList.get(i).valueOfScore()).append("\n");
        }
        return output.toString();
    }

    /**
     * Creates record with player's names and scores
     */
    record ScoreRecord(String name, Integer valueOfScore) implements Comparable<ScoreRecord> {
        @Override
        public int compareTo(ScoreRecord r) {
            return r.valueOfScore().compareTo(this.valueOfScore());
        }
    }
}


class SavedScoreIOUtility {
    /**
     * Read score from JSON file
     *
     * @param name path to file
     * @return list of (strings) entries
     */
    public static List<SavedScore.ScoreRecord> readSavedScore(String name) {
        var results = new ArrayList<SavedScore.ScoreRecord>();
        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            results = new Gson().fromJson(reader, new TypeToken<List<SavedScore.ScoreRecord>>() {
            }.getType());
            results.sort(Comparator.comparing(SavedScore.ScoreRecord::valueOfScore));
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist!");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return results;
    }

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

    public static boolean writeSavedScoreToFile(String path, List<SavedScore.ScoreRecord> scoreList, int score, String name) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(path))) {
            Gson gson = new Gson();

            scoreList.add(new SavedScore.ScoreRecord(name, score));

            Collections.sort(scoreList);
            gson.toJson(scoreList, writer);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }


    public static boolean writeSavedScoreToFile(String path, List<SavedScore.ScoreRecord> scoreList, int score) {
        return writeSavedScoreToFile(path, scoreList, score, generateName());
    }

}