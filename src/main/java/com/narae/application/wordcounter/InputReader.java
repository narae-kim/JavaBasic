package com.narae.application.wordcounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String getInput() {
        try {
            return reader.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
