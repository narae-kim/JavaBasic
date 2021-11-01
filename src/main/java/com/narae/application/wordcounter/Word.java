package com.narae.application.wordcounter;

public class Word {
    private int row;
    private int column;
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getWord() {
        return word;
    }

}
