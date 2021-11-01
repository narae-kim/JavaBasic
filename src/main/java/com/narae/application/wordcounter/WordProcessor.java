package com.narae.application.wordcounter;

public class WordProcessor {
    private static String removeSpecialCharacter(String word) {
        return word.replaceAll("[^a-zA-Z0-9]", "");
    }

    private static String lowercase(String word) {
        return word.toLowerCase();
    }

    /*
     * Word utility function that cleans the word by removing special characters and lowering the case.
     */
    public static String process(String word) {
        word = removeSpecialCharacter(word);
        word = lowercase(word);
        return word;
    }
}
