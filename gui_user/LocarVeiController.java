package gui_user;

import gui_adm.ListUserController;
import gui_ini.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.Car;
import model.services.CarService;
import model.services.LocationService;
import model.services.UnitService;
import model.services.UserService;
import state.AuthState;
import utils.Loader;

import java.net.URL;

import java.util.ResourceBundle;

public class LocarVeiController implements Initializable {

    private CarService carService;
    private Loader loader;
    private int idCar;
    private Car car;

    @FXML
    private CheckBox CB_arCondicionado;

    @FXML
    private CheckBox CB_cambioAutomatico;

    @FXML
    private CheckBox CB_direcaoHidraulica;

    @FXML
    private CheckBox CB_freioABS;

    @FXML
    private CheckBox CB_portaMalaGrande;

    @FXML
    private CheckBox CB_premium;

    @FXML
    private CheckBox CB_quatroPortas;

    @FXML
    private CheckBox CB_vidroEletrico;

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
    private Button BTN_actionFinal;

    @FXML
    private Button BTN_usuarios;

    @FXML
    private ImageView imageView = new ImageView();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.hiddenButtons();
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
        if (AuthState.isAuthenticated() && AuthState.getUserLogged().isIs_adm()) {
            this.updateCar(this.idCar);
        } else {
            loader.loadView("../gui_user/AlugarCarro.fxml", (AlugarCarroController alugarCarro) -> {
                alugarCarro.setService(new CarService(), new LocationService());
                alugarCarro.setCar(this.car);
                alugarCarro.updateView(this.car);
            });
        }
    }

    public void updateView(Car car) {
        this.setFieldsText(car);
        this.setCheckBox(car);

        this.idCar = car.getId();
        this.car = car;

        if (!AuthState.isAuthenticated() || !AuthState.getUserLogged().isIs_adm()) {
            this.disableFields();
        } else {
            BTN_actionFinal.setText("Atualizar");
        }
    }

    private void setFieldsText(Car car) {
        Image image = new Image(car.getImageUrl());
        imageView.setImage(image);
        imageView.setVisible(true);
        imageView.setCache(true);
        imageView.isVisible();
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);
        imageView.getImage();

        Txb_Modelo.setText(car.getModelo());
        Txb_ano.setText(String.valueOf(car.getAno()));
        Txb_cor.setText(car.getCor());
        Txb_placa.setText(car.getPlaca());
        Txb_valor.setText(String.valueOf(car.getValor()));
        Txb_unidade.setText(car.getUnit().getName());

    }

    private void disableFields() {

        Txb_Modelo.setDisable(true);
        Txb_ano.setDisable(true);
        Txb_cor.setDisable(true);
        Txb_placa.setDisable(true);
        Txb_valor.setDisable(true);
        Txb_unidade.setDisable(true);

        CB_arCondicionado.setDisable(true);
        CB_cambioAutomatico.setDisable(true);
        CB_direcaoHidraulica.setDisable(true);
        CB_freioABS.setDisable(true);
        CB_portaMalaGrande.setDisable(true);
        CB_premium.setDisable(true);
        CB_quatroPortas.setDisable(true);
        CB_vidroEletrico.setDisable(true);

        BTN_actionFinal.setText("Alugar agora");
    }

    private void setCheckBox(Car car) {
        CB_arCondicionado.setSelected(car.getArCondicionado());
        CB_cambioAutomatico.setSelected(car.getCambioAutomatico());
        CB_direcaoHidraulica.setSelected(car.getDirecaoHidrauliaca());
        CB_freioABS.setSelected(car.getFreioAbs());
        CB_portaMalaGrande.setSelected(car.getPortaMalaGrande());
        CB_premium.setSelected(car.getPremium());
        CB_quatroPortas.setSelected(car.getQuatroPortas());
        CB_vidroEletrico.setSelected(car.getVidroEletrico());
    }

    private void updateCar(int id) {
        if (carService == null) {
            throw new IllegalStateException("Service was null");
        }

        Car carToUpdate = this.generateCar(id);
        this.carService.update(carToUpdate);
        loader.loadView("/gui_ini/tabelasCarros.fxml", (MenuController menuController) -> {
            menuController.setCarService(new CarService());
            menuController.setUnitService(new UnitService());
            menuController.updateTableView(true);
            menuController.showMessage("Carro atualizado com sucesso", "Carro atualizado", "Atualização", Alert.AlertType.CONFIRMATION);
        });
    }

    private Car generateCar(int id) {
        String modelo = Txb_Modelo.getText();
        int ano = Integer.parseInt(Txb_ano.getText());
        String cor = Txb_cor.getText();
        String placa = Txb_placa.getText();
        int valor = (int) Double.parseDouble(Txb_valor.getText());

        boolean arCondicionado = CB_arCondicionado.isSelected();
        boolean cambioAutomatico = CB_cambioAutomatico.isSelected();
        boolean direcaoHidraulica = CB_direcaoHidraulica.isSelected();
        boolean freioABS = CB_freioABS.isSelected();
        boolean portaMalaGrande = CB_portaMalaGrande.isSelected();
        boolean premium = CB_premium.isSelected();
        boolean quatroPortas = CB_quatroPortas.isSelected();
        boolean vidroEletrico = CB_vidroEletrico.isSelected();

        return new Car(id, modelo, ano, cor, placa, valor, vidroEletrico, cambioAutomatico, arCondicionado, freioABS, quatroPortas, direcaoHidraulica, portaMalaGrande, premium);
    }

    public void setService(CarService carService, LocationService locationService) {
        this.carService = carService;
    }


    private void hiddenButtons() {
        if (!AuthState.isAuthenticated() || !AuthState.getUserLogged().isIs_adm()) {
            BTN_usuarios.setVisible(false);
        }
    }

}
