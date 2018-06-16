package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import objects.Coordinate;

import java.util.ArrayList;

public class Controller {
    private ObservableList<Coordinate> coordinates = FXCollections.observableArrayList();

    @FXML
    private TableView<Coordinate> mainTable;
    @FXML
    private TableColumn<Coordinate, String> colK;
    @FXML
    private TableColumn<Coordinate, String> colY;

    @FXML
    private Button btnFillRandom;
    @FXML
    private Button btnSolveTask;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnCancel;

    private final String emptyLine = "";

    @FXML
    private void initialize() {
        mainTable.setEditable(true);
        mainTable.itemsProperty().setValue(coordinates);

        fillNumbers();
        colK.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue1()));
        colK.setCellFactory(TextFieldTableCell.forTableColumn());
        colK.setOnEditCommit(event -> ((Coordinate) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValue1(event.getNewValue()));

        colY.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue2()));
    }

    /**
     * Заполнение столбца colK случайными значениями
     */
    private void fillNumbers() {
        for (int i = 0; i < 10; i++) {
            coordinates.add(new Coordinate(emptyLine, emptyLine));
        }

        mainTable.refresh();
    }

    @FXML
    private void fillRandomNumbers() {
        for (int i = 0; i < coordinates.size(); i++) {
            int a = (int) (Math.random()*100);

            coordinates.get(i).setValue1(String.valueOf(a));
            coordinates.get(i).setValue2(emptyLine);
        }

        mainTable.refresh();
    }

    @FXML
    private void clearCells() {
        for (int i = 0; i < coordinates.size(); ) {
            coordinates.remove(coordinates.get(i));
        }

        fillNumbers();
    }

    @FXML
    private void cancelWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void solveTask() {
        ArrayList<Long> arrMultiply = new ArrayList<>();
        ArrayList<Integer> arrSum = new ArrayList<>();
        ArrayList<Integer> arrCoordinates = new ArrayList<>();

        long multiplyK = 1;
        int sumK = 0;

        try {
            for (int i = 0; i < coordinates.size(); i++) {
                multiplyK *= Integer.parseInt(coordinates.get(i).getValue1());
                sumK += Integer.parseInt(coordinates.get(i).getValue1());
                
                arrMultiply.add(multiplyK);
                arrSum.add(sumK);
                arrCoordinates.add(Integer.parseInt(coordinates.get(i).getValue1()));
            }

            for (int i = 0; i < coordinates.size(); i++) {

                try{
                    long numFromArrMultiply = arrMultiply.get(i-1);
                    int numFromArrSum = arrSum.get(i);

                    double n = 3;
                    double a = Math.pow(numFromArrSum, 2/n);

                    //long subtractionNumbers = numFromArrMultiply - numFromArrSum;

                    if (numFromArrMultiply == 0) {
                        coordinates.get(i).setValue2(String.valueOf("-"));
                    } else {
                        int valueI = arrCoordinates.get(i);
                        int valueIMinusOne = arrCoordinates.get(i-1);

                        double solutionSubtractionSinAndCos = Math.pow(Math.pow(Math.sin(valueI), 2) - Math.pow(Math.cos(valueIMinusOne), 2), 3);

                        double solution = (a/numFromArrMultiply)*solutionSubtractionSinAndCos;

                        coordinates.get(i).setValue2(String.valueOf(solution));
                        }
                } catch (Exception e) {
                    coordinates.get(i).setValue2(String.valueOf("-"));
                }
            }
        } catch (Exception e) {
            // miss
        }

        mainTable.refresh();
    }
}
