package gui_ini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.Loader;

import java.net.URL;
import java.util.ResourceBundle;

public class QuemSomosController implements Initializable {

    private Loader loader;

    @FXML
    public void handleClickServices() {
        loader.loadView("/gui_ini/servicos.fxml", (x -> {
        }));
    }

    @FXML
    public void handleClickInit() {
        loader.loadView("/gui_ini/inicio.fxml", (x -> {
        }));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader = Loader.getLoaderInstance();
    }
}
