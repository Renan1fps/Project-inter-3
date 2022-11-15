package gui_ini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.services.CarService;
import utils.Loader;

import java.net.URL;
import java.util.ResourceBundle;

public class ServicosController implements Initializable {

    private Loader loader;

    @FXML
    public void handleClickBasic() {
        loader.loadView("/gui_ini/menuPrin.fxml", (MenuController menuController) -> {
            menuController.setService(new CarService());
            menuController.updateTableView(true);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader = new Loader();
    }


}
