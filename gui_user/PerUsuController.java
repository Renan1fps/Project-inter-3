package gui_user;

import application.Main;
import gui_ini.LoginController;
import gui_ini.MainController;
import gui_ini.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.entities.User;
import model.services.CarService;
import model.services.UnitService;
import model.services.UserService;
import state.AuthState;
import utils.Alerts;
import utils.Loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;

public class PerUsuController implements Initializable {

    private Loader loader;
    private UserService userService;
    private int idUser;
    private String imageURL;

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

    @FXML
    private TextField confirmacaoSenha;

    @FXML
    private TextField senhaUser;

    @FXML
    private ImageView imageView = new ImageView();

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
                loginController.showMessage(
                        "Autenticação necessária",
                        "Autenticação",
                        "Autenticação é necessária para poder acessar essa página",
                        Alert.AlertType.INFORMATION);
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

        Image image = new Image(user.getImageUrl());
        this.idUser = user.getId();
        cpfUser.setText(user.getCpf());
        emailUser.setText(user.getEmail());
        nacionalidade.setText(user.getNacionalidade());
        nomeUser.setText(user.getName());
        telefoneUser.setText(user.getPhone());
        imageView.setImage(image);
    }

    @FXML
    public void updateUser() {
        User user = new User();
        user.setId(this.idUser);
        user.setName(nomeUser.getText());
        user.setCpf(cpfUser.getText());
        user.setNacionalidade(nacionalidade.getText());
        user.setEmail(emailUser.getText());
        user.setPhone(telefoneUser.getText());
        user.setImageUrl(this.imageURL);

        if(!Objects.equals(senhaUser.getText(), "") && !Objects.equals(senhaUser.getText(), confirmacaoSenha.getText())){
            Alerts.showAlert("Senha incorreta", "Senha", "O campo 'senha' e 'confirmar senha' são diferentes!",
                    Alert.AlertType.ERROR);
            return;
        }

        user.setPassowrd(senhaUser.getText());
        userService.saveOrUpdate(user);
        loader.loadView("../gui_ini/inicio.fxml", (MainController mainController) -> {
            mainController.showMessage("Usuário atualizado",
                    null,
                    "Usuário atualizado com sucesso!",
                    Alert.AlertType.CONFIRMATION);
        });
    }

    @FXML
    public void pickImage() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Main.getMainStage());
        Image image = new Image(file.toURI().toString());
        this.imageURL = file.toURI().toString();
        imageView.setImage(image);
        imageView.setVisible(true);
        imageView.setCache(true);
        imageView.isVisible();
        imageView.setX(10);
        imageView.setY(10);
        imageView.setPreserveRatio(true);
        imageView.getImage();
    }
}
