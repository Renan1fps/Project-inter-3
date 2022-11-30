package gui_cadastros;

import application.Main;
import gui_adm.ListUserController;
import gui_ini.MainController;
import gui_ini.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.entities.Car;
import model.entities.Unit;
import model.services.CarService;
import model.services.UnitService;
import model.services.UserService;
import utils.Loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroCarroController implements Initializable {

    private CarService carService;
    private UnitService unitService;
    private Loader loader;

    @FXML
    private ImageView imageView = new ImageView();

    @FXML
    private Button BTN_choiceImage;

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
    private CheckBox Cb_arcondicionado;

    @FXML
    private TextField TXT_ano;

    @FXML
    private TextField TXT_cor;

    @FXML
    private TextField TXT_modelo;

    @FXML
    private TextField TXT_palaca;

    @FXML
    private TextField TXT_valor;

    @FXML
    private ChoiceBox<String> CbUnidades;

    private String imageURL;


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
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);
        imageView.getImage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    private Car generateCar() {
        Car carCreate = new Car();
        carCreate.setModelo(TXT_modelo.getText());
        carCreate.setAno(Integer.parseInt(TXT_ano.getText()));
        carCreate.setCor(TXT_cor.getText());
        carCreate.setPlaca(TXT_palaca.getText());
        carCreate.setImageUrl(this.imageURL);
        carCreate.setValor((int) Double.parseDouble(TXT_valor.getText()));

        carCreate.setArCondicionado(Cb_arcondicionado.isSelected());
        carCreate.setCambioAutomatico(CB_cambioAutomatico.isSelected());
        carCreate.setDirecaoHidrauliaca(CB_direcaoHidraulica.isSelected());
        carCreate.setFreioAbs(CB_freioABS.isSelected());
        carCreate.setPortaMalaGrande(CB_portaMalaGrande.isSelected());
        carCreate.setPremium(CB_premium.isSelected());
        carCreate.setQuatroPortas(CB_quatroPortas.isSelected());
        carCreate.setVidroEletrico(CB_vidroEletrico.isSelected());
        return carCreate;
    }

    public void setService(CarService carService, UnitService unitService) {
        this.carService = carService;
        this.unitService = unitService;
    }

    public void updateView() {
        if (carService == null || unitService == null) {
            throw new IllegalStateException("Service was null");
        }

        List<Unit> listUnit = unitService.findAll();
        List<String> unitNames = new ArrayList<>();
        listUnit.forEach(item -> unitNames.add(item.getName()));
        System.out.println("Passou aqui pae");
        ObservableList<String> obsListUnit = FXCollections.observableArrayList(unitNames);
        CbUnidades.setItems(obsListUnit);
    }

    @FXML
    private void handleCreateNewCar() {
        Car car = this.generateCar();
        carService.insert(car);
        loader.loadView("../gui_ini/inicio.fxml", (MainController mainController) -> {
            mainController.showMessage(
                    "Carro criado",
                    "Cadastro realizado",
                    "Carro cadastrado com sucesso",
                    Alert.AlertType.CONFIRMATION
            );
        });
    }
}

