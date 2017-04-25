package impl;

import java.net.URL;
import java.util.ResourceBundle;

import enums.COMMAND;
import javafx.fxml.Initializable;
import services.CharacterService;
import services.EngineService;
import services.PlayerService;

public class EngineImpl implements EngineService {

	private int height;
	private int width;
	private PlayerService player[] = new PlayerService[2];
	private boolean gameOver;
	
	

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public boolean isGameOver() {
		return gameOver;
	}

	@Override
	public CharacterService getChar(int i) {
		return player[i].getCharacter();
	}

	@Override
	public PlayerService getPlayer(int i) {
		return player[i];
	}

	@Override
	public void init(int h, int s, int w, PlayerService p1, PlayerService p2) {
		this.height = h;
		this.width = w;
		this.player[0] = p1.getNum() == 1 ? p1:p2;
		this.player[1] = p1.getNum() == 2 ? p1:p2;
		player[0].getCharacter().setPositions(w/2 - s/2, 0);
		player[0].getCharacter().initFace(true);
		player[1].getCharacter().setPositions(w/2 + s/2, 0);
		player[1].getCharacter().initFace(false);
		
	}

	@Override
	public void step(COMMAND c1, COMMAND c2) {
		player[0].getCharacter().step(c1);
		player[1].getCharacter().step(c2);
	}
	


}
