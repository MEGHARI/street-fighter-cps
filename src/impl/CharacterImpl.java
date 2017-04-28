
package impl;

import enums.COMMAND;
import enums.NAME;
import services.CharacterService;
import services.EngineService;
import services.HitboxService;

public class CharacterImpl implements CharacterService {

	private int positionX;
	private int positionY;
	private NAME name;
	private EngineService engine;
	private HitboxService charBox;
	private int life;
	private int speed;
	private boolean faceRight;
	private boolean dead;

	@Override
	public int getPositionX() {
		return positionX;
	}

	@Override
	public int getPositionY() {
		return positionY;
	}

	@Override
	public NAME getName() {
		return name;
	}

	@Override
	public EngineService getEngine() {
		return engine;
	}

	@Override
	public HitboxService getCharBox() {
		return charBox;
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public boolean faceRight() {
		return faceRight;
	}

	@Override
	public boolean isDead() {
		return dead;
	}

	@Override
	public void init(NAME name, int l, int s, boolean f, EngineService e) {
		this.name = name;
		this.life = l;
		this.dead = false;
		this.speed = s;
		this.faceRight = f;
		this.engine = e;
	}

	@Override
	public void setPositions(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}

	@Override
	public void initFace(boolean face) {
		faceRight = face;

	}

	@Override
	public void moveLeft() {
		positionX = Math.max(0, positionX - speed);
	}

	@Override
	public void moveRight() {
		positionX = Math.min(positionX + speed, getEngine().getWidth());
	}

	@Override
	public void switchSide() {
		faceRight = !faceRight;
	}

	@Override
	public void step(COMMAND c) {
		switch (c) {
		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveRight();
			break;
		case NEUTRAL:
			break;
		default:
			break;

		}
	}
	
	@Override
	public CharacterImpl clone(){
		CharacterImpl ci = new CharacterImpl();
		ci.charBox = charBox.clone();
		ci.dead = dead;
		ci.engine = engine;
		ci.faceRight = faceRight;
		ci.life = life;
		ci.name = name;
		ci.positionX = positionX;
		ci.positionY = positionY;
		ci.speed = speed;
		
		return ci;
	}

}
