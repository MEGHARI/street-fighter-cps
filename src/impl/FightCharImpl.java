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
	private Tech[] techs;
	private RectangleHitboxContract hitTechs[];
	private RectangleHitboxService charBox;
	public int cptHstunned ;
	public int cptBstunned ;
	private int height;
	private boolean isJump;
	private boolean isCrouch = false;
	public int jumpFrame;

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
	public boolean  isJump() {
		return isJump;
	}
	@Override
	public int getJumpFrame() {
		return jumpFrame;
	}

	@Override
	public void startTech(Tech tech) {
		if (!notManipulable()) {
			isTech = true;
			this.tech = tech;
			this.techFrame = 0;
			System.out.println("strat tech");
		}

	}

	@Override
	public void moveLeft() {
		if (!(notManipulable() || isBlocking())) {
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

	public void startJump() {
		if (!isJump && !notManipulable()) {
			isJump = true;
			jumpFrame = 0;
		}
	}

	@Override
	public void jump() {
		jumpFrame++;
		if (jumpFrame == 1) {
			this.positionY = 50;
			this.getCharBox().moveTo(this.getPositionX(), this.positionY);
	
		} else if (jumpFrame == 2) {
			this.positionY = 100;
			this.getCharBox().moveTo(this.getPositionX(), this.positionY);
		} else if (jumpFrame == 3) {
			this.positionY = 150;
			this.getCharBox().moveTo(this.getPositionX(), this.positionY);

		} else if (jumpFrame == 4) {
			this.positionY = 150;
			this.getCharBox().moveTo(this.getPositionX(), this.positionY);

		} else if (jumpFrame == 5) {
			this.positionY = 100;
			this.getCharBox().moveTo(this.getPositionX(), this.positionY);

		}else if (jumpFrame == 6) {
			this.positionY = 50;
			this.getCharBox().moveTo(this.getPositionX(), this.positionY);

		} else if (jumpFrame == 7) {
			this.positionY = 0;
			this.getCharBox().moveTo(this.getPositionX(), this.positionY);
			jumpFrame = 0;
			isJump = false;
			System.out.println("je suis dans la 6");

		}
		
	}

	@Override
	public void crouch() {
		if (!notManipulable()) {
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
		if (!isDead()) {
			FightCharContract autherFighter = getEngine().getChar(1).getCharBox() == this.getCharBox()
					? (FightCharContract) (getEngine().getChar(2))
					: (FightCharContract) (getEngine().getChar(1));
			RectangleHitboxContract rectech = new RectangleHitboxContract(new RectangleHitboxImpl());

			if (isTech) {

				this.techFrame++;
				System.out.println(techFrame);
				if (getCharBox() == getEngine().getChar(2).getCharBox()) {
					if (getTech() == techs[0]) {
						rectech.init(this.getPositionX() - 130, getCharBox().getHeight() - 50, 130, 50);
						System.out.println("techs[0]");
					} else

						rectech.init(this.getPositionX() - 130, getCharBox().getHeight() - 50, 130, 50);

				} else {
					if (getTech() == techs[0]) {
						rectech.init(this.getPositionX() + getCharBox().getWidth(), getCharBox().getHeight() - 50, 130,
								50);
					} else
						rectech.init(this.getPositionX() + getCharBox().getWidth(), 0, 130, 50);

				}
				if (techFrame > tech.getSframe() && techFrame <= tech.getHframe() + tech.getSframe()
						&& !isTechHasAlreadyHit) {

					if (tech.hitbox(rectech.getPositionX(), rectech.getPositionY(), rectech.getWidth(),
							rectech.getHeight()).collidesWith(autherFighter.getCharBox()) && !isTechHasAlreadyHit) {
						isTechHasAlreadyHit = true;
						if (autherFighter.isBlocking()) {
							((FightCharImpl) autherFighter.getDelegate()).cptBstunned = this.getTech().getBstun();
							autherFighter.setBlokstunned(true);
							System.out.println("l'adversaire s'est protégé");
						} else {
							((FightCharImpl) autherFighter.getDelegate()).cptHstunned = this.getTech().getHstun();
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
					tech = null;
				}
			}

			if (isBlockstunned) {
				cptBstunned--;
				if (cptBstunned == 1) {
					this.setBlokstunned(false);
				}

			}
			if (isHitstunned) {
				cptHstunned--;
				if (cptHstunned == 1) {
					this.setHitstunned(false);

				}
			}
			if(isJump)
				jump();
			switch (c) {
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			case JUMP:
				rise();
				startJump();
				break;
			case CROUCH:
				crouch();
				break;
			case TECH_1:
				if (isCrouch)
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

		} else
			System.out.println("le personnage est mort");

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
	public FightCharImpl clone() {
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
