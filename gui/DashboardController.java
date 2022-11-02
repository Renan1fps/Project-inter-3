package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.fxml.Initializable;

public class DashboardController implements Initializable {
	
	    @FXML
	    private Button back;

	    @FXML
	    private Button next;

	    @FXML
	    private AnchorPane pane1;

	    @FXML
	    private AnchorPane pane2;

	    @FXML
	    private AnchorPane pane3;
	    
	    
	    public void translationAnimation(double duration, Node node, double width) {
	    	TranslateTransition translate = new TranslateTransition(Duration.seconds(duration), node);
	    	translate.setByX(width);
	    	translate.play();
	    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		translationAnimation(0.5, pane2, 829);
		translationAnimation(0.5, pane3, 829);
	}
	
	int show = 0;
	@FXML
	void next(ActionEvent event) {
		System.out.println("VALOR NEXT " + show);
		if(show == 0) {
			System.out.println("Entrou aqui");
			translationAnimation(0.5, pane2,-829);
			show ++;
			
		}else if (show == 1) {
			System.out.println("Entrou aqueeei");
			translationAnimation(0.5, pane3,-829);
			show ++;
		}
		
	}
	
	@FXML
	void back(ActionEvent event) {
		System.out.println("VALOR BACK " + show);
		if(show == 1) {
			System.out.println("Entrou aqueeeeeeeeeeeeei");
			translationAnimation(0.5, pane2,-829);
			show --;
			
		}else if (show == 2) {
			System.out.println("Entrou aquiyyyyyyy");
			translationAnimation(0.5, pane3,-829);
			show --;
		}
		
	}
	

}
