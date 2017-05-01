package impl;

import enums.COMMAND;
import services.CharacterService;
import services.EngineService;
import services.FightCharService;
import services.PlayerService;

public class EngineBugImpl implements EngineService {

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
	public FightCharService getChar(int i) {
		return player[i].getCharacter();
	}

	@Override
	public PlayerService getPlayer(int i) {
		return player[i];
	}

	@Override
	public void init(int w, int h, int s, PlayerService p1, PlayerService p2) {
		this.height = h;
		this.width = w;
		this.player[0] = p1.getNum() == 1 ? p1:p2;
		this.player[1] = p1.getNum() == 2 ? p1:p2;;
		player[0].getCharacter().setPositions(w/2 - s/2, 0);
		// bug
		player[0].getCharacter().initFace(false);
		// bug
		player[1].getCharacter().setPositions(w/2 + s, 0);
		// bug
		player[1].getCharacter().initFace(true);
		
	}

	@Override
	public void step(COMMAND c1, COMMAND c2) {
		player[0].getCharacter().step(c1);
		player[1].getCharacter().step(c2);
		// bug
	}

}
