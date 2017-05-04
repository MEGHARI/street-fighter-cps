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

public class FightCharBugImpl extends CharacterImpl implements FightCharService {

	private int techFrame;
	private boolean isBlocking;
	private boolean isBlockstunned;
	private boolean isHitstunned;
	private boolean isTech;
	private boolean isTechHasAlreadyHit;
	private Tech tech;
	public Tech[] techs;
	private RectangleHitboxContract hitTechs[];
	private RectangleHitboxService charBox;
	private int cptHstunned = 0;
	private int cptBstunned = 0;
	private int height;
	private boolean isCrouch = false;


	@Override
	public void init(NAME name, int l, int s, boolean f, EngineService e, RectangleHitboxService rh) {
		super.init(name, l, s, f, e);
		rh.moveTo(getPositionX(), getPositionY());
		isBlocking = false;
		height = rh.getHeight();
		this.charBox = rh;
		techs = new Tech[] { new Tech(30, 5, 3, 2, 5, 2), new Tech(10, 2, 3, 2, 4, 2) };
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
	public boolean isCrouch() {
		return isCrouch;
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
		return isTechHasAlreadyHit;
	}

	@Override
	public void startTech(Tech tech) {
		if (!notManipulable()) {
			// bug
			this.tech = tech;
			this.techFrame = 0;
		}

	}

	@Override
	public void moveLeft() {
		// bug
			RectangleHitboxContract hit = (RectangleHitboxContract) this.getCharBox().clone();
			hit.moveTo(getPositionX() - getSpeed(), getPositionY());
			if (getEngine().getChar(1).getCharBox() != this.getCharBox()) {
				if (!(hit.collidesWith(getEngine().getChar(1).getCharBox()))) {
					positionX = Math.max(0, positionX - (speed));
					getCharBox().moveTo(this.positionX, this.positionY);
				}
			} else if (getEngine().getChar(2).getCharBox() != this.getCharBox()) {
				if (!(hit.collidesWith(getEngine().getChar(2).getCharBox()))) {
					positionX = Math.max(0, positionX - speed);
					getCharBox().moveTo(this.positionX, this.positionY);
				}
			}

		
	}

	@Override
	public void moveRight() {
		if (!(notManipulable() || isBlocking())) {
			RectangleHitboxContract hit = (RectangleHitboxContract) this.getCharBox().clone();
			hit.moveTo(getPositionX() + getSpeed(), getPositionY());
			if (getEngine().getChar(1).getCharBox() != this.getCharBox()) {
				if (!(hit.collidesWith(getEngine().getChar(1).getCharBox()))) {
					positionX = Math.min(positionX + speed, getEngine().getWidth() - getCharBox().getWidth());
					getCharBox().moveTo(this.positionX, this.positionY);
				}
			} else if (getEngine().getChar(2).getCharBox() != this.getCharBox()) {
				if (!(hit.collidesWith(getEngine().getChar(2).getCharBox()))) {
					positionX = Math.min(positionX + speed, getEngine().getWidth() - getCharBox().getWidth());
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
		// bug
		if (notManipulable()) {
			if (!isCrouch) {
				getCharBox().resize(getCharBox().getWidth(), (getCharBox().getHeight()) / 2);
				isCrouch = true;
			}
		}

	}

	@Override
	public void rise() {
		if (!notManipulable()) {
			if (isCrouch) {
				getCharBox().resize(getCharBox().getWidth(), (getCharBox().getHeight()) * 2);
				isCrouch = false;

			}
		}

	}

	@Override
	public void startBlock() {
		isBlocking = true;

	}

	@Override
	public void step(COMMAND c) {
		// bug
		if (isDead()) {
			FightCharContract autherFighter = getEngine().getChar(1).getCharBox() == this.getCharBox()
					? (FightCharContract) (getEngine().getChar(2))
					: (FightCharContract) (getEngine().getChar(1));
			RectangleHitboxContract rectech = new RectangleHitboxContract(new RectangleHitboxImpl());
			

			if (isTech) {
				
				this.techFrame++;
				System.out.println(techFrame);
				if (getCharBox() == getEngine().getChar(2).getCharBox()) {
					if(getTech() == techs[0]) {
						rectech.init(this.getPositionX() - 130, getCharBox().getHeight() - 50, 130, 50);
					System.out.println("techs[0]");	
					}
					else
						
						rectech.init(this.getPositionX() - 130, getCharBox().getHeight() - 50, 130, 50);

				} else {
					if(getTech() == techs[0]) {
						rectech.init(this.getPositionX() + getCharBox().getWidth(), getCharBox().getHeight() - 50, 130, 50);
					}
					else
						rectech.init(this.getPositionX() + getCharBox().getWidth(),0, 130, 50);
						//rectech.init(this.getPositionX() + getCharBox().getWidth(), getCharBox().getHeight() - 50, 130, 50);

				}
				if (techFrame > tech.getSframe() && techFrame <= tech.getHframe() + tech.getSframe()
						&& !isTechHasAlreadyHit) {

					if (tech.hitbox(rectech.getPositionX(), rectech.getPositionY(), rectech.getWidth(),
							rectech.getHeight()).collidesWith(autherFighter.getCharBox()) && !isTechHasAlreadyHit) {
						isTechHasAlreadyHit = true;
						System.out.println("essa");
						if (autherFighter.isBlocking()) {
							autherFighter.setBlokstunned(true);
							System.out.println("l'adversaire s'est protégé");
						} else {
							autherFighter.setHitstunned(true);
							autherFighter.updateLife(tech.getDamage());
							System.out.println("l'adversaire ne s'est pas protégé");
						}
					}

				} else if (techFrame > tech.getHframe() + tech.getSframe()
						&& techFrame <= tech.getRframe() + tech.getHframe() + tech.getSframe()) {
					isTechHasAlreadyHit = false;
				} else if (techFrame > tech.getRframe() + tech.getHframe() + tech.getSframe()) {
					isTech = false;
					//tech = null;
				}
			} else if (isBlockstunned) {
				cptBstunned++;
				if (cptBstunned == autherFighter.getTech().getBstun()) {
					this.setBlokstunned(false);
				}

			} else if (isHitstunned) {
				cptHstunned++;
				if (cptHstunned >autherFighter.getTech().getHstun()) {
					this.setHitstunned(false);

				}
			} else {

				switch (c) {
				case LEFT:
					moveLeft();
					break;
				case RIGHT:
					moveRight();
					break;
				case JUMP:
					rise();
					jump();
					break;
				case CROUCH:
					crouch();
					break;
				case TECH_1:
					if(isCrouch)
						startTech(techs[1]);
					else
						startTech(techs[0]);
					break;
				case TECH_2:
					startTech(techs[1]);
					break;
				case PROTECT:
					startBlock();
					break;
				case RIZE:
					rise();
					break;
				case ENDPROTECT:
					isBlocking = false;
					break;
				default:
					break;
				}
			}
		} else
			System.out.println();

	}

	public void setBlokstunned(boolean bool) {
		isBlockstunned = bool;
	}

	public void setHitstunned(boolean bool) {
		isHitstunned = bool;
	}

	public void updateLife(int damage) {
		if (damage > 0) {
			life = Math.max(0, life - damage);
		}
	}

	@Override
	public FightCharBugImpl clone() {
		FightCharBugImpl fci = new FightCharBugImpl();
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
