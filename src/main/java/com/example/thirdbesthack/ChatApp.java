package com.example.thirdbesthack;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ChatApp extends Application {

    private VBox messageContainer;
    private TextField inputField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        messageContainer = new VBox();
        messageContainer.setAlignment(Pos.TOP_LEFT);
        messageContainer.setSpacing(10);
        messageContainer.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(messageContainer);
        scrollPane.setFitToWidth(true);

        inputField = new TextField();
        inputField.setPrefWidth(300);

        Button sendButton = new Button("Invia");
        sendButton.setOnAction(e -> sendMessage());

        //HBox inputContainer = new HBox(inputField, sendButton);
        //inputContainer.setSpacing(10);
        //inputContainer.setPadding(new Insets(10));
        VBox inputContainer = new VBox(inputField, sendButton);
        inputContainer.setAlignment(Pos.BOTTOM_CENTER);
        inputContainer.setSpacing(10);VBox root = new VBox(scrollPane, inputContainer);
        inputContainer.setPadding(new Insets(10));root.setSpacing(10);
        addMessage("Ciao", false);
        addMessage("Ciao! Come posso aiutarti?", true);
        addMessage("Sto cercando informazioni su un prodotto", false);
        addMessage("Posso aiutarti a trovare le informazioni di cui hai bisogno", true);

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat App");
        primaryStage.show();
    }

    private void addMessage(String text, boolean isUserMessage) {
        Label label = new Label(text);
        label.setTextAlignment(isUserMessage ? TextAlignment.RIGHT : TextAlignment.LEFT);

        label.setMaxWidth(Double.MAX_VALUE);
        label.setWrapText(true);
        label.setPadding(new Insets(10));
        label.setStyle("-fx-background-color: " + (isUserMessage ? "#ffffff" : "#dcf8c6") + "; " +
                "-fx-background-radius: 10; " +
                "-fx-border-radius: 10; " +
                "-fx-border-width: 1; " +
                "-fx-border-color: #e6e6e6;");

        VBox.setMargin(label, new Insets(5));
        messageContainer.getChildren().add(label);

        if (isUserMessage) {
            VBox.setMargin(label, new Insets(5, 20, 5, 5)); // Imposta i margini per il messaggio dell'utente
        } else {
            VBox.setMargin(label, new Insets(5, 5, 5, 20)); // Imposta i margini per il messaggio di risposta
        }
    }

    private void sendMessage() {
        String messageText = inputField.getText();
        addMessage(messageText, false);
        addMessage("bene",true);
        inputField.clear();
    }
}


