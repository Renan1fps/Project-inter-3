package gui_ini;

import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.ValidationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.entities.User;
import model.services.UserService;

public class LoginController implements Initializable {

	private UserService userService = new UserService();

	@FXML
	private TextField TXT_email;

	@FXML
	private TextField TXT_senha;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
	void handleLogin(ActionEvent event) {
		ValidationException exception = new ValidationException("Validation error");
		String email = TXT_email.getText().trim();
		User user = this.userService.findByEmail(email);
		
		if(user == null) {
			try {
				throw exception;
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro ->" + e.getMessage());
			}
		}
		
		System.out.println(user);
	}

}
