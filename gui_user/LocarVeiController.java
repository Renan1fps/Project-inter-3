package gui_user;

import application.Main;
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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LocarVeiController implements Initializable {

	private CarService carService;
	private Loader loader;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		loader = new Loader();

	}

	private void initializeNodes() {
//		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
//		tableColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
//		tableColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
//
//		Stage stage = (Stage) Main.getMainScene().getWindow();
//		tableViewCar.prefHeightProperty().bind(stage.heightProperty());
//		 Car carro =  tableViewCar.getSelectionModel().getSelectedItem();
//		if(carro != null){
//			loader.loadView("/gui_ini/locarVei.fxml", (MenuController menuController);
//		}
	}

	public void getCar() {
		if (carService == null) {
			throw new IllegalStateException("Service was null");
		}
		System.out.println("Aqui");
//		Car car = carService.f;
//		obsList = FXCollections.observableArrayList(list);
//		tableViewCar.setItems(obsList);
	}




	public void setService(CarService carService) {
		this.carService = carService;
	}

}
