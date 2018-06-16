package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.Coordinate;

import java.util.ArrayList;
import java.util.Collections;

public class MainController {
    private ObservableList<Coordinate> coordinates = FXCollections.observableArrayList();

    @FXML
    private Label labelInfo;
    @FXML
    private Label labelMistakeOne;
    @FXML
    private Label labelMistakeTwo;
    @FXML
    private Label labelAmountOne;

    @FXML
    private Button btnFill;
    @FXML
    private Button btnRun;

    @FXML
    private TableView<Coordinate> mainTable;
    @FXML
    private TableColumn<Coordinate, String> c1Column;
    @FXML
    private TableColumn<Coordinate, String> c2Column;
    @FXML
    private TableColumn<Coordinate, String> c3Column;
    @FXML
    private TableColumn<Coordinate, String> c4Column;
    @FXML
    private TableColumn<Coordinate, String> c5Column;

    private double max = Double.MIN_VALUE;

    @FXML
    private void initialize() {
        mainTable.setEditable(true);
        mainTable.itemsProperty().setValue(coordinates);

        zeroPadding();

        c1Column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue1()));
        c1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        c1Column.setOnEditCommit(event -> ((Coordinate) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValue1(event.getNewValue()));

        c2Column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue2()));
        c2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        c2Column.setOnEditCommit(event -> ((Coordinate) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValue2(event.getNewValue()));

        c3Column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue3()));
        c3Column.setCellFactory(TextFieldTableCell.forTableColumn());
        c3Column.setOnEditCommit(event -> ((Coordinate) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValue3(event.getNewValue()));

        c4Column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue4()));
        c4Column.setCellFactory(TextFieldTableCell.forTableColumn());
        c4Column.setOnEditCommit(event -> ((Coordinate) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValue4(event.getNewValue()));

        c5Column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue5()));
        c5Column.setCellFactory(TextFieldTableCell.forTableColumn());
        c5Column.setOnEditCommit(event -> ((Coordinate) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValue5(event.getNewValue()));
    }

    /**
     * Заполняем таблицу нулями
     */
    private void zeroPadding() {
        for (int i = 0; i < 4; i++) {
            int zero = 0;
            coordinates.add(new Coordinate(String.valueOf(zero), String.valueOf(zero), String.valueOf(zero), String.valueOf(zero), String.valueOf(zero)));
        }

        max = 0;

        labelInfo.setText("Максимальный элемент - " + max);
    }

    /**
     * Обрабатывание события при нажатии на кнопку "Заполнить случ. числами"
     * Заполняем таблицу случайными числами
     */
    public void fillRandomNumbers() {
        labelAmountOne.setText("");
        labelMistakeOne.setVisible(false);
        labelMistakeTwo.setVisible(false);

        ArrayList<Double> arr = new ArrayList<>();

        for (int i = 0; i < coordinates.size(); ) {
            coordinates.remove(i);
        }

        for (int i = 0; i < 4; i++) {
            double a = Math.random()*200 - 100;
            double b = Math.random()*200 - 100;
            double c = Math.random()*200 - 100;
            double d = Math.random()*200 - 100;
            double e = Math.random()*200 - 100;

            arr.add(a);
            arr.add(b);
            arr.add(c);
            arr.add(d);
            arr.add(e);

            coordinates.add(new Coordinate(String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d), String.valueOf(e)));
        }

        max = Collections.max(arr);
        findPositionMax(arr);
    }

    /**
     * Находим позицию максимального числа, невзирая на кол-во единиц перед ним
     * @param arr - список заполненный элементами из таблицы
     */
    private void findPositionMax(ArrayList<Double> arr) {
        int positionMax = 0;

        // Считаем какое количество элементов находится до максимального числа
        for (int i = 0; i < arr.size(); i++) {
            positionMax++;
            if (arr.get(i) == max) {
                break;
            }
        }

        // Определяем строку и позицию максимального числа в строке
        if (positionMax <= 5) {
            setTextLabelPositionMax(0, positionMax);
        } else if (positionMax > 5 && positionMax <= 10) {
            setTextLabelPositionMax(1, positionMax - 5);
        } else if (positionMax > 10 && positionMax <= 15) {
            setTextLabelPositionMax(2, positionMax - 10);
        } else {
            setTextLabelPositionMax(3, positionMax - 15);
        }
    }

    /**
     * Обрабатывание события при нажатии на кнопку "Выполнить задание"
     */
    public void solveTask() {
        labelMistakeOne.setVisible(false);
        labelMistakeTwo.setVisible(false);

        ArrayList<Double> arr = new ArrayList<>();

        try {
            for (int i = 0; i < coordinates.size(); i++) {
                arr.add(Double.valueOf(coordinates.get(i).getValue1()));
                arr.add(Double.valueOf(coordinates.get(i).getValue2()));
                arr.add(Double.valueOf(coordinates.get(i).getValue3()));
                arr.add(Double.valueOf(coordinates.get(i).getValue4()));
                arr.add(Double.valueOf(coordinates.get(i).getValue5()));
            }

            max = Collections.max(arr);

            int amountOne = 0; // кол-во единиц перед максимальным числом

            /*
                Вводим переменную haveSolution для того, чтобы можно было
                найти позицию максимального числа, несмотря на недостаток единиц перед ним
             */
            boolean haveSolution = false;

            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) == 1.0) {
                    amountOne++;
                    if (arr.get(i + 1) == max) {
                        haveSolution = true;
                    }
                } else break;
            }

            int positionMax = amountOne + 1; // позиция максимального числа = (кол-во единиц + 1)

            /*
                Если максимальное число будет равняться единице,
                то от amountOne и positionMax отнимается по единице,
                потому что одну единицу берём как максимальное значение.
             */
            if (max == 1) {
                amountOne--;
                positionMax--;
            }

            /*
                Если перед максимальным числом недостаточно единиц,
                то тогда, просто определяем его место в таблице, иначе
                перезаписываем его значение и обновляем labelInfo
             */
            if (!haveSolution) {
                findPositionMax(arr);
                labelAmountOne.setText("Сумма - " + 0);
            } else {

                /*
                    Мы имеем 4 строки. В каждой строке содержится всего пять
                    элементов. В зависимости от значения positionMax выбирается та или иная строка.
                 */
                if (positionMax <= 5) {
                    replaceOldValueToNew(0, positionMax, amountOne);
                    setTextLabelPositionMax(0, positionMax);
                } else if (positionMax > 5 && positionMax <= 10) {
                    replaceOldValueToNew(1, positionMax - 5, amountOne);
                    setTextLabelPositionMax(1, positionMax - 5);
                } else if (positionMax > 10 && positionMax <= 15) {
                    replaceOldValueToNew(2, positionMax - 10, amountOne);
                    setTextLabelPositionMax(2, positionMax - 10);
                } else {
                    replaceOldValueToNew(3, positionMax - 15, amountOne);
                    setTextLabelPositionMax(3, positionMax - 15);
                }

                labelAmountOne.setText("Сумма - " + amountOne);
            }

        } catch (Exception e) {
            labelMistakeOne.setVisible(true);
            labelMistakeTwo.setVisible(true);
        }
    }

    /**
     * Меняем значение максимального числа на сумму единиц, впереди него стоящих
     * @param row - номер строки, в которой находится максимальное число
     * @param positionMaxInRow - позиция максимального числа в строке
     */
    private void replaceOldValueToNew(int row, int positionMaxInRow, int amountOne) {
        switch (positionMaxInRow) {
            case 1: coordinates.get(row).setValue1(String.valueOf(amountOne));
                break;
            case 2: coordinates.get(row).setValue2(String.valueOf(amountOne));
                break;
            case 3: coordinates.get(row).setValue3(String.valueOf(amountOne));
                break;
            case 4: coordinates.get(row).setValue4(String.valueOf(amountOne));
                break;
            case 5: coordinates.get(row).setValue5(String.valueOf(amountOne ));
                break;
        }

        mainTable.refresh();
    }

    /**
     * Устанавливаем значение labelInfo
     * @param row - номер строки, в которой находится максимальное число
     * @param positionMaxInRow - позиция максимального числа в строке
     */
    private void setTextLabelPositionMax(int row, int positionMaxInRow) {
        switch (positionMaxInRow) {
            case 1: labelInfo.setText("Максимальный элемент - " + max + " [" + (row + 1) + "," + 1 + "] ");
                break;
            case 2: labelInfo.setText("Максимальный элемент - " + max + " [" + (row + 1) + "," + 2 + "] ");
                break;
            case 3: labelInfo.setText("Максимальный элемент - " + max + " [" + (row + 1) + "," + 3 + "] ");
                break;
            case 4: labelInfo.setText("Максимальный элемент - " + max + " [" + (row + 1) + "," + 4 + "] ");
                break;
            case 5: labelInfo.setText("Максимальный элемент - " + max + " [" + (row + 1) + "," + 5 + "] ");
                break;
        }
    }
}
