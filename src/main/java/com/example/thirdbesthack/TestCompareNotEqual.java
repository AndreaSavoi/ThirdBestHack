package com.example.thirdbesthack;

import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;

public class TestCompareNotEqual {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\andre\\Downloads\\EsportazioneGreciaAnomalie.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] header = reader.readNext(); // Leggi la prima riga come l'intestazione delle colonne

            int columnCount = header.length;

            String[] line;
            int rowNumber = 0;
            while ((line = reader.readNext()) != null) {
                rowNumber++;
                for (int i = 0; i < columnCount; i++) {
                    for (int j = 0; j < columnCount; j++) {
                        if (i != j) { // Ignora la stessa colonna
                            String columnI = line[i];
                            String columnJ = line[j];
                            if (columnI.equals(columnJ)) {
                                System.out.println("Duplicato alla riga " + rowNumber +
                                        ", colonna " + header[i] + " e colonna " + header[j] +
                                        ", valore: " + columnI);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
