package gui_user;

import gui_adm.ListUserController;
import gui_ini.LoginController;
import gui_ini.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Car;
import model.entities.Location;
import model.services.CarService;
import model.services.LocationService;
import model.services.UnitService;
import model.services.UserService;
import state.AuthState;
import utils.Loader;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class AlugarCarroController implements Initializable {

    private LocationService locationService;
    private Loader loader;
    private Car car;

    @FXML
    private Button BTN_actionFinal;

    @FXML
    private Button BTN_usuarios;

    @FXML
    private TextField TXT_quantidadeDias;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.loader = Loader.getLoaderInstance();
        this.hiddenButtons();
        TXT_quantidadeDias.textProperty().addListener((observable, oldValue, newValue) -> {
            Txb_valor.setText(Objects.equals(newValue, "") ?
                    TXT_quantidadeDias.getText() :
                    String.valueOf(calculateValue())
            );
        });
    }

    public Double calculateValue() {
        double valorFinal = Double.parseDouble(TXT_quantidadeDias.getText());
        double finalResult = 0.0;
        if (valorFinal > 0) {
            finalResult = valorFinal * 100;
        }
        return finalResult;
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

    @FXML
    public void handleClickUser() {
        loader.loadView("../gui_adm/listUsu.fxml", (ListUserController listUserController) -> {
            listUserController.setCarService(new UserService());
            listUserController.updateTableView();
        });
    }

    @FXML
    private void handleClickAction() {
        if (AuthState.isAuthenticated() && !AuthState.getUserLogged().isIs_adm()) {
            Calendar dateReturn = Calendar.getInstance();

            dateReturn.setTime(new Date());
            dateReturn.add(Calendar.DATE, Integer.parseInt(TXT_quantidadeDias.getText()));
            Double valorFinal = Double.parseDouble(TXT_quantidadeDias.getText()) * 100;

            Date date = new Date(dateReturn.getTimeInMillis());
            Location location = new Location();
            location.setIdCar(this.car.getId());
            location.setIdUser(AuthState.getUserLogged().getId());
            location.setIdUnit(1);
            location.setQuantityDays(Integer.parseInt(TXT_quantidadeDias.getText()));
            location.setDateOut(new Date());
            location.setReturnDate(date);
            location.setFinalValue(valorFinal);
            locationService.insert(location);
            loader.loadView("../gui_ini/inicio.fxml", (x -> {
            }));
        } else {
            loader.loadView("../gui_ini/login.fxml", (LoginController loginController) -> {
                loginController.setService(new UserService());
                loginController.showMessage(
                        "Necessário login",
                        "Login",
                        "Para alugar um carro é preciso identificar-se primiero",
                        Alert.AlertType.INFORMATION);
            });
        }
    }

    public void updateView(Car car) {
        Txb_ano.setText(String.valueOf(car.getAno()));
        Txb_placa.setText(car.getPlaca());
        Txb_cor.setText(car.getCor());
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setService(CarService carService, LocationService locationService) {
        this.locationService = locationService;
    }

    private void hiddenButtons() {
        if (!AuthState.isAuthenticated() || !AuthState.getUserLogged().isIs_adm()) {
            BTN_usuarios.setVisible(false);
        }
    }
}
