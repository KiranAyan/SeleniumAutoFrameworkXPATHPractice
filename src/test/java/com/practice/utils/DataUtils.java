package com.practice.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataUtils { // You were missing the class declaration!

    public static Object[][] readCsvData(String filePath) throws IOException {
        System.out.println("--- Starting Data Extraction ---");
        List<Object[]> data = new ArrayList<>();
        String line;
        int rowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        	br.readLine();
           /* String header = br.readLine();
            System.out.println("Header found: " + header);

            if (header == null) {
                System.out.println("WARNING: The file is completely empty!");
                return new Object[0][0];
            }*/

            while ((line = br.readLine()) != null) {
                // Ignore empty lines in the CSV
                if (line.trim().isEmpty()) continue;

                rowCount++;
                System.out.println("Processing Row " + rowCount + ": " + line);

                // Split by comma
                String[] fields = line.split(",");
                data.add(fields);
            }
        }

        System.out.println("Total Data Rows Loaded: " + rowCount);
        
        // Converts the List to a 2D Array for TestNG
        return data.toArray(new Object[0][0]);
    }
}