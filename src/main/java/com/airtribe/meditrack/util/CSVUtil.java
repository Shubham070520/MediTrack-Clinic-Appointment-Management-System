package com.airtribe.meditrack.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static void writeToCSV(String filePath, List<String> data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Data saved to " + filePath);

        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }

    public static List<String> readFromCSV(String filePath) {

        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                data.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return data;
    }
}