package impl;

import contracts.HitboxContract;
import enums.COMMAND;
import enums.NAME;
import services.CharacterService;
import services.EngineService;
import services.HitboxService;

public class CharacterBugImpl implements CharacterService {

	protected int positionX;
	protected int positionY;
	protected NAME name;
	protected EngineService engine;
	protected HitboxService charBox;
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
		return (getLife()<= 0);
	}

	@Override
	public void init(NAME name, int l, int s, boolean f, EngineService e) {
		this.name = name;
		this.life = l;
		this.speed = s;
		this.faceRight = f;
		this.engine = e;
		this.charBox = new HitboxContract(new HitboxImpl());
		this.charBox.init(getPositionX(), getPositionY());

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
		HitboxContract hit = (HitboxContract) this.getCharBox().clone();
		hit.moveTo(getPositionX()-getSpeed(),getPositionY());
		if(getEngine().getChar(1).getCharBox() != this.getCharBox()){
			if(!(hit.collidesWith(getEngine().getChar(1).getCharBox()))){
				// bug
				positionX = Math.max(0, positionX);
				getCharBox().moveTo(this.positionX, this.positionY);
			}
		}else if(getEngine().getChar(2).getCharBox() != this.getCharBox()){
			if(!(hit.collidesWith(getEngine().getChar(2).getCharBox()))){
				// bug
				positionX = Math.max(0, positionX );
				getCharBox().moveTo(this.positionX, this.positionY);
			}
		}
	}

	@Override
	public void moveRight() {
		HitboxContract hit = (HitboxContract) this.getCharBox().clone();
		hit.moveTo(getPositionX()+getSpeed(),getPositionY());
		if(getEngine().getChar(1).getCharBox() != this.getCharBox()){
			if(!(hit.collidesWith(getEngine().getChar(1).getCharBox()))){
				positionX = Math.min(positionX + speed, getEngine().getWidth());
				getCharBox().moveTo(this.positionX, this.positionY);
			}
		}else if(getEngine().getChar(2).getCharBox() != this.getCharBox()){
			if(!(hit.collidesWith(getEngine().getChar(2).getCharBox()))){
				positionX = Math.min(positionX + speed, getEngine().getWidth());
				getCharBox().moveTo(this.positionX, this.positionY);
			}
		}
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
	public CharacterBugImpl clone(){
		CharacterBugImpl ci = new CharacterBugImpl();
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
