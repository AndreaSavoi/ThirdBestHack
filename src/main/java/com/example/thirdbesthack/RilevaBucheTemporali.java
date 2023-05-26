package com.example.thirdbesthack;

import com.opencsv.CSVReader;
import eccezioni.BucaTemporaleException;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RilevaBucheTemporali {
    private final int tug=30;
    private final int cargo=45;
    private final int other=16;
    private final int passenger=20;
    private  List <String> naviConBucaTemporale = new ArrayList<String>();
    private  int rowCount = 0;
    private  int columnCount = 0;
    private String[] line;
    private SimpleDateFormat dateFormat;
    private long diffInMinutes;
    int numberOfDate;
    //variabile che vede il tipo di nave
    private String tipoDiNave;
    //creo un array che conterrà le date pure
    private String[] dateArray;
    //creo una stringa che conterrà le date senza perentesi quadre inziali e finali
    private String datesStringWithoutBrackets;
    //creo una stringa dove ci sono una serie di date
    private String datesString;
    //variabile settata a true se viene trovato un errore
    private Boolean verificaErrore=false;
    private String csvFile;
    //creo 2 variabili di tipo data in cui metterò nel ciclo for le 2 date e le potrò confrontare
    private Date date1;
    private Date date2;

    public void rilevaBuche(String path) throws BucaTemporaleException {
        //variabili che contengono i tempi
        //csvFile=path;
        //csvFile = "/Users/mauriziore/Desktop/EsportazioneGreciaAnomalie.csv";

        //try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
        //    // Ottieni il numero di righe e colonne nel file CSV
        //    while ((line = reader.readNext()) != null) {
        //        rowCount++;
        //        if (line.length > columnCount) {
        //            columnCount = line.length;
        //        }
        //    }
        //
        //    // Torna all'inizio del file
        //    reader.close();
        //    CSVReader newReader = new CSVReader(new FileReader(csvFile));
        //
        //    // Crea l'array bidimensionale
        //    data = new String[rowCount][columnCount];
        //
        //    // Popola l'array con i dati del CSV
        //    int rowIndex = 0;
        //    while ((line = newReader.readNext()) != null) {
        //        for (int columnIndex = 0; columnIndex < line.length; columnIndex++) {
        //            data[rowIndex][columnIndex] = line[columnIndex];
        //        }
        //        rowIndex++;
        //    }
            //creo un array che conterrà le navi che hanno avuto un buco temporale
            //ora devo scansionare ogni riga con la colonna che contiene le date, devo sapere quante righe ho in tutto
            //e questa informazione ce l'ho nella variabile row count
            for(int i=1; i<rowCount;i++) {
                //vedo il tipo di nave che ho ora
                tipoDiNave=DummyCsv.data[i][3];
                // ora controlliamo la data, dobbiamo verificare che non sia superiore a tot minuti
                //assegno alla stringa le date
                datesString = DummyCsv.data[i][20];
                // Rimuovo le parentesi quadre iniziali e finali
                datesStringWithoutBrackets = datesString.substring(1, datesString.length() - 1);
                // Dividi le date in base alla virgola e le metto in un array
                dateArray = datesStringWithoutBrackets.split(",");
                // Rimuovi gli spazi bianchi iniziali e finali dalle date
                for (int j = 0; j < dateArray.length; j++) {
                    dateArray[j] = dateArray[j].trim();
                }
                //fatto questo dobbiamo confrontare le date
                //1 calcolo la dimensione dell'array
                numberOfDate = dateArray.length;
                //inoltre il formato della data lo conosciamo, implemento una variabile che mi aiuta a controllarlo
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //confronto le date tra loro e vedo se c'e' una distanza in termini temporali maggiore a quella consentita
                for (int k = 0; k < dateArray.length - 2; k++) {
                    //converto le due date consecutive nel formato Date
                    try {
                        date1 = dateFormat.parse(dateArray[k + 1]);
                        date2 = dateFormat.parse(dateArray[k]);
                        //System.out.println(date1);
                        //System.out.println(date2);
                        //verifico se la differenza tra le 2 date e' maggiore di un certo limite di tempo( a titolo di esempio prendo 10 minuti)
                        //creo quindi prima una variabile che contiene la differenza delle 2 date in minuti
                        diffInMinutes = (date1.getTime() - date2.getTime()) / (1000 * 60);
                        //una volta fatto ciò verifico che la differenza trovata non sia maggiore del tempo stabilito per quel tipo di nave
                        //prendo il tempo
                        int minTime;
                        if(tipoDiNave.equals("Cargo")){
                            minTime=cargo;
                        } else if (tipoDiNave.equals("Tug")) {
                            minTime=tug;
                        } else if (tipoDiNave.equals("Passenger")) {
                            minTime=passenger;
                        } else{
                            minTime=other;
                        }
                        if (diffInMinutes < minTime) {
                            //sono al sicuro passo al prossimo
                            continue;
                        } else {
                            //in caso contrario significa che una nave non ha lanciato il segnale entro il limite di tempo consenstito
                            //c'e' quindi un buco temporale, devo ricordare la nave qual'e', e settare la variabile booleana di sorpa
                            //a true in modo che una volta che il ciclo for finisce viene lanciata l'eccezione
                            verificaErrore = true;
                            naviConBucaTemporale.add(DummyCsv.data[i][0]);
                            //devo uscire dal ciclo for tanto ormai il buco temporale e' stato trovato
                            k = dateArray.length;
                            //throw new BucaTemporaleException("attenzione, buco temporale rilevato!");
                        }
                    }catch(ParseException e ){
                        //visto che un buco temporale puo verificarsi anche quando una data non viene convertita nel modod giusto
                        //oppure quando è  presente uno spazio bianca ( mancanza di dati), gestisco l'eccezione e inserisco la nave
                        // nell'array delle navi con buca temporale
                        verificaErrore = true;
                        naviConBucaTemporale.add(DummyCsv.data[i][0]);
                        //devo uscire dal ciclo for tanto ormai il buco temporale e' stato trovato
                        k = dateArray.length;
                    }

                }
                //arrivato qui ho scansionato tutto il file, se la variabile booleana vale true devo restituire tutte le navi
                //che sono state inserite nell'array delle navi
                //questo verra fatto con un metodo get dato che l'array dovrà essere private, pero per ora le scrivo
                }
            if(verificaErrore) {
                //System.out.println(naviConBucaTemporale);
                for (String navi : naviConBucaTemporale) {
                 System.out.println(navi);
                }
            }
        //}catch (IOException e) {
          //  e.getMessage();
        //}
    }
    public List<String> getListNaviBucheTemporali(){
        return naviConBucaTemporale;
    }
}
