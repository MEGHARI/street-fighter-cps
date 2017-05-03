package main;

import contracts.EngineContract;
import contracts.FightCharContract;
import contracts.PlayerContract;
import contracts.RectangleHitboxContract;
import enums.COMMAND;
import enums.NAME;
import impl.EngineImpl;
import impl.FightCharImpl;
import impl.PlayerImpl;
import impl.RectangleHitboxImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import main.MultiplePressedKeysEventHandler.MultiKeyEvent;
import main.MultiplePressedKeysEventHandler.MultiKeyEventHandler;

public class mainApplication extends Application {

	private Pane paneJoueur1, paneJoueur2;
	private int frameRate = 60;
	private EngineContract engine;
	private PlayerContract p1, p2;
	private FightCharContract fighter1, fighter2;
	private RectangleHitboxContract hitFighter1, hitFighter2, hitTEch1, HitTech2;
	private ProgressBar vieJoueur1, vieJoueur2;
	private Rectangle joueur1, joueur2, rectTech1, rectTech2;
	private COMMAND commandPlayer1, commandPlayer2;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Initialisation

		hitFighter1 = new RectangleHitboxContract(new RectangleHitboxImpl());
		hitFighter1.init(0, 0, 69, 172);
		hitFighter2 = new RectangleHitboxContract(new RectangleHitboxImpl());
		hitFighter2.init(0, 0, 69, 172);
		p1 = new PlayerContract(new PlayerImpl());
		p2 = new PlayerContract(new PlayerImpl());
		fighter1 = new FightCharContract(new FightCharImpl());
		fighter2 = new FightCharContract(new FightCharImpl());
		engine = new EngineContract(new EngineImpl());
		fighter1.init(NAME.RY, 100, 5, true, engine, hitFighter1);
		fighter2.init(NAME.BISON, 100, 5, false, engine, hitFighter2);
		p1.init(1);
		p2.init(2);
		p1.setCharacter(fighter1);
		p2.setCharacter(fighter2);
		engine.init(660, 340, 200, p1, p2);
		commandPlayer1 = COMMAND.NEUTRAL;
		commandPlayer2 = COMMAND.NEUTRAL;

		vieJoueur1 = new ProgressBar(1.0);
		vieJoueur1.setLayoutX(65);
		vieJoueur1.setLayoutY(27);
		vieJoueur1.prefHeight(20);
		vieJoueur1.prefWidth(168);
		
		
		rectTech1 = new Rectangle();
		rectTech1.setFill(Color.RED);
		rectTech1.setHeight(50.0);
		rectTech1.setWidth(130);
		rectTech1.setLayoutY(170);
		rectTech1.setVisible(false);
		
		joueur1 = new Rectangle();
		joueur1.setFill(Color.RED);
		joueur1.setHeight(171);
		joueur1.setLayoutX(fighter1.getPositionX());
		joueur1.setLayoutY(169);
		joueur1.setStroke(Color.BLACK);
		joueur1.setStrokeType(StrokeType.INSIDE);
		joueur1.setWidth(hitFighter1.getWidth());



		vieJoueur2 = new ProgressBar(fighter2.getLife());
		vieJoueur2.setLayoutX(432);
		vieJoueur2.setLayoutY(27);
		vieJoueur2.prefHeight(20);
		vieJoueur2.prefWidth(168);

		rectTech2 = new Rectangle();
		rectTech2.setFill(Color.BLUE);
		rectTech2.setHeight(50.0);
		rectTech2.setWidth(130);
		rectTech2.setLayoutY(170);
		rectTech2.setVisible(false);

		joueur2 = new Rectangle();
		joueur2.setFill(Color.BLUE);
		joueur2.setLayoutX(fighter2.getPositionX());
		joueur2.setLayoutY(169);
		joueur2.setHeight(hitFighter2.getHeight());
		joueur2.setStroke(Color.BLUE);
		joueur2.setStrokeType(StrokeType.INSIDE);
		joueur2.setWidth(hitFighter2.getWidth());
	

		AnchorPane anchore = new AnchorPane(vieJoueur1, vieJoueur2,rectTech1,joueur1,rectTech2, joueur2);
		anchore.setId("anchore");

		Scene scene = new Scene(anchore, engine.getWidth(), engine.getHeight());

