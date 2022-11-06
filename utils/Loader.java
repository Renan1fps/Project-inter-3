package utils;

import java.io.IOException;
import java.util.function.Consumer;
import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Loader {
	
	private Stage stage;
	private Scene scene;

	public synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			stage = Main.getMainStage();
			scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();

			T controller = loader.getController();
			initializingAction.accept(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
