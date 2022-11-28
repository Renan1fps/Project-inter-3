package gui_ini;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


import gui_adm.ListUserController;
import gui_user.LocarVeiController;
import gui_user.PerUsuController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Car;
import model.entities.Unit;
import model.services.CarService;
import model.services.UnitService;
import model.services.UserService;
import state.AuthState;
import utils.Alerts;
import utils.Loader;

public class MenuController implements Initializable {

    private CarService carService;
    private UnitService unitService;
    private Loader loader;
    @FXML
    private TableView<Car> tableViewCar;

    @FXML
    private TableColumn<Car, Integer> tableColumnId;

    @FXML
    private TableColumn<Car, String> tableColumnCor;

    @FXML
    private TableColumn<Car, String> tableColumnModelo;

    @FXML
    private TableColumn<Car, Integer> tableColumnAno;

    @FXML
    private TableColumn<Car, String> tableColumnPlaca;

    @FXML
    private TableColumn<Car, Double> tableColumnValor;

    @FXML
    private TableColumn<Car, Car> tableColumnEDIT;

    @FXML
    private CheckBox ArCondicionado;

    @FXML
    private CheckBox DirecaoHidraulica;

    @FXML
    private CheckBox FreioABS;

    @FXML
    private CheckBox PortaMalaGrande;

    @FXML
    private CheckBox Premium;

    @FXML
    private CheckBox QuatroPortas;

    @FXML
    private CheckBox cambioAutomatico;

    @FXML
    private CheckBox vidroEletrico;

    @FXML
    private Button BTN_usuarios;

    @FXML
    private ChoiceBox<String> CbUnidades;

    private ObservableList<Car> obsList;

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
    public void handleClickServices() {
        loader.loadView("/gui_ini/servicos.fxml", (x -> {
        }));
    }

    @FXML
    public void handleClickUserProfile() {
        loader.loadView("../gui_user/perUsu.fxml", (PerUsuController perUsuController) -> {
            perUsuController.setService(new UserService());
            perUsuController.updateView();
        });
    }

    @FXML
    public void handleClickUser() {
        loader.loadView("../gui_adm/listUsu.fxml", (ListUserController listUserController)-> {
            listUserController.setCarService(new UserService());
            listUserController.updateTableView();
        });
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.hiddenButtons();
        initializeNodes();
        loader = Loader.getLoaderInstance();

    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableColumnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tableColumnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    public void updateTableView(boolean tipoPremium) {

        if (carService == null || unitService == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Car> listCar = carService.findAll(tipoPremium);
        List<Unit> listUnit = unitService.findAll();

        obsList = FXCollections.observableArrayList(listCar);
        tableViewCar.setItems(obsList);

        List<String> unitNames = new ArrayList<>();
        listUnit.forEach(item -> unitNames.add(item.getName()));

        ObservableList<String> obsListUnit = FXCollections.observableArrayList(unitNames);
        initEditButtons();
        CbUnidades.setItems(obsListUnit);
    }

    @FXML
    public void findCondition() {
        if (carService == null) {
            throw new IllegalStateException("Service was null");
        }

        Car carroComFiltro = new Car();
        if (ArCondicionado.isSelected()) {
            carroComFiltro.setArCondicionado(true);
        }

        if (DirecaoHidraulica.isSelected()) {
            carroComFiltro.setDirecaoHidrauliaca(true);
        }

        if (FreioABS.isSelected()) {
            carroComFiltro.setFreioAbs(true);
        }

        if (PortaMalaGrande.isSelected()) {
            carroComFiltro.setPortaMalaGrande(true);
        }

        if (Premium.isSelected()) {
            carroComFiltro.setPremium(true);
        }

        if (QuatroPortas.isSelected()) {
            carroComFiltro.setQuatroPortas(true);
        }

        if (cambioAutomatico.isSelected()) {
            carroComFiltro.setCambioAutomatico(true);
        }

        if (vidroEletrico.isSelected()) {
            carroComFiltro.setVidroEletrico(true);
        }

        if (CbUnidades.getValue() == null) {
            Alerts.showAlert("Unidade não selecionada", "Unidade", "Por favor, selecione uma unidade",
                    Alert.AlertType.WARNING);
            return;
        }

        Unit unit = new Unit();
        unit.setName(CbUnidades.getValue());
        carroComFiltro.setUnit(unit);

        List<Car> list = carService.findCondition(carroComFiltro);
        obsList = FXCollections.observableArrayList(list);
        tableViewCar.setItems(obsList);
    }


    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    @FXML
    public void handleClickLocations() {
        Boolean isUserAuth = AuthState.isAuthenticated();

        if (!isUserAuth) {
            loader.loadView("/gui_ini/login.fxml", (LoginController loginController) -> {
                loginController.setService(new UserService());
            });
        } else {
            loader.loadView("../gui_user/LocaUsu.fxml", (x -> {
            }));
        }
    }

    private void initEditButtons() {
        tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnEDIT.setCellFactory(param -> new TableCell<Car, Car>() {
            private final Button button = new Button("Detalhes");


            @Override
            protected void updateItem(Car obj, boolean empty) {
                super.updateItem(obj, empty);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> loader.loadView("../gui_user/locarVei.fxml", (LocarVeiController locarVeiController) -> {
                            locarVeiController.setService(new CarService());
                            locarVeiController.updateView(obj);
                        }));
                button.setId("my-button");
                button.getStylesheets().add(Objects.requireNonNull(getClass().getResource("ButtonStyles.css")).toExternalForm());
            }
        });
    }

    private void hiddenButtons() {
        if (!AuthState.isAuthenticated() || !AuthState.getUserLogged().isIs_adm()) {
            BTN_usuarios.setVisible(false);
        }
    }

    public void showSuccessMessage(String message, String title, String action){
        Alerts.showAlert(title, action, message,
                Alert.AlertType.CONFIRMATION);
    }
}
