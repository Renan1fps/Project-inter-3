package gui_ini;

import gui_cadastros.CadastroUsuarioController;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.entities.User;
import model.services.CarService;
import model.services.UnitService;
import model.services.UserService;
import state.AuthState;
import utils.Alerts;
import utils.Loader;

public class LoginController implements Initializable {

    private UserService userService;
    private Loader loader;

    @FXML
    private TextField TXT_email;

    @FXML
    private TextField TXT_senha;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loader = Loader.getLoaderInstance();
    }

    private void validateForm() throws Exception {

        if (TXT_email.getText() == null || TXT_email.getText().trim().equals("")) {
            Alerts.showAlert("Email incorreto", "Email", "O campo de email n�o pode estar vazio", AlertType.WARNING);
            throw new Exception();
        }

        if (TXT_senha.getText() == null || TXT_senha.getText().trim().equals("")) {
            Alerts.showAlert("Senha incorreta", "Senha", "O campo de senha n�o pode estar vazio", AlertType.WARNING);
            throw new Exception();
        }
    }

    @FXML
    void handleLogin(ActionEvent event) {
        try {
            validateForm();
            User user = this.userService.findByEmail(TXT_email.getText());

            if (user == null) {
                Alerts.showAlert("Usuário não encontrado", "Usuário", "Usuário não encontrado na base de dados",
                        AlertType.ERROR);
                return;
            }

            if (!Objects.equals(TXT_senha.getText(), user.getPassowrd())) {
                Alerts.showAlert("Senha incorreta", "Senha", "Senha incorreta, tente novamente!",
                        AlertType.ERROR);
                return;
            }

            AuthState.setIsAuthenticated();
            AuthState.setUser(user);
            loader.loadView("/gui_ini/tabelasCarros.fxml", (MenuController menuController) -> {
                menuController.setCarService(new CarService());
                menuController.setUnitService(new UnitService());
                menuController.updateTableView(true);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleClickCreateUser() {
        loader.loadView("../gui_cadastros/cadUs.fxml", (CadastroUsuarioController cadastroUsuarioController) -> {
            cadastroUsuarioController.setUserService(new UserService());
        });
    }

    public void setService(UserService userServiceNovo) {
        this.userService = userServiceNovo;
    }

    public void showAlertUserCreate() {
        Alerts.showAlert("Usuário cadastrado", "Usuário", "Usuário cadstrado com sucesso",
                AlertType.CONFIRMATION);
    }

    public void showMessage(String title, String header, String content, AlertType type){
        Alerts.showAlert(title, header, content, type);
    }

}
