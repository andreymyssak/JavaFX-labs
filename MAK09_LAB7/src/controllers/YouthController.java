package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import start.Repository;

import java.io.IOException;

/**
 * @author - Andrey Myssak
 * @since - 18.10.2017
 */
public class YouthController {
    private Repository repository = new Repository();

    private ObservableList<String> arr = FXCollections.observableArrayList(
            "Неполное образование",
            "Среднее",
            "Среднее специальное",
            "Высшее"
    );

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnGoBackScreen;
    @FXML
    private Button btnNextScreen;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label labelSelected;

    @FXML
    private void initialize() {
        comboBox.setItems(arr);
    }

    @FXML
    private void getLine() {
        String line = comboBox.getSelectionModel().getSelectedItem();
        String full = "Ваше образование: " + line;

        repository.setResult(2, full);
        labelSelected.setText("Выбарно: " + line);
    }

    @FXML
    private void loadNextScreen() throws IOException {
        Stage stage = (Stage) btnNextScreen.getScene().getWindow();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/results.fxml"));
        stage.setTitle("Результаты");
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void goBack() throws IOException {
        Stage stage = (Stage) btnGoBackScreen.getScene().getWindow();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/adolescence.fxml"));
        rootPane.getChildren().setAll(pane);

        stage.setTitle("Отрочество");
    }
}
