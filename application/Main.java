package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    private static Scene mainScene;
    private static Stage newStage;
    private static Boolean autenticado = false;

    @Override
    public void start(Stage stage) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/gui_ini/inicio.fxml"));
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

    public static Boolean estaAutenticado() {
        return autenticado;
    }

    public static  void setAutenticado(){
        autenticado = true;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
