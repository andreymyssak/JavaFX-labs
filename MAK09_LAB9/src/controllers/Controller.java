package controllers;

import exceptions.MissingItemsInArray;
import exceptions.NumberOfItemsInArrayFull;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class Controller {
    @FXML
    private TextArea txtAreaInfoSpace;

    @FXML
    private Button btnLoadInformation;
    @FXML
    private Button btnProcessData;
    @FXML
    private Button btnSaveInformation;
    @FXML
    private Button btnClearTxtArea;

    @FXML
    private Text txtForMistakes;

    private ArrayList<String> arrLinesFromFile = new ArrayList<>();
    private String[][] arrNum = new String[5][6];

    private final byte numberOfRows= 5;
    private final byte numberOfColumns = 6;

    private boolean isSave = false;
    private boolean isProcessed = false;

    @FXML
    private void initialize() {

    }

    /**
     * Загружаем файл при нажатии на кнопку btnLoadInformation
     */
    @FXML
    private void loadFile () {
        txtForMistakes.setText("");
        isProcessed = false;

        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(btnLoadInformation.getScene().getWindow());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            for (int i = 0; i < arrLinesFromFile.size(); ) {
                arrLinesFromFile.remove(i);
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                arrLinesFromFile.add(line);
            }

            createMassive();
            txtAreaInfoSpace.appendText("Исходные даные из файла:" + "\n");

            printMassive();
            txtAreaInfoSpace.appendText("\n");

            bufferedReader.close();
        } catch (MissingItemsInArray e) {
            txtForMistakes.setText("Недостаточно элементов в полученных данных");
        } catch (NumberOfItemsInArrayFull e){
            txtForMistakes.setText("Слишком много элементов в полученных данных");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            txtForMistakes.setText("Файл не был загружен!");
        }
    }

    /**
     * Создаём массив
     */
    private void createMassive() throws MissingItemsInArray, NumberOfItemsInArrayFull {
        for (int i = 0; i < 5; i++) {
            String[] arr = arrLinesFromFile.get(i).split(" ");
            int k = 0;

            String[] arrClean;
            try {
                arrClean = new String[numberOfColumns];
                for (String anArr : arr) {
                    String word = anArr.replaceAll("[\uFEFF-\uFFFF]", "");
                    if (!word.equals("")) {
                        arrClean[k] = anArr;
                        k++;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                throw new NumberOfItemsInArrayFull();
            }

            if (k < 6) {
                throw new MissingItemsInArray();
            } else {
                for (int j = 0; j < arrClean.length; j++) {
                    arrNum[i][j] = arrClean[j];
                }
            }
        }

        refactoringMassive();
    }

    /**
     * Обрабатываем массив: выравниваем строки
     */
    private void refactoringMassive() {
        for (int i = 0; i < 6; i++) {
            setNewValue(i, getMaxLength(i));
        }
    }

    /**
     * Получаем максимальную длину числа в столбце
     * @param numberRow - номер столбца
     * @return maxLength - самая максимальная длина элемента в столбце
     */
    private int getMaxLength(int numberRow) {
        int maxLength = Integer.MIN_VALUE;

        for (int i = 0; i < 5; i++) {
            if (maxLength < arrNum[i][numberRow].length()) {
                maxLength = arrNum[i][numberRow].length();
            }
        }

        return maxLength;
    }

    /**
     * Выравниваем столбец и переприсваиваем значения в двумерный массив arrNum
     * @param numberRow - номер столбца
     * @param maxLength - самая максимальная длина элемента в столбце
     */
    private void setNewValue(int numberRow, int maxLength) {
        final String sevenSpaces = "\u0020\u0020\u0020\u0020\u0020\u0020\u0020";
        final String twoSpaces = "\u0020\u0020";
        final String space = "\u0020";

        if (isSave) {
            for (int i = 0; i < 5; i++) {
                if (String.valueOf(arrNum[i][numberRow]).length() == maxLength) {
                    if (numberRow == 0) {
                        arrNum[i][numberRow] = arrNum[i][numberRow];
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    int s = maxLength - arrNum[i][numberRow].length();

                    for (int k = 0; k < s; k++) {
                        sb.append(space);
                    }

                    sb.append(String.valueOf(arrNum[i][numberRow]));
                        arrNum[i][numberRow] = "" + sb;
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (String.valueOf(arrNum[i][numberRow]).length() == maxLength) {
                    if (numberRow == 0) {
                        arrNum[i][numberRow] = sevenSpaces + arrNum[i][numberRow]; // 7 пробелов
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    int s = maxLength - arrNum[i][numberRow].length();

                    for (int k = 0; k < s; k++) {
                        sb.append(twoSpaces);
                    }

                    sb.append(String.valueOf(arrNum[i][numberRow]));
                    if (numberRow == 0) {
                        arrNum[i][numberRow] = sevenSpaces + sb; // 7 пробелов
                    } else {
                        arrNum[i][numberRow] = "" + sb;
                    }
                }
            }
        }

    }

    /**
     * Вывод массива на экран
     */
    private void printMassive() {
        final String distanceToNextElement = "\u0020\u0020\u0020\u0020"; // 4 пробеда
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                txtAreaInfoSpace.appendText(arrNum[i][j] + distanceToNextElement);
            }
            txtAreaInfoSpace.appendText("\n");
        }
    }

    /**
     * Обрабатываем массив при нажатии на кнопку btnProcessData
     */
    @FXML
    private void processData() {
        int max = Integer.MIN_VALUE;
        int sumNumbersFromFirstLine = 0;

        try {
            clearSpaces();

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if (max < Integer.parseInt(arrNum[i][j])) {
                        max = Integer.parseInt(arrNum[i][j]);
                    }
                }
            }

            for (int i = 0; i < 6; i++) {
                sumNumbersFromFirstLine += Integer.parseInt(arrNum[0][i]);
            }

            if (max != sumNumbersFromFirstLine) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        arrNum[i][j] = arrNum[i][j];
                        if (max == Integer.parseInt(arrNum[i][j])) {
                            arrNum[i][j] = String.valueOf(max * 2);
                        }
                    }
                }
            }

            txtAreaInfoSpace.appendText("Данные обработанны!" + "\n");

            refactoringMassive();

            txtAreaInfoSpace.appendText("\n");
            txtAreaInfoSpace.appendText("Обработанные данные:" + "\n");

            printMassive();
            txtAreaInfoSpace.appendText("\n");

            isProcessed = true;
        } catch (NumberFormatException e) {
            isProcessed = false;
            txtForMistakes.setText("В массиве обнаружено не числовое значение!");
        } catch (Exception e) {
            isProcessed = false;
            txtForMistakes.setText("Загрузите файл!");
        }
    }

    /**
     * Очищаем массив от пробелов вообще
     */
    private void clearSpaces() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                arrNum[i][j] = arrNum[i][j].replaceAll(" ", "");
            }
        }
    }

    /**
     * Сохраняем обработанный массив в файл
     */

    @FXML
    private void saveInformation() {
        if (isProcessed) {
            try {
                final FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(btnLoadInformation.getScene().getWindow());

                isSave = true;
                clearSpaces();
                refactoringMassive();


                try(BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath())))
                {
                    for (int i = 0; i < numberOfRows; i++) {
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < numberOfColumns; j++) {
                            //bw.write(arrNum[i][j]);
                            sb.append(arrNum[i][j]).append(" "); // 4 пробела
                        }
                        bw.write(sb + System.lineSeparator());
                    }

                    bw.flush();
                    bw.close();
                } catch(IOException ex){

                    System.out.println(ex.getMessage());
                }

            } catch (Exception e) {
                txtForMistakes.setText("Загрузите файл!");
            }
        } else {
            txtForMistakes.setText("Файл не обработан!");
        }
    }

    /**
     * Очищаем поле txtAreaInformation
     */
    @FXML
    private void clearTxtArea()
    {
        txtAreaInfoSpace.setText("");
    }

}
