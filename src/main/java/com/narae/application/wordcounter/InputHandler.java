package com.narae.application.wordcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class InputHandler {
    private String filepath;

    public InputHandler(String filepath) {
        this.filepath = filepath;
    }

    /*
     * Return an object of WordDataBase that contains the processed words and their locations.
     */
    public WordDataBase inputToDatabase() {
        try {
            File file = new File(filepath);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            WordDataBase db = new WordDataBase();
            int ln = 0;
            while ((line = reader.readLine()) != null) {
                int column = 1;
                ln++;
                for (String token : line.split(" ")) {
                    String word = WordProcessor.process(token);
                    if (!word.equals("")) {
                        db.add(word, ln, column);
                    }
                    column += token.length();
                    column++;
                }
            }
            return db;
        } catch (FileNotFoundException fnfe) {
            System.out.println("Check your input - the file path is not valid.");
            fnfe.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }
}
