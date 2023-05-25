package com.example.thirdbesthack;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCSV {
        public static void main(String[] args) {
            String csvFile = "/Users/mauriziore/Desktop/EsportazioneGreciaAnomalie.csv";

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

                // Confronta le date nella colonna 8
                int columnToCheck = 20; // Indice 7 perché gli array sono zero-based
                String[] columnData = data[columnToCheck];
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (int i = 0; i < columnData.length - 1; i++) {
                    String[] dateStrings = columnData[i].replaceAll("[\\[\\]]", "").split(",");
                    for (int j = 0; j < dateStrings.length - 1; j++) {
                        String dateString1 = dateStrings[j].trim();
                        String dateString2 = dateStrings[j + 1].trim();

                        if (dateString1.isEmpty() || dateString2.isEmpty()) {
                            continue; // Salta le righe senza date valide
                        }

                        try {
                            Date date1 = dateFormat.parse(dateString1);
                            Date date2 = dateFormat.parse(dateString2);

                            long diffInMinutes = (date2.getTime() - date1.getTime()) / (1000 * 60);
                            if (diffInMinutes > 20) {
                                System.out.println("Errore: Intervallo di tempo maggiore a 20 minuti tra le date " + dateString1 + " e " + dateString2);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}


