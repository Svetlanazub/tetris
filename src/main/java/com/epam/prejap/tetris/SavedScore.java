package com.epam.prejap.tetris;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Keeps players scores in JSON file and print
 * 25 maximum score in order from the highest to lowest
 *
 * @author Svetlana_Zubkova
 */
class SavedScore {
    private List<Record> scoresList;
    private final static int n = 25;
    private String path;

    SavedScore(String filePath) {
        path = filePath;
        scoresList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            scoresList = new Gson().fromJson(reader, new TypeToken<List<Record>>() {
            }.getType());
            Collections.sort(scoresList);
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist!");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Writes a new score to the JSON file if it is in the range of 25 max scores.
     *
     * @param currentPoints score received in the current game
     * @param nameNewScore  name of current player
     */
    void rewriteSavedScore(int currentPoints, String nameNewScore) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(path))) {
            Gson gson = new Gson();
            scoresList.add(new Record(nameNewScore, currentPoints));
            Collections.sort(scoresList);
            gson.toJson(scoresList, writer);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Generates name of current player that consists of 3 characters
     *
     * @return String
     */
    String nameForNewScore() {
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
     * Prints 25 highest score with players' names
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < n && i < scoresList.size(); i++) {
            output.append(scoresList.get(i).getName()).append(" ").append(scoresList.get(i).getValueOfScore()).append("\n");
        }
        return output.toString();
    }

    /**
     * Creates record with player's names and scores
     */
    private static class Record implements Comparable<Record> {
        private final String name;
        private final Integer valueOfScore;

        private Record(String name, Integer valueOfScore) {
            this.name = name;
            this.valueOfScore = valueOfScore;
        }

        private Integer getValueOfScore() {
            return valueOfScore;
        }

        private String getName() {
            return name;
        }

        @Override
        public int compareTo(Record r) {
            return r.getValueOfScore().compareTo(this.getValueOfScore());
        }
    }
}
