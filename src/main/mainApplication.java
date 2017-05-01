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
	private Pane paneJoueur1,paneJoueur2;
	private double xJumpRadius = 20;
	private final double yJumpRadius = 80;
	private int frameRate = 1;
	private EngineContract engine;
	private PlayerContract p1, p2;
	private FightCharContract  fighter1, fighter2;
	private RectangleHitboxContract hitFighter1, hitFighter2;
	private ProgressBar vieJoueur1, vieJoueur2;
	private Rectangle joueur1, joueur2;
	private COMMAND commandPlayer1, commandPlayer2;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Initialisation
		
		hitFighter1 = new RectangleHitboxContract(new RectangleHitboxImpl());
		hitFighter1.init(0, 0,69 , 172);
		hitFighter2 = new RectangleHitboxContract(new RectangleHitboxImpl());
		hitFighter2.init(0, 0,69 , 172);
		p1 = new PlayerContract(new PlayerImpl());
		p2 = new PlayerContract(new PlayerImpl());
		fighter1 = new FightCharContract(new FightCharImpl());
		fighter2 = new FightCharContract(new FightCharImpl());
		engine = new EngineContract(new EngineImpl());
		fighter1.init(NAME.RY,100,5,true,engine,hitFighter1);
		fighter2.init(NAME.BISON,100,5,false,engine,hitFighter2);
		p1.init(1);
		p1.init(2);
		p1.setCharacter(fighter1);
		p2.setCharacter(fighter2);
		engine.init(659, 340,200, p1, p2);
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
		
		joueur1 = new Rectangle();
		joueur1.setFill(Paint.valueOf("#ff1f1f"));
		joueur1.setHeight(hitFighter1.getHeight());
		joueur1.setStroke(Color.BLACK);
		joueur1.setStrokeType(StrokeType.INSIDE);
		joueur1.setWidth(hitFighter1.getWidth());
		paneJoueur1.getChildren().addAll(joueur1);


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
		
		joueur2 = new Rectangle();
		joueur2.setFill(Paint.valueOf("#1c1d1e"));
		joueur2.setHeight(hitFighter2.getHeight());
		joueur2.setStroke(Color.BLUE);
		joueur2.setStrokeType(StrokeType.INSIDE);
		joueur2.setWidth(hitFighter2.getWidth());
		paneJoueur2.getChildren().addAll(joueur2);
		
		AnchorPane anchore = new AnchorPane(vieJoueur1, vieJoueur2, paneJoueur1, paneJoueur2);
		anchore.setId("anchore");

		Scene scene = new Scene(anchore,engine.getWidth(), engine.getHeight());


		scene.getStylesheets().add("/css/main.css");
		MultiplePressedKeysEventHandler keyHandler = new MultiplePressedKeysEventHandler(new MultiKeyEventHandler() {
			@Override
			public void handle(MultiKeyEvent event) {
				/**player1**/
				if (event.isPressed(KeyCode.UP) && event.isPressed(KeyCode.NUMPAD1)) {
					commandPlayer1 = COMMAND.JUMP_TECH_1;
				} else if (event.isPressed(KeyCode.UP) && event.isPressed(KeyCode.NUMPAD2)) {
					commandPlayer1 = COMMAND.JUMP_TECH_2;
				}else if (event.isPressed(KeyCode.DOWN) && event.isPressed(KeyCode.NUMPAD1)) {
					commandPlayer1 = COMMAND.CROUCH_TECH_1;
				} else if (event.isPressed(KeyCode.DOWN) && event.isPressed(KeyCode.NUMPAD2)) {
					commandPlayer1 = COMMAND.JUMP_TECH_2;
				} else if (event.isPressed(KeyCode.UP)) {
					commandPlayer1 = COMMAND.JUMP;
				} else if (event.isPressed(KeyCode.LEFT)) {
					commandPlayer1 = COMMAND.LEFT;	
				} else if (event.isPressed(KeyCode.RIGHT)) {
					commandPlayer1 = COMMAND.RIGHT;
					engine.getChar(1).moveRight();
					paneJoueur1.setLayoutX(engine.getChar(1).getPositionX());
				} else if (event.isPressed(KeyCode.DOWN)) {
					commandPlayer1 = COMMAND.CROUCH;
				} else if (event.isPressed(KeyCode.NUMPAD1)) {
					commandPlayer1 = COMMAND.TECH_1;
				} else if (event.isPressed(KeyCode.NUMPAD2)) {
					commandPlayer1 = COMMAND.TECH_2;
				} else if (event.isPressed(KeyCode.NUMPAD3)) {
					commandPlayer1 = COMMAND.PROTECT;
				} 
				
				/**player2**/
				else if (event.isPressed(KeyCode.Z) && event.isPressed(KeyCode.E)) {
					commandPlayer2 = COMMAND.JUMP_TECH_1;
				} else if (event.isPressed(KeyCode.Z) && event.isPressed(KeyCode.R)) {
					commandPlayer2 = COMMAND.JUMP_TECH_2;
				}else if (event.isPressed(KeyCode.S) && event.isPressed(KeyCode.E)) {
					commandPlayer2 = COMMAND.CROUCH_TECH_1;
				}else if (event.isPressed(KeyCode.S) && event.isPressed(KeyCode.R)) {
					commandPlayer2 = COMMAND.CROUCH_TECH_2;
				}else if (event.isPressed(KeyCode.Z)) {
					commandPlayer2 = COMMAND.JUMP;
				} else if (event.isPressed(KeyCode.D)) {
					commandPlayer2 = COMMAND.LEFT;
				} else if (event.isPressed(KeyCode.Q)) {
					commandPlayer2 = COMMAND.RIGHT;
				} else if (event.isPressed(KeyCode.S)) {
					commandPlayer2 = COMMAND.CROUCH;
				}else if (event.isPressed(KeyCode.E)) {
					commandPlayer2 = COMMAND.TECH_1;
				}else if (event.isPressed(KeyCode.R)) {
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

	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/frameRate), e -> {
		//System.out.println(commandPlayer1+" > "+commandPlayer2);
		System.out.println(commandPlayer1);
		System.out.println(commandPlayer1);
		System.out.println(engine.getChar(1));
		engine.step(commandPlayer1, commandPlayer2);
		//update();
		
		

	});

	private void jumpRight(Rectangle rect) {
		jump(rect, 180, -180, getHorizontalCenter(rect) + xJumpRadius, getVerticalCenter(rect));
		// System.out.println(rect.getTranslateY());
		rect.setTranslateY(0.0);

	}

	private void jumpLeft(Rectangle rect) {
		jump(rect, 0, 180, getHorizontalCenter(rect) - xJumpRadius, getVerticalCenter(rect));
		rect.setTranslateY(0.0);
	}

	private void normalyJump(Rectangle rect) {
		xJumpRadius = 0;
		jump(rect, 0, 180, getHorizontalCenter(rect), getVerticalCenter(rect));
		xJumpRadius = 20;
	}

	private void jump(Rectangle rect, double startAngle, double angularLength, double centerX, double centerY) {
		if (rect.getTranslateY() == 0.0) {

			Arc arc = new Arc();
			arc.setCenterX(centerX);
			arc.setCenterY(centerY);
			arc.setRadiusX(xJumpRadius);
			arc.setRadiusY(yJumpRadius);
			arc.setStartAngle(startAngle);
			arc.setLength(angularLength);

			PathTransition transition = new PathTransition(Duration.seconds(1), arc, rect);

			transition.playFromStart();
		}

	}

	private double getHorizontalCenter(Rectangle rect) {
		return rect.getX() + rect.getTranslateX() + rect.getWidth() / 2;

	}

	private double getVerticalCenter(Rectangle rect) {
		return rect.getY() + rect.getTranslateY() + rect.getHeight() / 2;

	}
	
	private void update() {
		paneJoueur1.setLayoutX(engine.getChar(1).getPositionX());
		paneJoueur2.setLayoutX(engine.getChar(2).getPositionY());
		vieJoueur1.setProgress(engine.getChar(1).getLife()/100);
		vieJoueur1.setProgress(engine.getChar(2).getLife()/100);
	}

	public static void main(String... args) {
		launch(args);
	}
}
