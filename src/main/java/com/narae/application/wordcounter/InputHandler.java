package com.narae.application.wordcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class InputHandler {

    public static BufferedReader readFile(String filepath) {
        try {
            File file = new File(filepath);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            return reader;
        } catch (FileNotFoundException fnfe) {
            System.out.println("Check your input - the file path is not valid.");
            fnfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
