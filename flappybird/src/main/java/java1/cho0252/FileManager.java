package java1.cho0252;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

/**
 * Manages loading and saving scores using IO streams.
 */
public class FileManager {
    private final String SAVE_FILE = "scores.txt";
    private final char SEPARATOR = ';';

    public FileManager() {
    }

    public void saveScore(String name, int score) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(SAVE_FILE, true);
            String scoreString = name + SEPARATOR + score + "\n";
            fileOutputStream.write(scoreString.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pair<String,Integer>> loadScores() {
        List<Pair<String,Integer>> scores = new ArrayList<Pair<String,Integer>>();
        try {
            FileInputStream fileInputStream = new FileInputStream(SAVE_FILE);
            String name = new String();
            String score = new String();
            boolean isScore = false;
            int c;
            while ((c = fileInputStream.read()) != -1) {
                if (c == '\n') {
                    isScore = false;
                    scores.add(new Pair<String,Integer>(name, Integer.parseInt(score)));
                    name = new String();
                    score = new String();
                } else if (c == SEPARATOR) {
                    isScore = true;
                } else if (isScore) {
                    score += (char) c;
                } else {
                    name += (char) c;
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }
        
        
}
