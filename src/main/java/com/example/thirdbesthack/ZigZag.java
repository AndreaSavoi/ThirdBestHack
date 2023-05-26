package com.example.thirdbesthack;

import java.util.ArrayList;
import java.util.List;

public class ZigZag {

    private List<String> naviZigZag = new ArrayList<String>();
    String headingString;
    String headingStringWithoutBrackets;
    private final int limit = 5;
    String[] headingArray;
    String latitudeString;
    String latitudeStringWithoutBrackets;
    String longitudeString;
    String longitudeStringWithoutBrackets;
    String[] latitudeArray;
    String[] longitudeArray;

    public void ZigZag(){
        for(int i = 1; i < DummyCsv.rowCount; i++) {
            /*headingString = DummyCsv.data[i][19];
            // Rimuovo le parentesi quadre iniziali e finali
            headingStringWithoutBrackets = headingString.substring(1, headingString.length() - 1);
            // Dividi le date in base alla virgola e le metto in un array
            headingArray = headingStringWithoutBrackets.split(",");
            int[] headingIntArray = new int[headingArray.length];
            for (int j = 0; j < headingArray.length; j++) {
                headingIntArray[j] = Integer.parseInt(headingArray[j]);
            }
            int count = 0;
            int k = 2;
            while(count < limit && k < headingIntArray.length) {
                if(headingIntArray[k-1] - headingIntArray[k-2] > 0) {
                    if(headingIntArray[k] - headingIntArray[k-1] < 0) {
                        //si forma una V al contrario
                        count++;
                    }
                } else if(headingIntArray[k-1] - headingIntArray[k-2] < 0) {
                    if(headingIntArray[k] - headingIntArray[k-1] > 0) {
                        //si forma una V
                        count++;
                    }
                }
                k++;
            }
            if(count >= limit) {
                naviZigZag.add(DummyCsv.data[i][0]);
            }*/

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
            // Calcola la differenza di latitudine e longitudine tra i punti consecutivi
            double[] latDiff = new double[latitudeDoubleArray.length - 1];
            double[] lonDiff = new double[longitudeDoubleArray.length - 1];
            for (int k = 0; k < latitudeDoubleArray.length - 1; k++) {
                latDiff[k] = latitudeDoubleArray[k + 1] - latitudeDoubleArray[k];
                lonDiff[k] = longitudeDoubleArray[k + 1] - longitudeDoubleArray[k];
            }

            // Conta il numero di cambi di direzione
            int directionChanges = 0;
            int zigzagCount = 0;
            for (int n = 0; n < latDiff.length - 1; n++) {
                if ((latDiff[n] > 0 && latDiff[n + 1] < 0) || (latDiff[n] < 0 && latDiff[n + 1] > 0)
                        || (lonDiff[n] > 0 && lonDiff[n + 1] < 0) || (lonDiff[n] < 0 && lonDiff[n + 1] > 0)) {
                    directionChanges++;

                    if (directionChanges >= 4) {
                        naviZigZag.add(DummyCsv.data[i][0]);
                        break;
                    }
                }
            }
        }
    }

    public List<String> getListNaviZigZag() {
        for (String navi : naviZigZag) {
            System.out.println(navi);
        }
        return naviZigZag;
    }

}
