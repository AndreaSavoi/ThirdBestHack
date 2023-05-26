package com.example.thirdbesthack;

import eccezioni.BucaTemporaleException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    protected VBox mess;
    @FXML
    protected TextField chat;
    @FXML
    protected ImageView enter;
    @FXML
    protected ImageView upload;

    private List<String> BucheTemporali = new ArrayList<>();
    private List<String> noFlag = new ArrayList<>();
    private List<String> zigZag = new ArrayList<>();

    private List<String> stessoPercorso = new ArrayList<>();


    @FXML
    public void send() throws BucaTemporaleException {
        String input = chat.getText();

        if (input.equalsIgnoreCase("ciao")){
            createResponsePane(input); // Pannello per la risposta dell'utente
            createResponsePane("Ciao! Sono il chatbot best!");
            chat.setText("");
        } else if (input.equalsIgnoreCase("puoi mostrarmi le anomalie?")) {
            createResponsePane(input);
            RilevaBucheTemporali buche = new RilevaBucheTemporali();
            buche.rilevaBuche();
            BucheTemporali = buche.getListNaviBucheTemporali();
            String bucheTot = String.join(",", BucheTemporali);
            NoFlag nF = new NoFlag();
            nF.NoFlag();
            noFlag = nF.getListNaviNoFlag();
            String nFT = String.join(",", noFlag);
            ZigZag zz = new ZigZag();
            zz.ZigZag();
            zigZag = zz.getListNaviZigZag();
            String zzT = String.join(",", zigZag);
            StessoPercorso sP = new StessoPercorso();
            stessoPercorso = sP.rilevaStessoPercorso();
            String sPT = String.join(",", stessoPercorso);
            createResponsePane("Le navi con buche temporali sono: "+bucheTot +  "\n");
            createResponsePane("Le navi senza flag sono: "+nFT+ "\n");
            createResponsePane("Le navi con un percorso a zig zag sono: "+zzT+ "\n");
            createResponsePane("Le navi con lo stesso percorso sono: "+sPT + "\n");
        } else {
            createResponsePane(input); // Pannello per la risposta dell'utente
            createResponsePane("Mi dispiace, non ho capito!");
        }
    }

    private void createResponsePane(String text) {
        Pane pane = new Pane();
        pane.setPrefSize(1239, 120);
        Label label = new Label();
        label.setLayoutY(pane.getPrefHeight() / 2 - 15);
        label.setFont(new Font("Century Gothic", 20));
        label.setStyle("-fx-font-weight: bold italic");
        label.setText(text);
        pane.getChildren().add(label);
        mess.getChildren().add(pane);
    }

    @FXML
    private void up(MouseEvent event) {
        upload.setOnMouseClicked(mevent -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);

            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                // Esegui le azioni desiderate con il file selezionato
                String filePath = selectedFile.getAbsolutePath();
                // Esempio: Leggi il file CSV
                //ora carica il file csv in Singleton
                DummyCsv.setCsv(filePath);
                createResponsePane("Il file csv è stato caricato con successo!");
            }
        });
    }
}