		scene.getStylesheets().add("/css/main.css");
		MultiplePressedKeysEventHandler keyHandler = new MultiplePressedKeysEventHandler(new MultiKeyEventHandler() {
			@Override
			public void handle(MultiKeyEvent event) {
				/** player2 **/
				if (event.isPressed(KeyCode.UP) && event.isPressed(KeyCode.NUMPAD1)) {
					commandPlayer2 = COMMAND.JUMP_TECH_1;
				} else if (event.isPressed(KeyCode.UP) && event.isPressed(KeyCode.NUMPAD2)) {
					commandPlayer2 = COMMAND.JUMP_TECH_2;
				} else if (event.isPressed(KeyCode.DOWN) && event.isPressed(KeyCode.NUMPAD1)) {
					commandPlayer2 = COMMAND.CROUCH_TECH_1;
				} else if (event.isPressed(KeyCode.DOWN) && event.isPressed(KeyCode.NUMPAD2)) {
					commandPlayer2 = COMMAND.JUMP_TECH_2;
				} else if (event.isPressed(KeyCode.UP)) {
					commandPlayer2 = COMMAND.JUMP;
				} else if (event.isPressed(KeyCode.LEFT)) {
					commandPlayer2 = COMMAND.LEFT;
				} else if (event.isPressed(KeyCode.RIGHT)) {
					commandPlayer2 = COMMAND.RIGHT;
				} else if (event.isPressed(KeyCode.DOWN)) {
					commandPlayer2 = COMMAND.CROUCH;
				} else if (event.isPressed(KeyCode.NUMPAD1)) {
					commandPlayer2 = COMMAND.TECH_1;
				} else if (event.isPressed(KeyCode.NUMPAD2)) {
					commandPlayer2 = COMMAND.TECH_2;
				} else if (event.isPressed(KeyCode.NUMPAD3)) {
					commandPlayer2 = COMMAND.PROTECT;
				}

				/** player1 **/
				else if (event.isPressed(KeyCode.Z) && event.isPressed(KeyCode.E)) {
					commandPlayer1 = COMMAND.JUMP_TECH_1;
				} else if (event.isPressed(KeyCode.Z) && event.isPressed(KeyCode.R)) {
					commandPlayer1 = COMMAND.JUMP_TECH_2;
				} else if (event.isPressed(KeyCode.S) && event.isPressed(KeyCode.E)) {
					commandPlayer1 = COMMAND.CROUCH_TECH_1;
				} else if (event.isPressed(KeyCode.S) && event.isPressed(KeyCode.R)) {
					commandPlayer1 = COMMAND.CROUCH_TECH_2;
				} else if (event.isPressed(KeyCode.Z)) {
					commandPlayer1 = COMMAND.JUMP;
				} else if (event.isPressed(KeyCode.D)) {
					commandPlayer1 = COMMAND.RIGHT;
				} else if (event.isPressed(KeyCode.Q)) {
					commandPlayer1 = COMMAND.LEFT;
				} else if (event.isPressed(KeyCode.S)) {
					commandPlayer1 = COMMAND.CROUCH;
				} else if (event.isPressed(KeyCode.E)) {
					commandPlayer1 = COMMAND.TECH_1;
				} else if (event.isPressed(KeyCode.R)) {
					commandPlayer1 = COMMAND.TECH_2;
				} else if (event.isPressed(KeyCode.A)) {
					commandPlayer1 = COMMAND.PROTECT;
				}
			}
		});
		scene.setOnKeyPressed(keyHandler);
		scene.setOnKeyReleased(keyHandler);
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

	KeyFrame keyFrame = new KeyFrame(Duration.millis(2000 ), e -> {
		if (fighter1.isTeching() && !fighter1.isBlockstunned() && !fighter1.isHitstunned()) {
			if (fighter1.getTechFrame() >= fighter1.getTech().getSframe()
					&& fighter1.getTechFrame() < fighter1.getTech().getSframe() + fighter1.getTech().getHframe())
	
				rectTech1.setVisible(true);
			else
				rectTech1.setVisible(false);
		} else if (fighter2.isTeching() && !fighter2.isBlockstunned() && !fighter2.isHitstunned()) {
			if (fighter2.getTechFrame() >= fighter2.getTech().getSframe()
					&& fighter2.getTechFrame() < fighter2.getTech().getSframe() + fighter2.getTech().getHframe())
				rectTech2.setVisible(true);
			else
				rectTech2.setVisible(false);
		}		
		
		engine.step(commandPlayer1, commandPlayer2);
		//updateGames();
		vieJoueur1.setProgress(vieJoueur1.getProgress()-0.2);
		commandPlayer1 = COMMAND.NEUTRAL;
		commandPlayer2 = COMMAND.NEUTRAL;

	});

	public  Pane crouch(Pane p) {
		if (p.getTransforms().isEmpty()) {
			Scale scale = new Scale();
			// Setting the dimensions for the transformation
			scale.setY(0.5);
			// Setting the pivot point for the transformation
			scale.setPivotX(0);
			scale.setPivotY(p.getHeight());
			p.getTransforms().add(scale);
		}
		return p;
	}

	public  Pane rize(Pane p) {
		p.getTransforms().clear();
		return p;
	}
	
	public void  updateGames() {
		joueur1.setLayoutX(fighter1.getPositionX());
		joueur2.setLayoutX(fighter2.getPositionX());
		//vieJoueur1.setProgress(vieJoueur1.getProgress()-i);
		//vieJoueur2.setProgress(fighter2.getLife());
		rectTech1.setLayoutX(fighter1.getPositionX()+fighter1.getCharBox().getWidth());
		rectTech2.setLayoutX(fighter2.getPositionX()-rectTech2.getWidth());
	}

	public static void main(String... args) {
		launch(args);
	}
}
