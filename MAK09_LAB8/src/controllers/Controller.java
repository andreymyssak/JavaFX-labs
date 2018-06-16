package controllers;

import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Controller {
    @FXML
    private Button btnRun;

    @FXML
    private Path path;
    @FXML
    private Rectangle rectangle;

    @FXML
    private Slider startPoint;
    @FXML
    private Slider endPoint;

    @FXML
    private LineTo lineToOne;
    @FXML
    private LineTo lineToTwo;
    @FXML
    private LineTo lineToThree;
    @FXML
    private LineTo lineToFour;
    @FXML
    private LineTo lineToFive;
    @FXML
    private LineTo lineToSix;
    @FXML
    private LineTo lineToSeven;

    @FXML
    private MoveTo moveTo;

    @FXML
    private Label labelShowMistake;

    @FXML
    private void initialize() {
        Image image = new Image("/images/bus.png");
        rectangle.setFill(new ImagePattern(image));
    }

    @FXML
    private void startDrive() {
        int valueStartPoint = (int) startPoint.getValue();
        int valueEndPoint = (int) endPoint.getValue();

        Path path1 = new Path();

        if (valueEndPoint <= valueStartPoint) {
            labelShowMistake.setVisible(true);
        } else {
            labelShowMistake.setVisible(false);
            path1 = setMoveTo(path1, valueStartPoint);

            if (valueStartPoint == 6 || valueStartPoint == 5) {
                valueEndPoint++;
            }

            for (int i = valueStartPoint; i < valueEndPoint; i++) {
                if (i == 4) {
                    valueEndPoint++;
                }
                path1.getElements().add(path.getElements().get(i));
            }

            PathTransition transition = generatePathTransition(path1);
            transition.play();
        }
    }

    private Path setMoveTo(Path path1, int valueStartPoint) {
        switch (valueStartPoint) {
            case 1: path1.getElements().add(moveTo);
                break;
            case 2: path1.getElements().add(new MoveTo(lineToOne.getX(), lineToOne.getY()));
                break;
            case 3: path1.getElements().add(new MoveTo(lineToTwo.getX(), lineToTwo.getY()));
                break;
            case 4: path1.getElements().add(new MoveTo(lineToThree.getX(), lineToThree.getY()));
                break;
            case 5: path1.getElements().add(new MoveTo(lineToFive.getX(), lineToFive.getY()));
                break;
            case 6: path1.getElements().add(new MoveTo(lineToSix.getX(), lineToSix.getY()));
                break;
            case 7: path1.getElements().add(new MoveTo(lineToSeven.getX(), lineToSeven.getY()));
                break;
        }

        return path1;
    }

    private PathTransition generatePathTransition(Path path1) {
        final PathTransition pathTransition = new PathTransition();
        int time = 0;
        int countElements = path1.getElements().size() - 1;

        switch (countElements) {
            case 1: time = 1;
                break;
            case 2: time = 2;
                break;
            case 3: time = 3;
                break;
            case 4: time = 4;
                break;
            case 5: time = 5;
                break;
            case 6: time = 6;
                break;
            case 7: time = 7;
                break;
        }

        pathTransition.setDuration(Duration.seconds(time));
        pathTransition.setDelay(Duration.seconds(0));
        pathTransition.setPath(path1);
        pathTransition.setNode(rectangle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        return pathTransition;
    }
}
