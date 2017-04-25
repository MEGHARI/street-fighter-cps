package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class mainController implements Initializable {

	@FXML
	ImageView zangief, ry, vega, bison, chn;
	
	@FXML
	ImageView choosen1,choosen2;
	
	@FXML
	Button startGame;
	ImageView fighters[] = { zangief, ry, vega, bison, chn };
	public static ImageView playersChossen [] ;

	boolean player1choosen, player2choosen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		player1choosen = player2choosen = false;
		playersChossen = new ImageView[2];
		startGame.setVisible(false);

	}

	@FXML
	public void startGame(ActionEvent event) {

	}

	@FXML
	public void chooseCharacher(MouseEvent event) {
		ImageView choose = ((ImageView) ((Node) event.getSource()));
		if (!player1choosen) {
			if (choose == zangief) {
				zangief.setVisible(false);
				Image im = new Image("/images/zangief.gif");
				choosen1.setImage(im);
				player1choosen = true;
				playersChossen[0]=zangief;

			} else if (choose == ry) {
				ry.setVisible(false);
				Image im = new Image("/images/ry.gif");
				choosen1.setImage(im);
				player1choosen = true;
				playersChossen[0]=ry;

			} else if (choose == vega) {
				vega.setVisible(false);
				Image im = new Image("/images/vega.gif");
				choosen1.setImage(im);
				player1choosen = true;
				playersChossen[0]=vega;

			} else if (choose == chn) {
				chn.setVisible(false);
				Image im = new Image("/images/chn.gif");
				choosen1.setImage(im);
				player1choosen = true;
				playersChossen[0]=chn;

			}
			if (choose == bison) {
				bison.setVisible(false);
				Image im = new Image("/images/bison.gif");
				choosen1.setImage(im);
				player1choosen = true;
				playersChossen[0]=bison;

			}
		} else if (!player2choosen) {
			if (choose == zangief) {
				zangief.setVisible(false);
				Image im = new Image("/images/zangief.gif");
				choosen2.setImage(im);
				player2choosen = true;
				playersChossen[1]=zangief;


			} else if (choose == ry) {
				ry.setVisible(false);
				Image im = new Image("/images/ry.gif");
				choosen2.setImage(im);
				player2choosen = true;
				playersChossen[1]=ry;

			} else if (choose == vega) {
				vega.setVisible(false);
				Image im = new Image("/images/vega.gif");
				choosen2.setImage(im);
				playersChossen[1]=vega;
		
				player2choosen = true;

			} else if (choose == chn) {
				chn.setVisible(false);
				Image im = new Image("/images/chn.gif");
				choosen2.setImage(im);
				player2choosen = true;
				playersChossen[1]=chn;

			}
			if (choose == bison) {
				bison.setVisible(false);
				Image im = new Image("/images/bison.gif");
				choosen2.setImage(im);
				player2choosen = true;
				playersChossen[1]=bison;

			}

		}
		if(player1choosen && player2choosen)
			startGame.setVisible(true);
			
			

	}
	
	@FXML
	public void reset(MouseEvent event) {
		ImageView choosen = ((ImageView) ((Node) event.getSource()));
		if(choosen1== choosen) {
			choosen1.setImage(null);
			player1choosen =false;
			playersChossen[0].setVisible(true);
			startGame.setVisible(false);
		} else if(choosen2== choosen) {
			choosen2.setImage(null);
			player2choosen =false;
			playersChossen[1].setVisible(true);
			startGame.setVisible(false);
		}
	}

}
