package com.example.thirdbesthack;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class TestCSV {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\andre\\Downloads\\EsportazioneGreciaAnomalie.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] header = reader.readNext(); // Leggi la prima riga come l'intestazione delle colonne

            int columnCount = header.length;
            String[][] data = new String[columnCount][];

            // Inizializza gli array per i dati di ogni colonna
            for (int i = 0; i < columnCount; i++) {
                data[i] = new String[0];
            }

            String[] line;
            while ((line = reader.readNext()) != null) {
                for (int i = 0; i < columnCount; i++) {
                    // Aggiungi il dato alla colonna corrispondente
                    String[] columnData = data[i];
                    String[] newColumnData = new String[columnData.length + 1];
                    System.arraycopy(columnData, 0, newColumnData, 0, columnData.length);
                    newColumnData[columnData.length] = line[i];
                    data[i] = newColumnData;
                }
            }

            // Stampa i valori salvati nelle colonne
            for (int i = 0; i < columnCount; i++) {
                System.out.println("Colonna: " + header[i]);
                String[] columnData = data[i];
                for (String cell : columnData) {
                    System.out.println("Dato: " + cell);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

