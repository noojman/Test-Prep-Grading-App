package com.noojman.testprepgradingapp;

public class RowData {
    public String title;
    public String publisher;
    public int testNumber;
    public int numProblems;
    public int timerSeconds;

    public RowData(String title, String publisher, int testNumber, int numProblems, int timerSeconds) {
        this.title = title;
        this.publisher = publisher;
        this.testNumber = testNumber;
        this.numProblems = numProblems;
        this.timerSeconds = timerSeconds;
    }
}
