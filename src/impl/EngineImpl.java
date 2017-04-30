package impl;

import enums.COMMAND;
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
		return player[i-1].getCharacter();
	}

	@Override
	public PlayerService getPlayer(int i) {
		return player[i-1];
	}

	@Override
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) {
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
		/*
		getChar(1).step(c1);
		getChar(2).step(c2);
		if(getChar(1).getPositionX()<getChar(2).getPositionX()) {
			if(getChar(1).faceRight()) {
				getChar(1).switchSide();
			}
			if(!getChar(2).faceRight()) {
				getChar(2).switchSide();
			}
				
		}*/
	}
}
