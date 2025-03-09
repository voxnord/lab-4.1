import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TestGUI extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("JavaFX is working!");
        Scene scene = new Scene(label, 250, 20);
        stage.setScene(scene);
        stage.setTitle("GUI JavaFX test");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
