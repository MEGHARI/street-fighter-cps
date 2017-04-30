package impl;

import contracts.HitboxContract;
import contracts.RectangleHitboxContract;
import data.Tech;
import enums.COMMAND;
import enums.NAME;
import services.EngineService;
import services.FightCharService;
import services.RectangleHitboxService;

public class FightCharImpl extends CharacterImpl implements FightCharService {

	private int techFrame;
	private boolean isBlocking;
	private boolean isBlockstunned;
	private boolean isHitstunned;
	private boolean isTech;
	private Tech tech;
	private Tech[] techs ;
	private RectangleHitboxService charBox;

	@Override
	public void init(NAME name, int l, int s, boolean f, EngineService e) {
		super.init(name, l, s, f, e);
		techs = new Tech[2];
		charBox = new RectangleHitboxContract(new RectangleHitboxImpl());
		
	}
	
	public RectangleHitboxService getCharBox() {
		return charBox;
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
		case JUMP_TECH_1:
			break;
		case JUMP_TECH_2:
			break;
		case CROUCH_TECH_1:
			break;
		case CROUCH_TECH_2:
			break;
		case PROTECT:
			startBlock();
		break;
		default:
			break;
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
	public void moveLeft() {
		if(!(notManipulable() || isBlocking())) {
			HitboxContract hit = (HitboxContract) this.getCharBox().clone();
			hit.moveTo(getPositionX()-getSpeed(),getPositionY());
			if(getEngine().getChar(1).getCharBox() != this.getCharBox()){
				if(!(hit.collidesWith(getEngine().getChar(1).getCharBox()))){
					positionX = Math.max(0, positionX - speed);
					getCharBox().moveTo(this.positionX, this.positionY);
				}
			}else if(getEngine().getChar(2).getCharBox() != this.getCharBox()){
				if(!(hit.collidesWith(getEngine().getChar(2).getCharBox()))){
					positionX = Math.max(0, positionX - speed);
					getCharBox().moveTo(this.positionX, this.positionY);
				}
			}
		}
	}

	@Override
	public void moveRight() {
		if(!(notManipulable() || isBlocking())) {
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
		
	}

	@Override
	public void switchSide() {
		faceRight = !faceRight;
		getCharBox().moveTo(this.positionX, this.positionY);

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
	
	@Override
	public FightCharImpl clone(){
		FightCharImpl fci = new FightCharImpl();
		fci.engine = engine;
		fci.faceRight = faceRight;
		fci.charBox = charBox.clone();
		fci.isBlocking = isBlocking;
		fci.isBlockstunned = isBlockstunned;
		fci.isHitstunned = isHitstunned;
		fci.isTech = isTech;
		fci.life = life;
		fci.name = name;
		fci.positionX = positionX;
		fci.positionY = positionY;
		fci.speed = speed;
		fci.tech = tech;
		fci.techFrame = techFrame;
		fci.techs = techs;
		return fci;
	}

}