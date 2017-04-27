package main;

import java.net.URL;
import java.util.ResourceBundle;

import enums.COMMAND;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import services.EngineService;

public class PartieController implements Initializable {
	
	private EngineService engine;

	@FXML
	private ProgressBar vieJoueur1,vieJoueur2;
	
	@FXML
	private Rectangle joueur1,joueur2;
	
	@FXML
	private AnchorPane scene;
	
	private int frame = 60; 
	private COMMAND c1 = null;
	private COMMAND c2 = null;
	String name = "salim";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
				/*Timeline timerThread = new Timeline(keyFrame);
        timerThread.setCycleCount(Timeline.INDEFINITE);
        timerThread.play();*/
		joueur1.setOnKeyPressed(e->{System.out.println("hahah");});
		

	}
	

	
	
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/frame ), e -> {
		//engine.step(c1, c2);
		System.out.println(name);
    });

     @FXML
     public void essai(MouseEvent event) {
    	 name ="ghiles";
     }
	
	

}
