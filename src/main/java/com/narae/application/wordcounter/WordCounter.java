package com.narae.application.wordcounter;

import java.io.File;

public class WordCounter {
    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        wordCounter.run();
    }

    /*
     * Run WordCounter app if the given input is a valid file path.
     * Exit the program with the keyword "exit!".
     * Display the location of the keyword or all the keyword in the given file path.
     */
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
        InputHandler inputHandler = new InputHandler(filepath);
        WordDataBase db = inputHandler.inputToDatabase();
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
    }
}
