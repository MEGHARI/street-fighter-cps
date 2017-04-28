
package impl;

import contracts.RectangleHitboxContract;
import enums.COMMAND;
import enums.NAME;
import services.CharacterService;
import services.EngineService;
import services.HitboxService;
import services.RectangleHitboxService;

public class CharacterImpl implements CharacterService {

	protected int positionX;
	protected int positionY;
	protected NAME name;
	protected EngineService engine;
	protected RectangleHitboxService charBox;
	protected int life;
	protected int speed;
	protected boolean faceRight;

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
		return (getLife()<=0);
	}

	@Override
	public void init(NAME name, int l, int s, boolean f, EngineService e) {
		this.name = name;
		this.life = l;
		this.speed = s;
		this.faceRight = f;
		this.engine = e;
		this.charBox = new RectangleHitboxContract(new RectangleHitboxImpl());
		this.charBox.init(getPositionX(), getPositionY(),63,63);
		
	}

	@Override
	public void setPositions(int x, int y) {
		this.positionX = x;
		this.positionY = y;
		getCharBox().moveTo(x, y);
	}

	@Override
	public void initFace(boolean face) {
		faceRight = face;

	}

	@Override
	public void moveLeft() {
		positionX = Math.max(0, positionX - speed);
		getCharBox().moveTo(this.positionX, this.positionY);
	}

	@Override
	public void moveRight() {
		positionX = Math.min(positionX + speed, getEngine().getWidth());
		getCharBox().moveTo(this.positionX, this.positionY);
	}

	@Override
	public void switchSide() {
		faceRight = !faceRight;
		getCharBox().moveTo(this.positionX, this.positionY);

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
