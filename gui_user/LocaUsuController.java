package gui_user;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.Loader;
import java.net.URL;
import java.util.ResourceBundle;

public class LocaUsuController implements Initializable {

    private Loader loader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader = Loader.getLoaderInstance();
    }

    @FXML
    public void handleClickWhoWeAre() {
        loader.loadView("../gui_ini/quemSomos.fxml", (x -> {
        }));
    }

    @FXML
    public void handleClickInit() {
        loader.loadView("../gui_ini/inicio.fxml", (x -> {
        }));
    }

    @FXML
    public void handleClickServices() {
        loader.loadView("../gui_ini/servicos.fxml", (x -> {
        }));
    }
}
