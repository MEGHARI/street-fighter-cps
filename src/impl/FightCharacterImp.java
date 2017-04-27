package impl;

import data.Tech;
import enums.COMMAND;
import enums.NAME;
import services.CharacterService;
import services.EngineService;
import services.FightCharService;
import services.HitboxService;

public class FightCharacterImp implements FightCharService {

	private int positionX;
	private int positionY;
	private NAME name;
	private EngineService engine;
	private HitboxService hitbox;
	private int life;
	private int speed;
	private int techFrame;
	// jsk
	private boolean faceRight;
	private boolean isBlocking;
	private boolean isBlockstunned;
	private boolean isHitstunned;
	private boolean isTech;
	private Tech tech;
	private Tech[] techs = { new Tech(50, 10, 4, 3, 10, 5), new Tech(50, 10, 7, 7, 10, 5) };

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
		return hitbox;
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
		return (life > 0);
	}

	@Override
	public void init(NAME name, int l, int s, boolean f, EngineService e) {
		this.name = name;
		life = l;
		speed = s;
		faceRight = f;
		engine = e;
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
		case JUMP:
			jump();
			break;
		case CROUCH:
			crouch();
			break;
		default:
			break;
		}
		int i = 0;
		if (isTeching()) {

			if (techFrame == 1) {
				i++;
				if (tech.getSframe() <= i) {
					techFrame = 2;
					i = 0;
				}
			} else if (techFrame == 2) {
				i++;
				if (tech.getHframe() <= i) {
					techFrame = 3;
					i = 0;
				}

			} else if (techFrame == 3) {
				i++;
				if (tech.getRframe() <= i) {
					techFrame = 0;
					i = 0;
					isTech = false;
				}

			} else {
			}
		}

	}

	@Override
	public boolean notManipulable() {
		return isBlockstunned() || isHitstunned() || isTeching();
	}

	@Override
	public boolean isBlocking() {
		return isBlocking;
	}

	@Override
	public boolean isBlockstunned() {
		return isBlockstunned;
	}

	@Override
	public boolean isHitstunned() {
		return isHitstunned;
	}

	@Override
	public boolean isTeching() {
		return isTech;
	}

	@Override
	public Tech getTech() {
		return tech;
	}

	@Override
	public int getTechFrame() {
		return techFrame;
	}

	@Override
	public boolean isTechHasAlreadyHit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startTech(Tech tech) {
		if (!notManipulable()) {
			isTech = true;
			this.tech = tech;
			this.techFrame = 1;
		}

	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub

	}

	@Override
	public void crouch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startBlock() {
		isBlocking = true;

	}

}
