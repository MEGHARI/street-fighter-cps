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
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import main.MultiplePressedKeysEventHandler.MultiKeyEvent;
import main.MultiplePressedKeysEventHandler.MultiKeyEventHandler;
import sun.security.action.GetLongAction;

public class mainApplication extends Application {
	
	private Pane paneJoueur1, paneJoueur2;
	private int frameRate = 1;
	private EngineContract engine;
	private PlayerContract p1, p2;
	private FightCharContract fighter1, fighter2;
	private RectangleHitboxContract hitFighter1, hitFighter2,hitTEch1,HitTech2;
	private ProgressBar vieJoueur1, vieJoueur2;
	private Rectangle joueur1, joueur2,rectTech1,rectTech2;
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
		p1.init(2);
		p1.setCharacter(fighter1);
		p2.setCharacter(fighter2);
		engine.init(659, 340, 200, p1, p2);
		commandPlayer1 = COMMAND.NEUTRAL;
		commandPlayer2 = COMMAND.NEUTRAL;

		// joueur 1
		paneJoueur1 = new Pane();
		paneJoueur1.setPrefHeight(172.0);
		paneJoueur1.setPrefWidth(69.0);
		paneJoueur1.setLayoutX(engine.getChar(1).getPositionX());
		paneJoueur1.setTranslateY(169);

		vieJoueur1 = new ProgressBar(100);
		vieJoueur1.setLayoutX(339);
		vieJoueur1.setLayoutY(27);
		vieJoueur1.prefHeight(20);
		vieJoueur1.prefWidth(168);
		
		rectTech1 = new Rectangle();
		rectTech1.setFill(Color.RED);
		rectTech1.setHeight(50.0);
		rectTech1.setLayoutX(-99);
		rectTech1.setWidth(168);
		rectTech1.setVisible(false);
		
		joueur1 = new Rectangle();
		joueur1.setFill(Paint.valueOf("#ff1f1f"));
		joueur1.setHeight(hitFighter1.getHeight());
		joueur1.setStroke(Color.BLACK);
		joueur1.setStrokeType(StrokeType.INSIDE);
		joueur1.setWidth(hitFighter1.getWidth());
		paneJoueur1.getChildren().addAll(joueur1,rectTech1);

		// joueur 2
		paneJoueur2 = new Pane();
		paneJoueur2.setLayoutX(engine.getChar(2).getPositionX());
		paneJoueur2.setLayoutY(168);
		paneJoueur2.setPrefHeight(172.0);
		paneJoueur2.setPrefWidth(69.0);
		

		vieJoueur2 = new ProgressBar(100);
		vieJoueur2.setLayoutX(65);
		vieJoueur2.setLayoutY(27);
		vieJoueur2.prefHeight(20);
		vieJoueur2.prefWidth(168);
		
		rectTech2 = new Rectangle();
		rectTech2.setFill(Color.BLUE);
		rectTech2.setHeight(50.0);
		//rectTech2.setLayoutX(-99);
		rectTech2.setWidth(168);
		rectTech2.setVisible(false);

		joueur2 = new Rectangle();
		joueur2.setFill(Color.BLUE);
		joueur2.setHeight(hitFighter2.getHeight());
		joueur2.setStroke(Color.BLUE);
		joueur2.setStrokeType(StrokeType.INSIDE);
		joueur2.setWidth(hitFighter2.getWidth());
		paneJoueur2.getChildren().addAll(joueur2,rectTech2);

		AnchorPane anchore = new AnchorPane(vieJoueur1, vieJoueur2, paneJoueur1, paneJoueur2);
		anchore.setId("anchore");

		Scene scene = new Scene(anchore, engine.getWidth(), engine.getHeight());

