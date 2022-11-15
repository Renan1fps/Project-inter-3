package gui_ini;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui_user.LocarVeiController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Car;
import model.services.CarService;
import utils.Loader;

public class MenuController implements Initializable {

    private CarService carService;
    private Loader loader;
    @FXML
    private TableView<Car> tableViewCar;

    @FXML
    private TableColumn<Car, Integer> tableColumnId;

    @FXML
    private TableColumn<Car, String> tableColumnMarca;

    @FXML
    private TableColumn<Car, String> tableColumnModelo;

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
        tableColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        //tableViewCar.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView(boolean tipoPremium) {
        if (carService == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Car> list = carService.findAll(tipoPremium);
        obsList = FXCollections.observableArrayList(list);
        tableViewCar.setItems(obsList);
    }


    public void setService(CarService carService) {
        this.carService = carService;
    }

}