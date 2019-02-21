package com.apexsoftware;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String filePath = "C:\\Users\\Jack\\OneDrive\\FERC\\Shark Tank\\data8-9.csv";
        File file = new File(filePath);

        Scanner inputStream;
        Integer[][] data = new Integer[192][9];
        String delimiter = ",";
        int index = 0;

        try {
            inputStream = new Scanner(file);

            for (int i = 0; inputStream.hasNext(); i++) {
                String line = inputStream.nextLine();
                String[] dataAsString = line.split(delimiter);
                Integer[] dataAsInt = new Integer[dataAsString.length];

                for (int j = 0; j < dataAsString.length; j++) {
                    dataAsInt[j] = Integer.parseInt(dataAsString[j]);
                }
                data[i] = dataAsInt;
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        data = changeData(data);
        printArray(data);
    }

    private static Integer[][] changeData(Integer[][] data) {
        for (int i = 0; i < 192; i++) {
            for (int j = 0; j < 9; j++) {
                if (j <= 3) {
                    if (data[i][j] == 7) {
                        data[i][j] = getRandomNumberInRange(5, 7);
                    } else if (data[i][j] == 1) {
                        data[i][j] = getRandomNumberInRange(1, 3);
                    }

                    data[i][j] = getRandomNumberInRange((data[i][j] - 1), data[i][j] + 1);
                } else {
                    if (data[i][j] == 10) {
                        data[i][j] = getRandomNumberInRange(8, 10);
                    } else if (data[i][j] == 1) {
                        data[i][j] = getRandomNumberInRange(1, 3);
                    }
                }
            }
        }

        return data;
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private static void printArray(Integer[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (j == 8) {
                    System.out.print(data[i][j]);
                } else {
                    System.out.print(data[i][j] + ",");
                }
            }
            System.out.println();
        }
    }
}
