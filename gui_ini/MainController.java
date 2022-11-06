package gui_ini;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import model.services.UserService;
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
	public void handleLogin() {
		loader.loadView("../gui_ini/login.fxml", (LoginController controller) -> {
			controller.setService(new UserService());
		});
	}

	@FXML
	public void onMenuItemAboutAction() {
		loader.loadView("/gui/About.fxml", x -> {
		});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		loader = new Loader();
	}
	
	

}
