package com.example.thirdbesthack;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class CSVToArrayExample {
    public static void main(String[] args) {
        String csvFile = "/Users/mauriziore/Desktop/EsportazioneGreciaAnomalie.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            int rowCount = 0;
            int columnCount = 0;

            // Ottieni il numero di righe e colonne nel file CSV
            while ((line = reader.readNext()) != null) {
                rowCount++;
                if (line.length > columnCount) {
                    columnCount = line.length;
                }
            }

            // Torna all'inizio del file
            reader.close();
            CSVReader newReader = new CSVReader(new FileReader(csvFile));

            // Crea l'array bidimensionale
            String[][] data = new String[rowCount][columnCount];

            // Popola l'array con i dati del CSV
            int rowIndex = 0;
            while ((line = newReader.readNext()) != null) {
                for (int columnIndex = 0; columnIndex < line.length; columnIndex++) {
                    data[rowIndex][columnIndex] = line[columnIndex];
                }
                rowIndex++;
            }

            // Stampa l'array
            for (String[] row : data) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
