package com.example.thirdbesthack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestCompare {
        public static void main(String[] args) {
            String csvFile = "C:\\Users\\andre\\Downloads\\EsportazioneGreciaAnomalie.csv";
            String columnToCompare = "mmsi"; // Specifica il nome della colonna da confrontare

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line;
                String[] header = br.readLine().split(","); // Leggi la prima riga come l'intestazione delle colonne
                int columnIndex = -1;
                int lineNumber = 0;

                // Trova l'indice della colonna da confrontare
                for (int i = 0; i < header.length; i++) {
                    if (header[i].equals(columnToCompare)) {
                        columnIndex = i;
                        break;
                    }
                }

                if (columnIndex == -1) {
                    System.out.println("Colonna non trovata.");
                    return;
                }

                // Leggi le righe successive e confronta i valori della colonna specificata
                while ((line = br.readLine()) != null) {
                    lineNumber++;
                    String[] values = line.split(",");

                    if (values.length <= columnIndex) {
                        System.out.println("Valore mancante alla riga " + lineNumber + " nella colonna " + columnToCompare);
                        continue;
                    }

                    String columnValue = values[columnIndex];

                    // Esegui il confronto o l'elaborazione desiderata con il valore della colonna
                    // Esempio: Stampa il valore della colonna
                    System.out.println("Valore alla riga " + lineNumber + " nella colonna " + columnToCompare + ": " + columnValue);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
