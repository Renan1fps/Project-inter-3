package gui_adm;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.User;
import model.services.UserService;
import utils.Loader;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ListUserController implements Initializable {

    private Loader loader;
    private UserService userService;
    private ObservableList<User> obsList;

    @FXML
    private TableView<User> TBV_user;

    @FXML
    private TextField TXT_cpf;

    @FXML
    private TextField TXT_email;

    @FXML
    private TextField TXT_nacionalidade;

    @FXML
    private TextField TXT_nome;

    @FXML
    private TextField TXT_telefone;

    @FXML
    private TableColumn<User, String> TC_cpf;

    @FXML
    private TableColumn<User, String> TC_email;

    @FXML
    private TableColumn<User, String> TC_nome;

    @FXML
    private TableColumn<User, User> tableColumnEDIT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNodes();
        loader = Loader.getLoaderInstance();
    }

    private void initializeNodes() {
        TC_cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        TC_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        TC_nome.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void updateTableView() {

        if (userService == null) {
            throw new IllegalStateException("Service was null");
        }

        List<User> listUser = userService.findAll();
        obsList = FXCollections.observableArrayList(listUser);
        TBV_user.setItems(obsList);
        this.initEditButtons();
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

    public void setCarService(UserService userService) {
        this.userService = userService;
    }

    private void initEditButtons() {
        tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnEDIT.setCellFactory(param -> new TableCell<User, User>() {
            private final Button button = new Button("Detalhes");

            @Override
            protected void updateItem(User obj, boolean empty) {
                super.updateItem(obj, empty);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> setFieldsDetails(obj));
                button.setId("my-button");
                button.getStylesheets()
                        .add(Objects.requireNonNull(getClass()
                                .getResource("../gui_ini/ButtonStyles.css"))
                                .toExternalForm());
            }
        });
    }

    private void setFieldsDetails(User user){
        TXT_cpf.setText(user.getCpf());
        TXT_email.setText(user.getEmail());
        TXT_nacionalidade.setText(user.getNacionalidade());
        TXT_nome.setText(user.getName());
        TXT_telefone.setText(user.getPhone());
    }
}
