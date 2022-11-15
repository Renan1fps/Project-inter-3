package gui_ini;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import model.services.CarService;
import utils.Loader;

public class MainController implements Initializable {

	private Loader loader;

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void handleClickNavigate() {
		loader.loadView("/gui_ini/menuPrin.fxml", (MenuController menuController) -> {
			menuController.setService(new CarService());
			menuController.updateTableView(false);
		});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		loader = new Loader();
	}

}
