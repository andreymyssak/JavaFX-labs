package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    private TextArea userText;
    @FXML
    private TextArea selectedWords;

    @FXML
    private Button btnRun;
    @FXML
    private Button btnClear;

    public void runJob() {
        String pattern = "\\p{Punct}";
        String text = userText.getText().replaceAll(pattern, "");

        String[] words = text.split(" ");
        String letters = "АВСТРХОНКМУЕ";

        StringBuilder stringBuilder = new StringBuilder();

        for (String word : words) {
            String[] lettersFromWord = word.split("");
            int count = 0;


            for (String aLettersFromWord : lettersFromWord) {

                if (aLettersFromWord.equals("")) {
                    count = -1;
                    break;
                } else {
                    if (letters.contains(aLettersFromWord)) {
                        count++;
                    }
                }
            }

            if (count == lettersFromWord.length) {
                stringBuilder.append(word).append(" ");
            }
        }

        selectedWords.setText(stringBuilder.toString());
    }

    public void clearFields() {
        userText.setText("");
        selectedWords.setText("");
    }
}
