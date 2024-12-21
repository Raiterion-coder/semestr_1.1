package client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import client.ClientSocketHandler;

public class ChatController {
    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    private ClientSocketHandler socketHandler;

    public void initialize() {
        sendButton.setOnAction(event -> {
            String message = messageField.getText();
            socketHandler.sendMessage(message);
            messageField.clear();
        });
    }

    public void setSocketHandler(ClientSocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }
}
