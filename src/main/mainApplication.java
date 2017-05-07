package main;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

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
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class mainApplication extends Application {

	private Pane paneJoueur1, paneJoueur2;
	private int frameRate = 60;
	private EngineContract engine;
	private PlayerContract p1, p2;
	private FightCharContract fighter1, fighter2;
	private RectangleHitboxContract hitFighter1, hitFighter2, hitTEch1, HitTech2;
	private ProgressBar vieJoueur1, vieJoueur2;
	private Rectangle joueur1, joueur2, rectTech1Joueur1, rectTech2Joueur1, rectTech1Joueur2, rectTech2Joueur2;
	private COMMAND commandPlayer1, commandPlayer2;
	private AnchorPane anchore;
	private ImageView gameOver;
	private Timeline timerThread;

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

		/* techniques joueur 1 */
		rectTech1Joueur1 = new Rectangle();
		rectTech1Joueur1.setFill(Color.RED);
		rectTech1Joueur1.setHeight(50.0);
		rectTech1Joueur1.setWidth(130);
		rectTech1Joueur1.setLayoutY(170);
		rectTech1Joueur1.setVisible(false);

		rectTech2Joueur1 = new Rectangle();
		rectTech2Joueur1.setFill(Color.RED);
		rectTech2Joueur1.setHeight(50.0);
		rectTech2Joueur1.setWidth(130);
		rectTech2Joueur1.setLayoutY(286);
		rectTech2Joueur1.setVisible(false);

		joueur1 = new Rectangle();
		joueur1.setFill(Color.RED);
		joueur1.setHeight(170);
		joueur1.setLayoutX(fighter1.getPositionX());
		joueur1.setLayoutY(170);
		joueur1.setStroke(Color.BLACK);
		joueur1.setStrokeType(StrokeType.INSIDE);
		joueur1.setWidth(hitFighter1.getWidth());

		vieJoueur2 = new ProgressBar(1.0);
		vieJoueur2.setLayoutX(432);
		vieJoueur2.setLayoutY(27);
		vieJoueur2.prefHeight(20);
		vieJoueur2.prefWidth(168);

		rectTech1Joueur2 = new Rectangle();
		rectTech1Joueur2.setFill(Color.BLUE);
		rectTech1Joueur2.setHeight(50.0);
		rectTech1Joueur2.setWidth(130);
		rectTech1Joueur2.setLayoutY(170);
		rectTech1Joueur2.setVisible(false);

		rectTech2Joueur2 = new Rectangle();
		rectTech2Joueur2.setFill(Color.BLUE);
		rectTech2Joueur2.setHeight(50.0);
		rectTech2Joueur2.setWidth(130);
		rectTech2Joueur2.setLayoutY(286);
		rectTech2Joueur2.setVisible(false);

		joueur2 = new Rectangle();
		joueur2.setFill(Color.BLUE);
		joueur2.setLayoutX(fighter2.getPositionX());
		joueur2.setLayoutY(170);
		joueur2.setHeight(hitFighter2.getHeight());
		joueur2.setStroke(Color.BLUE);
		joueur2.setStrokeType(StrokeType.INSIDE);
		joueur2.setWidth(hitFighter2.getWidth());

		/*Image image = new Image("/images/gameOver.jpg");
		gameOver = new ImageView(image);
		gameOver.setFitWidth(252.0);
		gameOver.setLayoutX(200);
		gameOver.setLayoutY(55.0);
		gameOver.setPickOnBounds(true);
		gameOver.setPreserveRatio(true);*/

		anchore = new AnchorPane(vieJoueur1, vieJoueur2, rectTech1Joueur1, rectTech2Joueur1, rectTech1Joueur2,
				rectTech2Joueur2, joueur1, joueur2);
		anchore.setId("anchore");

		Scene scene = new Scene(anchore, engine.getWidth(), engine.getHeight());

		scene.getStylesheets().add("/css/main.css");
		scene.setOnKeyPressed(e ->
		/** player2 **/
		{
			if (e.getCode() == KeyCode.UP) {
				commandPlayer2 = COMMAND.JUMP;
			} else if (e.getCode() == KeyCode.LEFT) {
				commandPlayer2 = COMMAND.LEFT;
			} else if (e.getCode() == KeyCode.RIGHT) {
				commandPlayer2 = COMMAND.RIGHT;
			} else if (e.getCode() == KeyCode.DOWN) {
				commandPlayer2 = COMMAND.CROUCH;
			} else if (e.getCode() == KeyCode.NUMPAD1) {
				commandPlayer2 = COMMAND.TECH_1;
			} else if (e.getCode() == KeyCode.NUMPAD2) {
				commandPlayer2 = COMMAND.TECH_2;
			} else if (e.getCode() == KeyCode.NUMPAD3) {
				commandPlayer2 = COMMAND.PROTECT;
			}

			/** player1 **/
			if (e.getCode() == KeyCode.Z) {
				commandPlayer1 = COMMAND.JUMP;
			} else if (e.getCode() == KeyCode.D) {
				commandPlayer1 = COMMAND.RIGHT;
			} else if (e.getCode() == KeyCode.Q) {
				commandPlayer1 = COMMAND.LEFT;
			} else if (e.getCode() == KeyCode.S) {
				commandPlayer1 = COMMAND.CROUCH;
			} else if (e.getCode() == KeyCode.E) {
				commandPlayer1 = COMMAND.TECH_1;
			} else if (e.getCode() == KeyCode.R) {
				commandPlayer1 = COMMAND.TECH_2;
			} else if (e.getCode() == KeyCode.A) {
				commandPlayer1 = COMMAND.PROTECT;
			}
		}

		);

		scene.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.DOWN) {
				commandPlayer2 = COMMAND.RIZE;
			}

			if (e.getCode() == KeyCode.S)
				commandPlayer1 = COMMAND.RIZE;

			if (e.getCode() == KeyCode.A) {
				commandPlayer1 = COMMAND.ENDPROTECT;
			}

			if (e.getCode() == KeyCode.NUMPAD3) {
				commandPlayer2 = COMMAND.ENDPROTECT;
			}
		});
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
		timerThread = new Timeline(keyFrame);
		timerThread.setCycleCount(Timeline.INDEFINITE);
		timerThread.play();

	}

	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/30), e -> {
		if (engine.isGameOver()) {
			anchore.getChildren().add(gameOver);
			gameOver.setVisible(true);
			timerThread.stop();

		} else {
			engine.step(commandPlayer1, commandPlayer2);
			updateGames();
			commandPlayer1 = COMMAND.NEUTRAL;
			commandPlayer2 = COMMAND.NEUTRAL;
		}

	});

	public Rectangle crouch(Rectangle p) {
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

	public Rectangle rize(Rectangle p) {
		p.getTransforms().clear();
		return p;
	}

	public void updateGames() {

		if (fighter1.isTeching() && !fighter1.isBlockstunned() && !fighter1.isHitstunned()) {
			if (fighter1.getTechFrame() >= fighter1.getTech().getSframe()
					&& fighter1.getTechFrame() < fighter1.getTech().getSframe() + fighter1.getTech().getHframe()) {
				if (fighter1.getTech().getDamage() == 10) {
					rectTech2Joueur1.setVisible(true);
				} else if (fighter1.getTech().getDamage() == 30)
					rectTech1Joueur1.setVisible(true);
			} else {
				rectTech1Joueur1.setVisible(false);
				rectTech2Joueur1.setVisible(false);
			}

		} else if (fighter2.isTeching() && !fighter2.isBlockstunned() && !fighter2.isHitstunned()) {
			if (fighter2.getTechFrame() >= fighter2.getTech().getSframe()
					&& fighter2.getTechFrame() < fighter2.getTech().getSframe() + fighter2.getTech().getHframe()) {
				if (fighter2.getTech().getDamage() == 10) {
					rectTech2Joueur2.setVisible(true);
				} else if (fighter2.getTech().getDamage() == 30)
					rectTech1Joueur2.setVisible(true);
			} else {
				rectTech1Joueur2.setVisible(false);
				rectTech2Joueur2.setVisible(false);
			}

		}

		if (fighter1.getJumpFrame() >= 0 && fighter1.getJumpFrame() <= 3)
			joueur1.setLayoutY(joueur1.getLayoutY() - fighter1.getPositionY());
		else if (fighter1.getJumpFrame() > 3 && fighter1.getJumpFrame() <= 7) {
			joueur1.setLayoutY(joueur1.getLayoutY() + fighter1.getPositionY());
		}
		

		if (fighter2.getJumpFrame() >= 0 && fighter2.getJumpFrame() <= 3)
			joueur2.setLayoutY(joueur2.getLayoutY() - fighter2.getPositionY());
		else if (fighter2.getJumpFrame() > 3 && fighter2.getJumpFrame() <= 7) {
			joueur2.setLayoutY(joueur2.getLayoutY() + fighter2.getPositionY());
		} 

		
		joueur1.setLayoutX(fighter1.getPositionX());
		joueur2.setLayoutX(fighter2.getPositionX());
		vieJoueur1.setProgress((double) fighter1.getLife() / 100);
		vieJoueur2.setProgress((double) fighter2.getLife() / 100);
		rectTech1Joueur1.setLayoutX(fighter1.getPositionX() + fighter1.getCharBox().getWidth());
		rectTech1Joueur2.setLayoutX(fighter2.getPositionX() - rectTech1Joueur2.getWidth());
		rectTech2Joueur1.setLayoutX(fighter1.getPositionX() + fighter1.getCharBox().getWidth());
		rectTech2Joueur2.setLayoutX(fighter2.getPositionX() - rectTech1Joueur2.getWidth());

		if (!(fighter1.isCrouch())) {
			rize(joueur1);
		} else if (fighter1.isCrouch()) {
			crouch(joueur1);
		}
		if (!(fighter2.isCrouch())) {
			rize(joueur2);
		} else if (fighter2.isCrouch()) {
			crouch(joueur2);
		}
	}

	public static void main(String... args) {
		launch(args);
	}
}
