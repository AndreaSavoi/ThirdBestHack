package com.example.thirdbesthack;


import java.util.ArrayList;
import java.util.List;

public class StessoPercorso {

    List<String> naviStessoPercorso = new ArrayList<>();
    String latitudeString;
    String latitudeStringWithoutBrackets;
    String longitudeString;
    String longitudeStringWithoutBrackets;
    String[] latitudeArray;
    String[] longitudeArray;

    String latitudeStringS;
    String latitudeStringWithoutBracketsS;
    String longitudeStringS;
    String longitudeStringWithoutBracketsS;
    String[] latitudeArrayS;
    String[] longitudeArrayS;

    public List<String> rilevaStessoPercorso() {
        for (int i = 1; i < DummyCsv.rowCount; i++) {
            latitudeString = DummyCsv.data[i][13];
            latitudeStringWithoutBrackets = latitudeString.substring(1, latitudeString.length() - 1);
            // Dividi le date in base alla virgola e le metto in un array
            latitudeArray = latitudeStringWithoutBrackets.split(",");
            double[] latitudeDoubleArray = new double[latitudeArray.length];
            for (int j = 0; j < latitudeArray.length; j++) {
                latitudeDoubleArray[j] = Double.parseDouble(latitudeArray[j]);
            }
            longitudeString = DummyCsv.data[i][14];
            longitudeStringWithoutBrackets = longitudeString.substring(1, longitudeString.length() - 1);
            // Dividi le date in base alla virgola e le metto in un array
            longitudeArray = longitudeStringWithoutBrackets.split(",");
            double[] longitudeDoubleArray = new double[longitudeArray.length];
            for (int j = 0; j < longitudeArray.length; j++) {
                longitudeDoubleArray[j] = Double.parseDouble(longitudeArray[j]);
            }
            for (int m = i+1; m < DummyCsv.rowCount; m++) {
                latitudeStringS = DummyCsv.data[m][13];
                latitudeStringWithoutBracketsS = latitudeStringS.substring(1, latitudeStringS.length() - 1);
                // Dividi le date in base alla virgola e le metto in un array
                latitudeArrayS = latitudeStringWithoutBracketsS.split(",");
                double[] latitudeDoubleArrayS = new double[latitudeArrayS.length];
                for (int j = 0; j < latitudeArrayS.length; j++) {
                    latitudeDoubleArrayS[j] = Double.parseDouble(latitudeArrayS[j]);
                }
                longitudeStringS = DummyCsv.data[m][14];
                longitudeStringWithoutBracketsS = longitudeStringS.substring(1, longitudeStringS.length() - 1);
                // Dividi le date in base alla virgola e le metto in un array
                longitudeArrayS = longitudeStringWithoutBracketsS.split(",");
                double[] longitudeDoubleArrayS = new double[longitudeArrayS.length];
                for (int j = 0; j < longitudeArrayS.length; j++) {
                    longitudeDoubleArrayS[j] = Double.parseDouble(longitudeArrayS[j]);
                }

                if (longitudeDoubleArrayS.length == longitudeDoubleArray.length) {
                    if(longitudeDoubleArrayS[0] == longitudeDoubleArray[0] && latitudeDoubleArrayS[0] == latitudeDoubleArray[0]) {
                        naviStessoPercorso.add(DummyCsv.data[i][0] + " e " + DummyCsv.data[m][0]);
                    }
                }
            }
        }
        return naviStessoPercorso;
    }
}
