package java1.cho0252;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

/**
 * Manages loading and saving scores using IO streams.
 */
public class FileManager {
    private static final String SAVE_FILE = "scores.txt";
    private static final char SEPARATOR = ';';

    public static void saveScore(String name, int score) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(SAVE_FILE, true));
            String scoreString = name + SEPARATOR + score;
            writer.println(scoreString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pair<String, Integer>> loadScores() {
        List<Pair<String, Integer>> scores = new ArrayList<Pair<String, Integer>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(String.valueOf(SEPARATOR));
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                scores.add(new Pair<String, Integer>(name, score));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

}
