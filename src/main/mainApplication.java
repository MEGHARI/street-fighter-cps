package main;



import enums.COMMAND;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import services.CharacterService;
import services.EngineService;
import services.HitboxService;
import services.PlayerService;

public class mainApplication extends Application {
	
	private int frame = 60; 
 	private COMMAND c1 ;
 	private COMMAND c2 ;
 	private EngineService engine;
 	private PlayerService p1,p2;
 	private CharacterService fighter1,fighter2;
 	private HitboxService hirFighter1,hitFighter2;
 	private ProgressBar vieJoueur1,vieJoueur2;
 	private Rectangle joueur1,joueur2;
	@Override
	public void start(Stage primaryStage) throws Exception {
         
		//Parent loader = FXMLLoader.load((getClass().getResource("/fxml/partie.fxml")));
		// Initialisation
		
		// joueur 1
		vieJoueur1 = new ProgressBar(100);
		vieJoueur1.setLayoutX(432);
		vieJoueur1.setLayoutY(27);
		vieJoueur1.prefHeight(20);
		vieJoueur1.prefHeight(168);
		joueur1 = new Rectangle();
		joueur1.setArcHeight(5.0);
		joueur1.setArcWidth(5.0);
		joueur1.setFill(Paint.valueOf("#ff1f1f"));
		joueur1.setHeight(172.0);
		joueur1.setLayoutX(170.0);
		joueur1.setLayoutY(170.0);
		joueur1.setStroke(Color.BLACK);
		joueur1.setStrokeType(StrokeType.INSIDE);
		joueur1.setWidth(63.0);
		joueur1.setOnKeyPressed(e->{System.out.println("player1");});
		//joueur1.setOnKeyPressed(e->{System.out.println("player2");});
		
		// joueur 2
		vieJoueur2 = new ProgressBar(100);
		vieJoueur2.setLayoutX(65);
		vieJoueur2.setLayoutY(27);
		vieJoueur2.prefHeight(20);
		vieJoueur2.prefHeight(168);
		joueur2 = new Rectangle();
		joueur2.setArcHeight(5.0);
		joueur2.setArcWidth(5.0);
		joueur2.setFill(Paint.valueOf("#1c1d1e"));
		joueur2.setHeight(172.0);
		joueur2.setLayoutX(305.0);
		joueur2.setLayoutY(172.0);
		joueur2.setStroke(Color.BLUE);
		joueur2.setStrokeType(StrokeType.INSIDE);
		joueur2.setWidth(63.0);
		
		AnchorPane anchore = new AnchorPane(vieJoueur1,vieJoueur2,joueur1,joueur2);
		anchore.setId("anchore");
		
         Scene scene = new Scene(anchore,659,340);
         scene.getStylesheets().add("/css/main.css");
         scene.setOnKeyPressed(e->{});
         scene.setOnKeyReleased(e->{});
         
         primaryStage.setTitle("street-fighter");
         primaryStage.setResizable(false);
         primaryStage.setScene(scene);
         primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		       @Override
		       public void handle(WindowEvent e) {
		          Platform.exit();
		          System.exit(0);
		       }
		    });
         primaryStage.show();
         Timeline timerThread = new Timeline(keyFrame);
         timerThread.setCycleCount(Timeline.INDEFINITE);
         timerThread.play();
         
	}
	
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000 ), e -> {
		//engine.step(c1, c2);
		//System.out.println("mouloud");
		joueur1.setLayoutX(joueur1.getLayoutX() - 1);
		
    });
	
	public static void main(String...args) {
		launch(args);
	}
}
