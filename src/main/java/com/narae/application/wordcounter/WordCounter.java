package com.narae.application.wordcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class WordCounter {
    public void run() {
        String filepath = "";
        InputReader inputReader = new InputReader();
        System.out.println("### If you want to exit, please type \"exit!\".");
        while (!filepath.equals("exit!")) {
            System.out.println("# Type the file path:");
            filepath = inputReader.getInput();
            File file = new File(filepath);
            if (filepath.equals("exit!")) {
                return;
            } else if (file.exists()) { // example: src/test/resources/test.txt
                break;
            } else {
                System.out.println("# Invalid file path. Try again.");
            }
        }
        BufferedReader testReader = InputHandler.readFile(filepath);
        String line;
        WordDataBase db = new WordDataBase();
        try {
            int ln = 0;
            while ((line = testReader.readLine()) != null) {
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
            String input = "";
            while (!input.equals("exit!")) {
                System.out.println("If you want to see the occurence of the word, type the word. If you want to see all word occurences, type \"all!\". Or type \"exit!\".");
                input = inputReader.getInput();
                if (input.equals("exit!")) {
                    return;
                } else if (input.equals("all!")) { // example: src/test/resources/test.txt
                    db.printAll();
                } else {
                    db.printWordLocation(input);
                }
            }
        } catch (IOException ioe) {
            System.out.println("Exception occurred during reading the lines.");
            ioe.printStackTrace();
        }
    }
}
