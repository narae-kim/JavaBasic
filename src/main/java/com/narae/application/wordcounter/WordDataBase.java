package com.narae.application.wordcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDataBase {
    private Map<String, List<Word>> wordMap = new HashMap<String, List<Word>>();
    private List<Word> wordList;
    private Word word;

    /*
     * Add the new line number and the new column number to the keyword.
     */
    public void add(String keyword, Integer ln, Integer column) {
        if (wordMap.containsKey(keyword)) {
            wordList = wordMap.get(keyword);
        } else {
            wordList = new ArrayList<Word>();
        }
        word = new Word(keyword);
        word.setRow(ln);
        word.setColumn(column);
        wordList.add(word);
        wordMap.put(keyword, wordList);
    }

    /*
     * Print all keywords and locations of the input file.
     */
    public void printAll() {
        for (Map.Entry<String, List<Word>> entry : wordMap.entrySet()) {
            String key = entry.getKey();
            System.out.println("The word location of \"" + key + "\" is:");
            List<Word> value = entry.getValue();
            for (Word word : value) {
                System.out.println("\tln: " + word.getRow() + "\t\t\tcolumn: " + word.getColumn());
            }
        }
    }

    /*
     * Print all locations of the keyword in the input file.
     */
    public void printWordLocation(String keyword) {
        keyword = WordProcessor.process(keyword);
        if (wordMap.containsKey(keyword)) {
            List<Word> wordLocation = wordMap.get(keyword);
            System.out.println("The word \"" + keyword + "\" occurs in " + wordLocation.size() + " places:");
            for (Word word : wordLocation) {
                System.out.println("\tln: " + word.getRow() + "\t\t\tcolumn: " + word.getColumn());
            }
        } else {
            System.out.println("The word \"" + keyword + "\" does not exist in the file.");
        }
    }
}
