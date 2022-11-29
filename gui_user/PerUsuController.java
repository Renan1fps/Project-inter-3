package gui_user;

import gui_ini.LoginController;
import gui_ini.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.entities.User;
import model.services.CarService;
import model.services.UnitService;
import model.services.UserService;
import state.AuthState;
import utils.Loader;

import java.net.URL;

import java.util.ResourceBundle;

public class PerUsuController implements Initializable {

    private Loader loader;
    private UserService userService;

    @FXML
    private TextField cpfUser;

    @FXML
    private TextField emailUser;

    @FXML
    private TextField nacionalidade;

    @FXML
    private TextField nomeUser;

    @FXML
    private TextField telefoneUser;

    @FXML
    private CheckBox CbIsAdmin;

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

    @FXML
    public void handleClickBack() {
        loader.loadView("../gui_ini/tabelasCarros.fxml", (MenuController menuController) -> {
            menuController.setCarService(new CarService());
            menuController.setUnitService(new UnitService());
            menuController.updateTableView(true);
        });
    }

    public void setService(UserService userService) {
        this.userService = userService;
    }

    public void updateView() {

        if (!AuthState.isAuthenticated()) {
            loader.loadView("/gui_ini/login.fxml", (LoginController loginController) -> {
                loginController.setService(new UserService());
            });
            return;
        }

        if (userService == null) {
            throw new IllegalStateException("Service was null");
        }

        if (!AuthState.getUserLogged().isIs_adm()) {
            CbIsAdmin.setDisable(true);
        }

        User user = userService.findByEmail(AuthState.getUserLogged().getEmail());

        cpfUser.setText(user.getCpf());
        emailUser.setText(user.getEmail());
        nacionalidade.setText(user.getNacionalidade());
        nomeUser.setText(user.getName());
        telefoneUser.setText(user.getPhone());
    }
}
