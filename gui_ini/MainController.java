package gui_ini;

import java.net.URL;
import java.util.ResourceBundle;

import gui_adm.ListUserController;
import gui_cadastros.CadastroCarroController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import model.services.CarService;
import model.services.UnitService;
import model.services.UserService;
import state.AuthState;
import utils.Alerts;
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
    private Button BTN_usuarios;

    @FXML
    private Button BTN_cadastroCarros;

    @FXML
    public void handleClickNavigate() {
        loader.loadView("/gui_ini/tabelasCarros.fxml", (MenuController menuController) -> {
            menuController.setCarService(new CarService());
            menuController.setUnitService(new UnitService());
            menuController.updateTableView(true);
        });
    }

    public void showMessage(String title, String header, String content, Alert.AlertType type){
        Alerts.showAlert(title, header, content, type);
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
    public void initialize(URL uri, ResourceBundle rb) {
        this.hiddenButtons();
        this.loader = Loader.getLoaderInstance();
    }
}
