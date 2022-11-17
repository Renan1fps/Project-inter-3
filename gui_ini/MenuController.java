package gui_ini;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Car;
import model.services.CarService;
import model.services.UserService;
import utils.Loader;

public class MenuController implements Initializable {

    private CarService carService;
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
    private TableColumn<Car, Boolean> tableColumnVidroEletrico;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initializeNodes();
        loader = new Loader();

    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableColumnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tableColumnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableColumnVidroEletrico.setCellValueFactory(new PropertyValueFactory<>("vidroEletrico"));
    }

    public void updateTableView(boolean tipoPremium) {
        if (carService == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Car> list = carService.findAll(tipoPremium);
        obsList = FXCollections.observableArrayList(list);
        tableViewCar.setItems(obsList);
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

        List<Car> list = carService.findCondition(carroComFiltro);
        obsList = FXCollections.observableArrayList(list);
        tableViewCar.setItems(obsList);
    }


    public void setService(CarService carService) {
        this.carService = carService;
    }

    @FXML
    public void handleClickLocations() {
        Boolean estaAutenticado = Main.estaAutenticado();

        if (!estaAutenticado) {
            loader.loadView("/gui_ini/login.fxml", (LoginController loginController) -> {
                loginController.setService(new UserService());
            });
        } else {
            loader.loadView("../gui_user/LocaUsu.fxml", (x -> {
            }));
        }
    }
}
