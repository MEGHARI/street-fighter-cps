package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import services.EngineService;

public class PartieController implements Initializable {
	
	private EngineService engine;
	@FXML
	private ProgressBar vieJoeur1,vieJoueur2;
	@FXML
	private ImageView joueur1,joueur2;
	@FXML
	private AnchorPane scene;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}
	
	@FXML
	public void onKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
		// player 2
		case UP:
			break;
		case DOWN:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		case NUMPAD1:
			break;
		case NUMPAD2:
			break;
		case NUMPAD3:
			break;
			
			// player 1
		case A:
			break;
		case Z:
			break;
		case E:
			break;
		case S:
			break;
		case F:
			break;
		case G:
			break;
		case H:
			break;
			
		default:
			break;
		}
	}
	
	@FXML
	public void onKeyReleased(KeyEvent event) {
		
	}

}
