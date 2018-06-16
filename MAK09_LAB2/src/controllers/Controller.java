package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Controller {

    @FXML
    private TextField txtX;

    @FXML
    private TextField txtB;

    @FXML
    private TextField txtA;

    @FXML
    private Button btnSolve;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnCancel;

    @FXML
    private Label labelAnswer;

    /**
     * Обрабатывает нажание кнопки "Решить" - btnSolve
     */
    @FXML
    public void clickedSolve() {
        int x = 0;
        int b = 0;
        int a = 0;

        // Возвращаем стили у TextField к стандартным
        applyDefaultStyle(txtX);
        applyDefaultStyle(txtB);
        applyDefaultStyle(txtA);

        int countMistakes = 0;

        try {
            x = Integer.parseInt(txtX.getText());
        } catch (Exception e) {
            applyErrorStyle(txtX);
            countMistakes++;
        }

        try {
            b = Integer.parseInt(txtB.getText());
        } catch (Exception e) {
            applyErrorStyle(txtB);
            countMistakes++;
        }

        try {
            a = Integer.parseInt(txtA.getText());
        } catch (Exception e) {
            applyErrorStyle(txtA);
            countMistakes++;
        }

        if (countMistakes == 0) {
            if (x > 6) {
                labelAnswer.setText("Ответ: " + (a/x + b/Math.pow(x, 2)));
            } else {
                labelAnswer.setText("Ответ: " + (Math.pow(a, 2)*(x + b)));
            }
        } else {
            labelAnswer.setText("Ответ: ошибка");
        }
    }

    /**
     * Применяем к TextField стиль ошибки
     * @param txt - TextField в котором было выкинуто исключение
     */
    private void applyErrorStyle(TextField txt) {
        txt.getStyleClass().add("error");
        txt.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
    }

    /**
     * Применяем к TextField стандартный стиль
     * @param txt - TextField, которому нужно применить стандартный стиль
     */
    private void applyDefaultStyle(TextField txt) {
        if (txt.getStyleClass().contains("error")) {
            txt.getStyleClass().remove("error");
        }
    }

    /**
     * Обрабатывем нажатие кнопки "Очистить" - btnClear
     */
    @FXML
    private void clickedClear() {
        applyDefaultStyle(txtX);
        applyDefaultStyle(txtB);
        applyDefaultStyle(txtA);

        txtX.setText("");
        txtB.setText("");
        txtA.setText("");
        labelAnswer.setText("Ответ: ");
    }

    /**
     * Обрабатываем нажание кнопки "Выйти" - btnCancel
     */
    @FXML
    private void clickedCancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
