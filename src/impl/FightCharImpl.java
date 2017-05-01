package impl;

import contracts.FightCharContract;
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
	private boolean isTechHasAlreadyHit;
	private Tech tech;
	private Tech[] techs ;
	private RectangleHitboxService charBox;
	
	@Override
	public void init(NAME name, int l, int s, boolean f,RectangleHitboxService rh, EngineService e) {
		super.init(name, l, s, f, e);
		rh.moveTo(getPositionX(), getPositionY());
		this.charBox = rh;
		techs = new Tech[2];
		
		
	}
	
	public RectangleHitboxService getCharBox() {
		return charBox;
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
		return isTechHasAlreadyHit;
	}

	@Override
	public void startTech(Tech tech) {
		if (!notManipulable()) {
			isTech = true;
			this.tech = tech;
			this.techFrame = 0;
		}

	}
	@Override
	public void moveLeft() {
		if(!(notManipulable() || isBlocking())) {
			RectangleHitboxContract hit = (RectangleHitboxContract) this.getCharBox().clone();
			hit.moveTo(getPositionX()-getSpeed(),getPositionY());
			System.out.println(hit.collidesWith(getEngine().getChar(2).getCharBox()));
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
			RectangleHitboxContract hit = (RectangleHitboxContract) this.getCharBox().clone();
			hit.moveTo(getPositionX()+getSpeed(),getPositionY());
			if(getEngine().getChar(1).getCharBox() != this.getCharBox()){
				if(!(hit.collidesWith(getEngine().getChar(1).getCharBox()))){
					positionX = Math.min(positionX + speed, getEngine().getWidth()-getCharBox().getWidth());
					getCharBox().moveTo(this.positionX, this.positionY);
				}
			}else if(getEngine().getChar(2).getCharBox() != this.getCharBox()){
				if(!(hit.collidesWith(getEngine().getChar(2).getCharBox()))){
					positionX = Math.min(positionX + speed, getEngine().getWidth()-getCharBox().getWidth());
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
		if (!notManipulable()) {
			
		}

	}

	@Override
	public void crouch() {
		if(!notManipulable()) {
			getCharBox().resize(getCharBox().getWidth(),(getCharBox().getHeight())/2);
		}

	}
	
	@Override
	public void startBlock() {
		isBlocking = true;

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
		case TECH_1:
			startTech(techs[0]);
			break;
		case TECH_2:
			startTech(techs[1]);
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
		
		if(isTeching()) {
			FightCharService autherFighter = getEngine().getChar(1) == this ?
					(FightCharService) getEngine().getChar(1):(FightCharService)getEngine().getChar(2);
				 
			techFrame++;
			if(techFrame <= tech.getSframe()) {
				
			} else if(techFrame > tech.getSframe() && techFrame <= tech.getHframe() && !isTechHasAlreadyHit) {
				if(tech.hitbox(getCharBox().getPositionX(),getCharBox().getPositionY()+(getCharBox().getHeight()-20)).collidesWith(autherFighter.getCharBox())) {
					isTechHasAlreadyHit = true;
					if(autherFighter.isBlocking()) {
						autherFighter.setBlokstunned(true);
					}
					else {
						autherFighter.setHitstunned(true);
						autherFighter.updateLife(tech.getDamage());
					}
				}
				
			}else if(techFrame >tech.getHframe() && techFrame <= tech.getRframe()) {
				isTechHasAlreadyHit= false;
			}
			else {
				isTech = false;
				tech = null;
			}
		}
	}
	

	public void setBlokstunned(boolean bool) {
		isBlockstunned = bool;
	}


	public void setHitstunned(boolean bool) {
		isHitstunned = bool;
	}
	
	public void updateLife(int damage) {
		if(damage > 0) {
			life= Math.max(0, life-damage);
		}
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