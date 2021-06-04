package com.epam.prejap.tetris;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Svetlana_Zubkova
 */
public class SavedScore {
    ArrayList<Record> scoresList;
    final static int n = 25;

    public SavedScore() throws IOException {
        scoresList = new ArrayList<>();
        try {
            FileReader reader = new FileReader("data/saved_result.txt");
            Scanner input = new Scanner(reader);
            while (input.hasNextLine()) {
                String[] record = input.nextLine().split(" ");
                String name = record[0];
                int valueOfScore = Integer.parseInt(record[1]);
                Record record1 = new Record(name, valueOfScore);
                scoresList.add(record1);
            }
            reader.close();
            Collections.sort(scoresList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    void rewriteSavedScore(int currentPoints, String nameNewScore) throws IOException {
        FileWriter writer = new FileWriter("data/saved_result.txt");
        scoresList.add(new Record(nameNewScore, currentPoints));
        writer.write(this.toString());
        writer.close();
    }

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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < n && i < scoresList.size(); i++) {
            output.append(scoresList.get(i).getName()).append(" ").append(scoresList.get(i).getValueOfScore()).append("\n");
        }
        return output.toString();
    }

    class Record implements Comparable<Record> {
        String name;
        Integer valueOfScore;

        public Record(String name, Integer valueOfScore) {
            this.name = name;
            this.valueOfScore = valueOfScore;
        }

        public Integer getValueOfScore() {
            return valueOfScore;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Record r) {
            return r.getValueOfScore().compareTo(this.getValueOfScore());
        }
    }
}
