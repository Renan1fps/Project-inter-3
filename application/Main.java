package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

public class Main extends Application {

    private static Scene mainScene;
    private static Stage newStage;

    @Override
    public void start(Stage stage) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui_ini/inicio.fxml")));
            mainScene = new Scene(parent);
            newStage = stage;
            stage.setScene(mainScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Scene getMainScene() {
        return mainScene;
    }

    public static Stage getMainStage() {
        return newStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
