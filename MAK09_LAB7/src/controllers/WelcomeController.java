package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnClose;
    @FXML
    private Button btnStart;

    @FXML
    private void loadNextScreen() throws IOException {
        Stage stage = (Stage) btnStart.getScene().getWindow();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/childhood.fxml"));
        stage.setTitle("Детство");
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
