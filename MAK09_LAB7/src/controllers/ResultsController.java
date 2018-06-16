package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import start.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author - Andrey Myssak
 * @since - 18.10.2017
 */
public class ResultsController {
    private Repository repository = new Repository();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnGoBackScreen;
    @FXML
    private Button btnCloseWindow;

    @FXML
    private TextArea txtArea;

    @FXML
    private void initialize() {

        HashMap<Integer, String> hashMap = repository.getHashMap();
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            txtArea.appendText(entry.getValue() + "\n");
        }
    }

    @FXML
    private void goBack() throws IOException {
        Stage stage = (Stage) btnGoBackScreen.getScene().getWindow();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/youth.fxml"));
        rootPane.getChildren().setAll(pane);

        stage.setTitle("Юность");
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) btnCloseWindow.getScene().getWindow();
        stage.close();
    }
}
