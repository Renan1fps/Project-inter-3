package utils;

import java.io.IOException;
import java.util.function.Consumer;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Loader {

    private static Loader loaderInstance = null;

    private Loader() {
    }

    public synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Stage stage = Main.getMainStage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();

            T controller = loader.getController();
            initializingAction.accept(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Loader getLoaderInstance() {
        if (loaderInstance == null) {
            loaderInstance = new Loader();
            return loaderInstance;
        }
        return loaderInstance;
    }

}
