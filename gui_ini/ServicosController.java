package gui_ini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.services.CarService;
import model.services.UnitService;
import utils.Loader;

import java.net.URL;
import java.util.ResourceBundle;

public class ServicosController implements Initializable {

    private Loader loader;


    @FXML
    public void handleClickBasic() {
        loader.loadView("/gui_ini/tabelasCarros.fxml", (MenuController menuController) -> {
            menuController.setCarService(new CarService());
            menuController.setUnitService(new UnitService());
            menuController.updateTableView(false);
        });
    }

    @FXML
    public void handleClickPremium() {
        loader.loadView("/gui_ini/tabelasCarros.fxml", (MenuController menuController) -> {
            menuController.setCarService(new CarService());
            menuController.setUnitService(new UnitService());
            menuController.updateTableView(true);
        });
    }

    @FXML
    public void handleClickWhoWeAre() {
        loader.loadView("/gui_ini/quemSomos.fxml", (x -> {
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
