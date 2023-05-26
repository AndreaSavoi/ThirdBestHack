package com.example.thirdbesthack;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DummyCsv {
    //in questa classe ci sarà il csv che verrà preso una sola volta per motivi di efficenza
    public static String[][] data;
    private static String[] line;
    public static int rowCount;
    public static int columnCount;
    private static CSVReader reader = null;

    public static void setCsv(String filePath) {
        //riceve il file path , ora lo deve caricare dentro la matrice che sraà static a cui tutte
        //le classi possono attingere
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Ottieni il numero di righe e colonne nel file CSV
            while ((line = reader.readNext()) != null) {
                rowCount++;
                if (line.length > columnCount) {
                    columnCount = line.length;
                }
            }

            // Torna all'inizio del file
            reader.close();
            CSVReader newReader = new CSVReader(new FileReader(filePath));

            // Crea l'array bidimensionale
            data = new String[rowCount][columnCount];

            // Popola l'array con i dati del CSV
            int rowIndex = 0;
            while ((line = newReader.readNext()) != null) {
                for (int columnIndex = 0; columnIndex < line.length; columnIndex++) {
                    data[rowIndex][columnIndex] = line[columnIndex];
                }
                rowIndex++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
