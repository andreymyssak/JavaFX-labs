package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import objects.SpecialForTableView;
import start.Repository;

import java.io.IOException;

/**
 * @author - Andrey Myssak
 * @since - 18.10.2017
 */
public class AdolescenceController {
    private Repository repository = new Repository();

    private ObservableList<SpecialForTableView> items = FXCollections.observableArrayList (
            new SpecialForTableView("Отлично"),
            new SpecialForTableView("Хорошо"),
            new SpecialForTableView("Удовлетворительно"),
            new SpecialForTableView("Неудовлетворительно"));

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnGoBackScreen;
    @FXML
    private Button btnNextScreen;

    @FXML
    private TableView<SpecialForTableView> tableView;
    @FXML
    private TableColumn<SpecialForTableView, String> grade;

    @FXML
    private Label labelSelected;

    @FXML
    private void initialize() {
        tableView.itemsProperty().setValue(items);

        // Скрываем заголовок таблицы
        tableView.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) {
                Pane header = (Pane) tableView.lookup("TableHeaderRow");
                if (header.isVisible()){
                    header.setMaxHeight(0);
                    header.setMinHeight(0);
                    header.setPrefHeight(0);
                    header.setVisible(false);
                }
            }
        });

        grade.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getResult()));
    }

    @FXML
    private void getLine() {
        String line = tableView.getFocusModel().getFocusedItem().getResult();
        String full = "Вы учились в пятом классе: " + line;
        repository.setResult(1, full);
        labelSelected.setText("Выбарно: " + line);
    }

    @FXML
    private void loadNextScreen() throws IOException {
        Stage stage = (Stage) btnNextScreen.getScene().getWindow();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/youth.fxml"));
        stage.setTitle("Юность");
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void goBack() throws IOException {
        Stage stage = (Stage) btnGoBackScreen.getScene().getWindow();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/childhood.fxml"));
        rootPane.getChildren().setAll(pane);

        stage.setTitle("Детство");
    }
}
