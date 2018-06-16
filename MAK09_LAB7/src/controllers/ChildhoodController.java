package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import start.Repository;

import java.io.IOException;

/**
 * @author - Andrey Myssak
 * @since - 13.10.2017
 */
public class ChildhoodController {
    private Repository repository = new Repository();

    private ObservableList<String> items = FXCollections.observableArrayList (
            "Манная",
            "Рисовая",
            "Гороховая",
            "Гречневая");

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnGoBackScreen;
    @FXML
    private Button btnNextScreen;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label labelSelected;

    @FXML
    private void initialize() {
        listView.setItems(items);
    }

    @FXML
    private void loadNextScreen() throws IOException {
        Stage stage = (Stage) btnNextScreen.getScene().getWindow();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/adolescence.fxml"));
        rootPane.getChildren().setAll(pane);

        stage.setTitle("Отрочество");
    }

    @FXML
    private void goBack() throws IOException {
        Stage stage = (Stage) btnGoBackScreen.getScene().getWindow();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/welcome.fxml"));
        rootPane.getChildren().setAll(pane);

        stage.setTitle("Приветствие");
    }

    @FXML
    private void getLine() {
        String line = listView.getSelectionModel().getSelectedItem();
        String full = "Ваша любамая каша: " + line;

        repository.setResult(0, full);
        labelSelected.setText("Выбарно: " + line);
    }
}
