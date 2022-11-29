package gui_ini;

import gui_adm.ListUserController;
import gui_cadastros.CadastroCarroController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.services.CarService;
import model.services.UnitService;
import model.services.UserService;
import state.AuthState;
import utils.Loader;

import java.net.URL;
import java.util.ResourceBundle;

public class ServicosController implements Initializable {

    private Loader loader;
    @FXML
    private Button BTN_usuarios;

    @FXML
    private Button BTN_cadastroCarros;

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

    @FXML
    public void handleClickUser() {
        loader.loadView("../gui_adm/listUsu.fxml", (ListUserController listUserController)-> {
            listUserController.setCarService(new UserService());
            listUserController.updateTableView();
        });
    }

    @FXML
    public void handleClickPostCar() {
        loader.loadView("../gui_cadastros/cadCar.fxml", (CadastroCarroController cadastroCarroController) -> {
            cadastroCarroController.setService(new CarService(), new UnitService());
            cadastroCarroController.updateView();

        });
    }

    private void hiddenButtons() {
        if (!AuthState.isAuthenticated() || !AuthState.getUserLogged().isIs_adm()) {
            BTN_usuarios.setVisible(false);
            BTN_cadastroCarros.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.hiddenButtons();
        loader = Loader.getLoaderInstance();
    }


}
