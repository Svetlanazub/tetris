package com.epam.prejap.tetris;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Read and write score from/to JSON file
 * @author Svetlana_Zubkova
 */
class SavedScoreIOUtility {
    /**
     * Read score from JSON file
     *
     * @param name path to file
     * @return list of ScoreRecords entries
     */
   static List<SavedScore.ScoreRecord> readSavedScore(String name) {
        var results = new ArrayList<SavedScore.ScoreRecord>();

        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            results.addAll(new Gson().fromJson(reader, new TypeToken<List<SavedScore.ScoreRecord>>() {
            }.getType()));
            Collections.sort(results);

        } catch (FileNotFoundException e) {
            System.err.println("You are first player!");
            return results;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return results;
    }

    /**
     * Write score to JSON file
     *
     * @param path path to file
     * @param score score of current game
     * @param name name of player from 3 chars
     * @param scoreList list of previous games' results
     */
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

    /**
     * Write score to JSON file
     *
     * @param path path to file
     * @param score score of current game
     * @param scoreList list of previous games' results
     */
    public static boolean writeSavedScoreToFile(String path, List<SavedScore.ScoreRecord> scoreList, int score) {
        return writeSavedScoreToFile(path, scoreList, score, SavedScore.generateName());
    }
}