		scene.getStylesheets().add("/css/main.css");
		MultiplePressedKeysEventHandler keyHandler = new MultiplePressedKeysEventHandler(new MultiKeyEventHandler() {
			@Override
			public void handle(MultiKeyEvent event) {
				/** player1 **/
				if (event.isPressed(KeyCode.UP) && event.isPressed(KeyCode.NUMPAD1)) {
					commandPlayer1 = COMMAND.JUMP_TECH_1;
				} else if (event.isPressed(KeyCode.UP) && event.isPressed(KeyCode.NUMPAD2)) {
					commandPlayer1 = COMMAND.JUMP_TECH_2;
				} else if (event.isPressed(KeyCode.DOWN) && event.isPressed(KeyCode.NUMPAD1)) {
					commandPlayer1 = COMMAND.CROUCH_TECH_1;
				} else if (event.isPressed(KeyCode.DOWN) && event.isPressed(KeyCode.NUMPAD2)) {
					commandPlayer1 = COMMAND.JUMP_TECH_2;
				} else if (event.isPressed(KeyCode.UP)) {
					commandPlayer1 = COMMAND.JUMP;
				} else if (event.isPressed(KeyCode.LEFT)) {
					commandPlayer1 = COMMAND.LEFT;
					engine.getChar(1).moveLeft();
					paneJoueur1.setLayoutX(engine.getChar(1).getPositionX());
				} else if (event.isPressed(KeyCode.RIGHT)) {
					commandPlayer1 = COMMAND.RIGHT;
				} else if (event.isPressed(KeyCode.DOWN)) {
					System.out.println(joueur2.getTranslateX());
					System.out.println(joueur2.getTranslateY());
					commandPlayer1 = COMMAND.CROUCH;
					engine.getChar(2).crouch();
					paneJoueur2.setPrefHeight(hitFighter1.getHeight()/2);
					joueur2.setHeight(hitFighter1.getHeight()/2);
					System.out.println(paneJoueur2.getLayoutX());
					System.out.println(paneJoueur2.getLayoutY());
					joueur2.setLayoutY(169);
					System.out.println(paneJoueur1);
				} else if (event.isPressed(KeyCode.NUMPAD1)) {
					commandPlayer1 = COMMAND.TECH_1;
				} else if (event.isPressed(KeyCode.NUMPAD2)) {
					commandPlayer1 = COMMAND.TECH_2;
				} else if (event.isPressed(KeyCode.NUMPAD3)) {
					commandPlayer1 = COMMAND.PROTECT;
				}

				/** player2 **/
				else if (event.isPressed(KeyCode.Z) && event.isPressed(KeyCode.E)) {
					commandPlayer2 = COMMAND.JUMP_TECH_1;
				} else if (event.isPressed(KeyCode.Z) && event.isPressed(KeyCode.R)) {
					commandPlayer2 = COMMAND.JUMP_TECH_2;
				} else if (event.isPressed(KeyCode.S) && event.isPressed(KeyCode.E)) {
					commandPlayer2 = COMMAND.CROUCH_TECH_1;
				} else if (event.isPressed(KeyCode.S) && event.isPressed(KeyCode.R)) {
					commandPlayer2 = COMMAND.CROUCH_TECH_2;
				} else if (event.isPressed(KeyCode.Z)) {
					commandPlayer2 = COMMAND.JUMP;
				} else if (event.isPressed(KeyCode.D)) {
					commandPlayer2 = COMMAND.LEFT;
				} else if (event.isPressed(KeyCode.Q)) {
					commandPlayer2 = COMMAND.RIGHT;
				} else if (event.isPressed(KeyCode.S)) {
					commandPlayer2 = COMMAND.CROUCH;
				} else if (event.isPressed(KeyCode.E)) {
					commandPlayer2 = COMMAND.TECH_1;
				} else if (event.isPressed(KeyCode.R)) {
					commandPlayer2 = COMMAND.TECH_2;
				} else if (event.isPressed(KeyCode.A)) {
					commandPlayer2 = COMMAND.PROTECT;
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

	KeyFrame keyFrame = new KeyFrame(Duration.millis(2000 / frameRate), e -> {
		if(engine.getChar(1).isTeching()) {
			if(engine.getChar(1).getTechFrame() == engine.getChar(1).getTech().getSframe())
				rectTech1.setVisible(true);
			else
				rectTech1.setVisible(false);
		}else if(engine.getChar(2).isTeching()) {
			if(engine.getChar(2).getTechFrame() == engine.getChar(2).getTech().getSframe())
				rectTech2.setVisible(true);
			else
				rectTech2.setVisible(false);
		}
		engine.step(commandPlayer1, commandPlayer2);
		commandPlayer1=COMMAND.NEUTRAL;
		commandPlayer2=COMMAND.NEUTRAL;		
		

	});

	public static void main(String... args) {
		launch(args);
	}
}
