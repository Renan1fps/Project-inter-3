package gui_user;


import gui_adm.ListUserController;
import gui_cadastros.CadastroCarroController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.Car;
import model.entities.Location;
import model.services.CarService;
import model.services.LocationService;
import model.services.UnitService;
import model.services.UserService;
import state.AuthState;
import utils.Loader;

import java.net.URL;
import java.util.*;

public class LocaUsuController implements Initializable {

    private Loader loader;
    private LocationService locationService;
    private CarService carService;

    @FXML
    private Button BTN_cadastroCarros;

    @FXML
    private Button BTN_usuarios;

    @FXML
    private TableColumn<Location, Date> TC_dataAlocacao;

    @FXML
    private TableColumn<Location, Date> TC_dataDevolucao;

    @FXML
    private TableColumn<Location, Integer> TC_diasAlugado;

    @FXML
    private TableColumn<Location, Double> TC_valor;

    @FXML
    private TextField Txb_Modelo;

    @FXML
    private TextField Txb_ano;

    @FXML
    private TextField Txb_cor;

    @FXML
    private TextField Txb_placa;

    @FXML
    private TextField Txb_unidade;

    @FXML
    private TextField Txb_valor;

    @FXML
    private TableColumn<Location, Location> tableColumnMore;

    @FXML
    private TableView<Location> tableViewLocation;

    @FXML
    private ImageView imageView = new ImageView();

    private ObservableList<Location> obsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeNodes();
        this.loader = Loader.getLoaderInstance();
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
    public void handleClickPostCar() {
        loader.loadView("../gui_cadastros/cadCar.fxml", (CadastroCarroController cadastroCarroController) -> {
            cadastroCarroController.setService(new CarService(), new UnitService());
            cadastroCarroController.updateView();

        });
    }

    @FXML
    public void handleClickUser() {
        loader.loadView("../gui_adm/listUsu.fxml", (ListUserController listUserController) -> {
            listUserController.setCarService(new UserService());
            listUserController.updateTableView();
        });
    }

    private void initializeNodes() {
        TC_dataAlocacao.setCellValueFactory(new PropertyValueFactory<>("dateOut"));
        TC_dataDevolucao.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        TC_diasAlugado.setCellValueFactory(new PropertyValueFactory<>("quantityDays"));
        TC_valor.setCellValueFactory(new PropertyValueFactory<>("finalValue"));
    }

    public void updateTableView() {
        if (locationService == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Location> listLocations = locationService.findCondition(AuthState.getUserLogged().getId());

        obsList = FXCollections.observableArrayList(listLocations);
        tableViewLocation.setItems(obsList);

        initEditButtons();
    }

    private void initEditButtons() {
        tableColumnMore.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnMore.setCellFactory(param -> new TableCell<Location, Location>() {
            private final Button button = new Button("Detalhes");

            @Override
            protected void updateItem(Location obj, boolean empty) {
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

    private void setFieldsDetails(Location location) {

        Car car = this.carService.finOne(location.getIdCar());

        Txb_Modelo.setText(car.getModelo());
        Txb_ano.setText(String.valueOf(car.getAno()));
        Txb_cor.setText(car.getCor());
        Txb_placa.setText(car.getPlaca());
        Txb_valor.setText(String.valueOf(car.getValor()));
        Txb_unidade.setText(car.getUnit().getName());
        Image image = new Image(car.getImageUrl());
        imageView.setImage(image);
    }

    public void setService(LocationService locationService, CarService carService) {
        this.locationService = locationService;
        this.carService = carService;
    }

}
