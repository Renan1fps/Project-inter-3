package gui_ini;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.entities.User;
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
            } else {
                AuthState.setIsAuthenticated();
                AuthState.setUser(user);
                loader.loadView("/gui_ini/tabelasCarros.fxml", (x -> {
                }));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setService(UserService userServiceNovo) {
        this.userService = userServiceNovo;
    }

}
