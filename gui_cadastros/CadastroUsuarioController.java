package gui_cadastros;

import gui_ini.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.entities.User;
import model.services.UserService;
import utils.Alerts;
import utils.Loader;

import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;

public class CadastroUsuarioController implements Initializable {

    @FXML
    private TextField TXT_cellphone;

    @FXML
    private TextField TXT_cpf;

    @FXML
    private TextField TXT_email;

    @FXML
    private TextField TXT_nacionalidade;

    @FXML
    private TextField TXT_name;

    @FXML
    private TextField TXT_passwordConfirmation;

    @FXML
    private TextField TXT_senha;

    private Loader loader;
    private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader = Loader.getLoaderInstance();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void handleClickCreateUser() {
        if (userService == null) {
            throw new IllegalStateException("Service was null");
        }

        User userToCreate = new User();
        userToCreate.setName(TXT_name.getText());
        userToCreate.setNacionalidade(TXT_nacionalidade.getText());
        userToCreate.setPhone(TXT_cellphone.getText());
        userToCreate.setPassowrd(TXT_senha.getText());
        userToCreate.setEmail(TXT_email.getText());
        userToCreate.setCpf(TXT_cpf.getText());

        if (TXT_senha.getText().isEmpty() || TXT_name.getText().isEmpty() || TXT_cpf.getText().isEmpty() || TXT_email.getText().isEmpty()) {
            Alerts.showAlert("Campos inválidos", "Campos", "Por favor, preencha todos os campos",
                    Alert.AlertType.WARNING);
            return;
        }

        if (!Objects.equals(TXT_senha.getText(), TXT_passwordConfirmation.getText())) {
            Alerts.showAlert("Campos inválidos", "Campos", "Senhas diferentes. verifique e tente novamente mais tarde",
                    Alert.AlertType.WARNING);
            return;
        }

        User existsUser = userService.findByEmail(TXT_email.getText());

        if (existsUser != null) {
            Alerts.showAlert("Usuários existente", "Usuário", "O email informado já consta na base de dados",
                    Alert.AlertType.WARNING);
            return;
        }
        userService.saveOrUpdate(userToCreate);
        loader.loadView("../gui_ini/login.fxml",(LoginController loginController) -> {
            loginController.setService(new UserService());
            loginController.showAlertUserCreate();
        });
    }

    @FXML
    public void handleClickCancel() {
        loader.loadView("../gui_ini/login.fxml", (x -> {
        }));
    }
}
