package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Chat Login");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
