package impl;

import contracts.HitboxContract;
import data.Tech;
import enums.COMMAND;
import enums.NAME;
import services.EngineService;
import services.FightCharService;

public class FightCharacterImp extends CharacterImpl implements FightCharService {

	private int techFrame;
	private boolean isBlocking;
	private boolean isBlockstunned;
	private boolean isHitstunned;
	private boolean isTech;
	private Tech tech;
	private Tech[] techs ;

	@Override
	public void init(NAME name, int l, int s, boolean f, EngineService e) {
		super.init(name, l, s, f, e);
		techs = new Tech[2];
		
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
	public void moveLeft() {
		if(!notManipulable()) {
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
		if(!notManipulable()) {
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
	public FightCharacterImp clone(){
		FightCharacterImp fci = new FightCharacterImp();
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
