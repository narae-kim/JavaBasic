package com.narae.application.wordcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDataBase {
    private Map<String, List<Integer[]>> wordMap = new HashMap<String, List<Integer[]>>();
    private List<Integer[]> locations;
    private Integer[] location;

    public void add(String word, Integer ln, Integer column) {
        if (wordMap.containsKey(word)) {
            locations = wordMap.get(word);
        } else {
            locations = new ArrayList<Integer[]>();
        }
        location = new Integer[2];
        location[0] = ln;
        location[1] = column;
        locations.add(location);
        wordMap.put(word, locations);
    }

    public void printAll() {
        for (Map.Entry<String, List<Integer[]>> entry : wordMap.entrySet()) {
            String key = entry.getKey();
            System.out.println("The word location of \"" + key + "\" is:");
            List<Integer[]> value = entry.getValue();
            for (Integer[] location : value) {
                System.out.println("\tln: " + location[0] + "\t\t\tcolumn: " + location[1]);
            }
        }
    }

    public void printWordLocation(String word) {
        word = WordProcessor.process(word);
        if (wordMap.containsKey(word)) {
            List<Integer[]> wordLocation = wordMap.get(word);
            System.out.println("The word \"" + word + "\" occurs in " + wordLocation.size() + " places:");
            for (Integer[] location : wordLocation) {
                System.out.println("\tln: " + location[0] + "\t\t\tcolumn: " + location[1]);
            }
        } else {
            System.out.println("The word \"" + word + "\" does not exist in the file.");
        }
    }
}
