package impl;

import data.Tech;
import enums.COMMAND;
import enums.NAME;
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
		return dead;
	}

	@Override
	public void init(NAME name, int l, int s, boolean f, EngineService e) {
		name =name;
		life = l;
		speed =s;
		faceRight = f;
		engine =e;
	}

	@Override
	public void setPositions(int x, int y) {
		

	}

	@Override
	public void initFace(boolean face) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(COMMAND c) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean notManipulable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlockstunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHitstunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTeching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tech getTech() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTechFrame() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTechHasAlreadyHit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startTech(Tech tech) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
