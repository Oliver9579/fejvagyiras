package hu.headortail.domain.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataFileHandler {

    private final String input;

    private int count;
    private double headPercent;
    private int doubleHeadCount;
    private int longestHeadCount;
    private int longestHeadPosition;

    public DataFileHandler(String input) {
        this.input = input;
        read();
    }

    private void read() {
        int headCount = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            int actualHeadCount = 0;
            while ((line = br.readLine()) != null) {
                count++;
                if ("F".equals(line)) {
                    headCount++;
                    actualHeadCount++;
                } else {
                    if(actualHeadCount == 2) {
                        doubleHeadCount++;
                    }
                    if (actualHeadCount > longestHeadCount) {
                        longestHeadCount = actualHeadCount;
                        longestHeadPosition = count - actualHeadCount;
                    }
                    actualHeadCount = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        headPercent = 100.0* headCount / count;
    }

    public int getCount() {
        return count;
    }

    public double getHeadPercent() {
        return headPercent;
    }

    public int getDoubleHeadCount() {
        return doubleHeadCount;
    }

    public int getLongestHeadCount() {
        return longestHeadCount;
    }

    public int getLongestHeadPosition() {
        return longestHeadPosition;
    }
}
