package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.COMMAND;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
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
	private  double xJumpRadius = 20 ;
	private final double yJumpRadius = 80 ;
	private int frame = 60;
	private COMMAND c1;
	private COMMAND c2;
	private EngineService engine;
	private PlayerService p1, p2;
	private CharacterService fighter1, fighter2;
	private HitboxService hitFighter1, hitFighter2;
	private ProgressBar vieJoueur1, vieJoueur2;
	private Rectangle joueur1, joueur2;
	private List<KeyCode> knownKeysPlayer1, knownKeysPlayer2;
	private int pressNumberPlayer1, pressNumberPlayer2;
	private List<KeyCode> combinationsPlayer1, combinationsPlayer2;
	private COMMAND commandPlayer1, commandPlayer2;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Initialisation

		knownKeysPlayer1 = new ArrayList<KeyCode>(Arrays.asList(KeyCode.UP, KeyCode.RIGHT, KeyCode.LEFT, KeyCode.DOWN,
				KeyCode.NUMPAD3,KeyCode.NUMPAD4, KeyCode.NUMPAD5, KeyCode.NUMPAD6));

		knownKeysPlayer2 = new ArrayList<KeyCode>(Arrays.asList(/* player2 */KeyCode.A, KeyCode.Z, KeyCode.E, KeyCode.S,
				KeyCode.F, KeyCode.G, KeyCode.H,KeyCode.B));

		// les combinations de touche
		combinationsPlayer1 = new ArrayList<>();
		combinationsPlayer2 = new ArrayList<>();
		pressNumberPlayer1 = 0;
		pressNumberPlayer2 = 0;
		commandPlayer1=COMMAND.NEUTRAL;
		commandPlayer2=COMMAND.NEUTRAL;

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

		// joueur1.setOnKeyPressed(e->{System.out.println("player2");});

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

		AnchorPane anchore = new AnchorPane(vieJoueur1, vieJoueur2, joueur1, joueur2);
		anchore.setId("anchore");

		Scene scene = new Scene(anchore, 659, 340);
		scene.getStylesheets().add("/css/main.css");
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode code = event.getCode();
				if (knownKeysPlayer1.contains(code)) {
					if (pressNumberPlayer1 < 0) {
						pressNumberPlayer1 = 0;
					}
					if (!combinationsPlayer1.contains(code)) {
						pressNumberPlayer1++;
						combinationsPlayer1.add(code);
					}
				} else if (knownKeysPlayer2.contains(code)) {
					if (pressNumberPlayer2 < 0) {
						pressNumberPlayer2 = 0;
					}
					if (!combinationsPlayer2.contains(code)) {
						pressNumberPlayer2++;
						combinationsPlayer2.add(code);
					}
				}
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(combinationsPlayer1.contains(event.getCode())) {
					pressNumberPlayer1--;
					if (pressNumberPlayer1 == 0) {
						if (combinationsPlayer1.contains(KeyCode.UP) && combinationsPlayer1.contains(KeyCode.RIGHT)) {
							commandPlayer1 = COMMAND.JUMP_RIGHT;
						} else if (combinationsPlayer1.contains(KeyCode.UP) && combinationsPlayer1.contains(KeyCode.LEFT)) {
							commandPlayer1 = COMMAND.JUMP_LEFT;
							System.out.println("jump_left");
						} else if (combinationsPlayer1.contains(KeyCode.UP)) {
							commandPlayer1 = COMMAND.JUMP;
						} else if (combinationsPlayer1.contains(KeyCode.LEFT)) {
							commandPlayer1 = COMMAND.LEFT;
						} else if (combinationsPlayer1.contains(KeyCode.RIGHT)) {
							commandPlayer1 = COMMAND.RIGHT;
						} else if (combinationsPlayer1.contains(KeyCode.DOWN)) {
							commandPlayer1 = COMMAND.CROUCH;
						} else if (combinationsPlayer1.contains(KeyCode.NUMPAD4)) {
							commandPlayer1 = COMMAND.TECH_1;
						} else if (combinationsPlayer1.contains(KeyCode.NUMPAD6)) {
							commandPlayer1 = COMMAND.TECH_2;
						} else if (combinationsPlayer1.contains(KeyCode.NUMPAD5)) {
							commandPlayer1 = COMMAND.PROTECT;
						} 
						combinationsPlayer1.clear();
					}
				}else if(combinationsPlayer2.contains(event.getCode())) {
					pressNumberPlayer2--;
					if (pressNumberPlayer2 == 0) {
						if (combinationsPlayer2.contains(KeyCode.Z) && combinationsPlayer2.contains(KeyCode.E)) {
							commandPlayer1 = COMMAND.JUMP_RIGHT;
							jumpRight(joueur2);
						} else if (combinationsPlayer2.contains(KeyCode.Z) && combinationsPlayer2.contains(KeyCode.A)) {
							 commandPlayer1 = COMMAND.JUMP_LEFT;
						} else if (combinationsPlayer2.contains(KeyCode.Z)) {
							 commandPlayer1 = COMMAND.JUMP;
							 normalyJump(joueur2);
						} else if (combinationsPlayer2.contains(KeyCode.A)) {
							 commandPlayer1 = COMMAND.LEFT;
						} else if (combinationsPlayer2.contains(KeyCode.E)) {
							 commandPlayer1 = COMMAND.RIGHT;
						} else if (combinationsPlayer2.contains(KeyCode.S)) {
							commandPlayer1 = COMMAND.CROUCH;
						} else if (combinationsPlayer2.contains(KeyCode.F)) {
							 commandPlayer1 = COMMAND.TECH_1;
						} else if (combinationsPlayer2.contains(KeyCode.H)) {
							commandPlayer1 = COMMAND.TECH_2;
						} else if (combinationsPlayer2.contains(KeyCode.G)) {
							 commandPlayer1 = COMMAND.PROTECT;
						} 
						combinationsPlayer2.clear();
					}
				}
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
		Timeline timerThread = new Timeline(keyFrame);
		timerThread.setCycleCount(Timeline.INDEFINITE);
		timerThread.play();

	}

	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), e -> {
		// engine.step(c1, c2);
		//System.out.println(commandPlayer1);
		//System.out.println(commandPlayer1);
		// System.out.println("mouloud");
		//joueur1.setLayoutX(joueur1.getLayoutX() - 1);

	});
	
	private void jumpRight(Rectangle rect) {
        jump(rect, 180,-180, getHorizontalCenter(rect) + xJumpRadius, getVerticalCenter(rect));
        //System.out.println(rect.getTranslateY());
		rect.setTranslateY(0.0);

    }

    private void jumpLeft(Rectangle rect) {        
        jump(rect, 0, 180, getHorizontalCenter(rect) - xJumpRadius, getVerticalCenter(rect));
        rect.setTranslateY(0.0);
    }
    
    private void normalyJump(Rectangle rect) { 
    	xJumpRadius=0;
        jump(rect, 0, 180, getHorizontalCenter(rect), getVerticalCenter(rect));
        xJumpRadius=20;
    }

    private void jump(Rectangle rect, double startAngle, double angularLength, double centerX, double centerY) {
        if(rect.getTranslateY()==0.0) {
        	
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
        return rect.getX() + rect.getTranslateX() + rect.getWidth() / 2 ;
      
    }

    private double getVerticalCenter(Rectangle rect) {
        return rect.getY() + rect.getTranslateY() + rect.getHeight() / 2 ;

    }


	public static void main(String... args) {
		launch(args);
	}
}
