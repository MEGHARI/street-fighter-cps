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
		gameOver = getChar(1).isDead() || getChar(2).isDead();
		return gameOver;
	}

	@Override
	public CharacterService getChar(int i) {
		return player[i-1].getCharacter();
	}

	@Override
	public PlayerService getPlayer(int i) {
		return player[i-1];
	}

	@Override
	public void init(int w, int h, int s, PlayerService p1, PlayerService p2) {
		this.height = h;
		this.width = w;
		this.player[0] = p1;
		this.player[1] = p2;
		getChar(1).setPositions(w/2 - s/2, 0);
		getChar(1).initFace(true);
		getChar(2).setPositions(w/2 + s/2, 0);
		getChar(2).initFace(false);
		
	}

	@Override
	public void step(COMMAND c1, COMMAND c2) {
		if(!gameOver) {
			getChar(1).step(c1);
			getChar(2).step(c2);
			
		}else {
			System.out.println("game over");
		}
	}
	


}
