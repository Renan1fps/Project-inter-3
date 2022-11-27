package gui_ini;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import model.services.CarService;
import model.services.UnitService;
import utils.Loader;

public class MainController implements Initializable {

    private Loader loader;

    @FXML
    private MenuItem menuItemSeller;

    @FXML
    private MenuItem menuItemDepartment;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private Button BTN_inicio;

    @FXML
    private Button BTN_quemSomos;

    @FXML
    private Button BTN_servicos;

    @FXML
    public void handleClickNavigate() {
        loader.loadView("/gui_ini/tabelasCarros.fxml", (MenuController menuController) -> {
            menuController.setCarService(new CarService());
            menuController.setUnitService(new UnitService());
            menuController.updateTableView(true);
        });
    }

    @FXML
    public void handleClickServices() {
        loader.loadView("/gui_ini/servicos.fxml", (x -> {
        }));
    }

    @FXML
    public void handleClickWhoWeAre() {
        loader.loadView("/gui_ini/quemSomos.fxml", (x -> {
        }));
    }

    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        loader = Loader.getLoaderInstance();
    }
}
