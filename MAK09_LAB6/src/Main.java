import java.util.List;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{
    @Override
    public void start(final Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        final Scene scene = new Scene(root, Color.GHOSTWHITE);

        stage.setScene(scene);
        stage.setTitle("Работа с графикой в Java");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(final String[] arguments)
    {
        Application.launch(arguments);
    }
}
