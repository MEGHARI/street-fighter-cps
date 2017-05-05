package contracts;

import java.nio.CharBuffer;

import data.Tech;
import decorators.FightCharDecorator;
import enums.COMMAND;
import enums.NAME;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.EngineService;
import services.FightCharService;
import services.HitboxService;
import services.RectangleHitboxService;

public class FightCharContract extends CharacterContract implements FightCharService {

	public FightCharContract(FightCharService delegate) {
		super(delegate);
	}

	public FightCharService getDelegate() {
		return (FightCharService) super.getDelegate();
	}

	public void checkInvariant() {
		if (!(getDelegate().notManipulable() == getDelegate().isTeching() || getDelegate().isBlockstunned()
				|| getDelegate().isHitstunned()))
			throw new InvariantError("incohérrence au niveau de notManipulable ");

		super.checkInvariant();
	}

	// Observators

	@Override
	public boolean isBlocking() {
		return getDelegate().isBlocking();
	}

	@Override
	public boolean notManipulable() {
		return getDelegate().notManipulable();
	}

	@Override
	public boolean isBlockstunned() {
		return getDelegate().isBlockstunned();
	}

	@Override
	public boolean isHitstunned() {
		return getDelegate().isHitstunned();
	}

	@Override
	public boolean isTeching() {
		return getDelegate().isTeching();
	}

	@Override
	public boolean isCrouch() {
		return getDelegate().isCrouch();
	}
	@Override
	public int getJumpFrame() {
		return getDelegate().getJumpFrame();
	}
	@Override
	public boolean isJump() {
		return getDelegate().isJump();
	}

	@Override
	public Tech getTech() {
		// preCondition
		// pre tech() requires isTeching()
		if (!isTeching()) {
			throw new PreconditionError("ya pas eu de lancement de la technique)");
		}
		// run
		return getDelegate().getTech();
	}

	@Override
	public int getTechFrame() {
		// pre : techFrame() requires isTeching()
		if (!isTeching()) {
			throw new PreconditionError("ya pas eu de lancement de la technique");
		}
		return getDelegate().getTechFrame();
	}

	@Override
	public boolean isTechHasAlreadyHit() {
		// pre techHasAlreadyHit() requires isTeching()
		if (!isTeching()) {
			throw new PreconditionError("ya pas eu de lancement de la technique");
		}

		return getDelegate().isTechHasAlreadyHit();
	}

	// Operators:
	public void init(NAME name, int l, int s, boolean f, EngineService e, RectangleHitboxService rh) {
		// preconditions
		// pre: l > 0
		if (!(l > 0))
			throw new PreconditionError("Vie négative ou nulle");
		// pre: s > 0
		if (!(s > 0))
			throw new PreconditionError("Vitesse négative ou nulle");

		// run
		getDelegate().init(name, l, s, f, e, rh);

		// postInvariants
		checkInvariant();

		// postConditions
		// \post : getName() = name
		if (!(getName() == name))
			throw new PostconditionError("initialisation du nom incorrect");
		// post: getLife() == l
		if (!(getLife() == l))
			throw new PostconditionError("initialisation de la vie incorrect");
		// post: getSpeed() == s
		if (!(getSpeed() == s))
			throw new PostconditionError("initialisation de la vitesse incorrect");
		// post: faceRight() == f
		if (!(faceRight() == f))
			throw new PostconditionError("initialisation de l'orientation incorrect");
		// post: getEngine() == e
		if (!(getEngine() == e))
			throw new PostconditionError("initialisation du moteur de jeu incorrect");
		// post: getCharBox() == rh
		if (!(getCharBox() == rh))
			throw new PostconditionError("initialisation de la hitbox  incorrect");
		if (notManipulable())
			throw new PostconditionError("le figghter doit etre manipulable a linitiialisation");
		if (isBlocking())
			throw new PostconditionError("le fighter doit etre sans protection a linitialisation");

	}

	public void startTech(Tech tech) {

		// precondition
		// \pre: startTech(tech) requires ¬notManipulable()

		if (notManipulable()) {
			throw new PreconditionError("le personnage n'est pas sous control");
		}
		getDelegate().startTech(tech);
	}

	@Override
	public void jump() {
		// precondition
		// \pre: startTech(tech) requires ¬notManipulable()
		if (notManipulable()) {
			throw new PreconditionError("le personnage n'est pas sous control");
		}
		// preInvariants
		checkInvariant();

		// captures
		int positionxPre = super.getPositionX();
		int positionyPre = super.getPositionY();
		// run
		getDelegate().jump();

		// preInvariants
		checkInvariant();

		// postCondition
		// \post : getPositionX()@pre=getPositionX()
		if (!(positionxPre == getPositionY())) {
			throw new PostconditionError("erreur de positionnement lors du jump 'X'");
		}

		// \post : getPositionY()@pre=getPositionY()
		if (!(positionyPre == getPositionY())) {
			throw new PostconditionError("erreur de positionnement lors du jump 'Y'");
		}
	}

		// pre: !notManipulable() && !isCrouch()
		// \post : getPositionX()@pre=getPositionX()
		// \post : getPositionY()@pre=getPositionY()
		// \post : getCharBox().getHeight() = getCharBox()@pre.getHeight()/2

	@Override
	public void crouch() {
		// precondition
		// \pre:  ¬notManipulable()
		if (notManipulable()) {
			throw new PreconditionError("le personnage n'est pas sous control");
		}
		// \pre: ¬isCrouch()
		if (isCrouch()) {
			throw new PreconditionError("le personnage est dejas en mode crouche");
		}

		// preInvariants
		checkInvariant();

		// captures
		int positionxPre = getDelegate().getPositionX();
		int positionyPre = getDelegate().getPositionY();
		int heightHitBoxPre = getDelegate().getCharBox().getHeight();
		boolean isCrouchPre = getDelegate().isCrouch();
		// run
		getDelegate().crouch();

		// preInvariants
		checkInvariant();

		// postCondition
		// \post : getPositionX()@pre=getPositionX()
		if (!(positionxPre == getPositionX())) {
			throw new PostconditionError("erreur de positionnement lors du crouch 'X'");
		}

		// \post : getPositionY()@pre=getPositionY()
		if (!(positionyPre == getPositionY())) {
			throw new PostconditionError("erreur de positionnement lors du crouch 'Y'");
		}
		// \post :getCharBox().getHeight() = getCharBox()@pre.getHeight()/2
			if (!(getDelegate().getCharBox().getHeight() == heightHitBoxPre /2 ))
				throw new PostconditionError("erreur du redimensienement de l hitbox du fighter");

	}

	@Override
	public void rise() {
		// precondition
		// \pre: ¬notManipulable()
		if (notManipulable()) {
			throw new PreconditionError("le personnage n'est pas sous control");
		}
		// \pre : isCrouch()
		if (isCrouch()) {
			throw new PreconditionError("le personnage n'est pas sous control");
		}

		// preInvariants
		checkInvariant();

		// captures
		int positionxPre = getDelegate().getPositionX();
		int positionyPre = getDelegate().getPositionY();
		int heightHitBoxPre = getDelegate().getCharBox().getHeight();
		// run
		getDelegate().rise();

		// preInvariants
		checkInvariant();

		// postCondition
		// \post : getPositionX()@pre=getPositionX()
		if (!(positionxPre == getPositionX())) {
			throw new PostconditionError("erreur de positionnement lors du crouch 'X'");
		}

		// \post : getPositionY()@pre=getPositionY()
		if (!(positionyPre == getPositionY())) {
			throw new PostconditionError("erreur de positionnement lors du crouch 'Y'");
		}
		// \post : getCharBox().getHeight() = getCharBox()@pre.getHeight()*2
			if (!(getDelegate().getCharBox().getHeight() == heightHitBoxPre * 2))
				throw new PostconditionError("erreur du redimensienement de l hitbox du fighter");

	}

	@Override
	public void startBlock() {
		// precondition
		// \pre: startTech(tech) requires ¬notManipulable() ¬isBlocking()
		if (notManipulable() || isBlocking()) {
			throw new PreconditionError("le personnage n'est pas sous controle");
		}

		// preInvariant
		checkInvariant();

		// run
		getDelegate().startBlock();

		// postCondition
		// post : isBlocking()=false
		if (isBlocking()) {
			throw new PreconditionError("le personnage est tjr en mode protection");
		}

	}

	@Override
	public void moveLeft() {
		System.out.println("je bouge");
		// preInvariant
		checkInvariant();
		// captures
		int positionXPre = getPositionX();
		int positionYPre = getPositionY();
		int speedPre = getSpeed();
		int lifePre = getLife();
		boolean faceRightPre = faceRight();
		EngineService enginePre = getEngine();
		boolean notManipulablePre = getDelegate().notManipulable();
		boolean isBlockingPre = getDelegate().isBlocking();

		RectangleHitboxContract hitPost = (RectangleHitboxContract) getCharBox().clone();
		hitPost.moveTo(positionXPre - speedPre, positionYPre);

		// run
		getDelegate().moveLeft();
		// postInvariants
		checkInvariant();
		// postConditions
		// \post (notManipulable()@pre || isBlocking()@pre) => getPositionX() =
		// getPositionX()@pre
		if (notManipulablePre || isBlockingPre)
			if ((getPositionX() == positionXPre))
				throw new PostconditionError("le fighter ne peut pas se deplacer a gauche");
		// \post: \exists i:int if ( !(notManipulable()@pre || isBlocking()@pre)
		// && getEngine()@pre.getChar(i) != self &&
		// getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox()) )
		// then getPositionX() == getPositionX()@pre

		boolean collision = false;
		for (int i = 1; i < 3; i++) {
			if ((!(notManipulablePre || isBlockingPre)) && enginePre.getChar(i) != this
					&& hitPost.collidesWith((RectangleHitboxContract) enginePre.getChar(i).getCharBox())) {
				collision = true;
				if (getPositionX() != positionXPre)
					throw new PostconditionError("erreur de position de x");
			}

		}
		/// \post: if ( !(notManipulable()@pre || isBlocking()@pre) &&
		/// getPositionX()@pre <= getSpeed()) &&
		// (\exist i: int { getEngine()@pre.getChar(i) == self)
		// ||
		/// !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		// then getPositionX() == 0
		if (positionXPre <= speedPre && !collision) {
			for (int i = 1; i < 3; i++) {
				if ((!(notManipulablePre || isBlockingPre)) && ((enginePre.getChar(i) == this)
						|| (!hitPost.collidesWith((RectangleHitboxContract) enginePre.getChar(i).getCharBox())))) {
					if (getPositionX() != 0) {
						throw new PostconditionError("erreur de positions");
					}
				}
			}
		}
		// \post: if ( !(notManipulable()@pre || isBlocking()@pre) &&
		// getPositionX()@pre > getSpeed()) &&
		// (\exists i: int { getEngine()@pre.getChar(i) == self)
		// ||
		// !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		// then getPositionX() == getPositionX()@pre -getSpeed()@pre
		else if (positionXPre > speedPre && !collision) {
			for (int i = 1; i < 3; i++) {
				if ((!(notManipulablePre || isBlockingPre)) && ((enginePre.getChar(i) == this)
						|| (!hitPost.collidesWith(enginePre.getChar(i).getCharBox()))))
					if (getPositionX() != positionXPre - speedPre)
						throw new PostconditionError("erreur de position");
			}
		}
		// \post: faceRight() == faceRight()@pre && getLife() == getLife()@pre
		if (!(faceRight() == faceRightPre && getLife() == lifePre))
			throw new PostconditionError("L'orientation ou la vie erronée");
		// \post: getPositionY() == getPositionY()@pre
		if (!(getPositionY() == positionYPre))
			throw new PostconditionError("La position Y erronée");
	}

	@Override
	public void moveRight() {
		// preInvariant
		checkInvariant();
		// captures
		int positionXPre = getPositionX();
		int positionYPre = getPositionY();
		int speedPre = getSpeed();
		int lifePre = getLife();
		boolean faceRightPre = faceRight();
		EngineService enginePre = getEngine();
		boolean notManipulablePre = getDelegate().notManipulable();
		boolean isBlockingPre = getDelegate().isBlocking();
		RectangleHitboxContract hitPost = (RectangleHitboxContract) getCharBox().clone();
		hitPost.moveTo(positionXPre + speedPre, positionYPre);

		// run
		getDelegate().moveRight();
		// postInvariants
		checkInvariant();
		// postConditions
		// \post (notManipulable()@pre || isBlocking()@pre) => getPositionX() =
		// getPositionX()@pre
		if (notManipulablePre || isBlockingPre)
			if (!(getPositionX() == positionXPre))
				throw new PostconditionError("le fighter ne peut pas se deplacer a droite");

		// \post: \forall i:int if ( !(notManipulable()@pre || isBlocking()@pre)
		// && getEngine()@pre.getChar(i) != self &&
		// getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox()) )
		// then getPositionX() == getPositionX()@pre
		boolean collision = false;
		for (int i = 1; i < 3; i++) {
			if ((!(notManipulablePre || isBlockingPre)) && enginePre.getChar(i) != this
					&& hitPost.collidesWith((RectangleHitboxContract) enginePre.getChar(i).getCharBox())) {
				collision = true;
				if (getPositionX() != positionXPre)
					throw new PostconditionError("erreur de position de x");
			}
		}
		// \post: if (!(notManipulable()@pre || isBlocking()@pre) &&
		// (getPositionX()@pre <=
		// getEngine()@pre.getWidth()-getSpeed()@pre-getCharBox().getWidth()) &&
		// (\forall i: int { getEngine()@pre.getChar(i) == self)
		// ||
		// !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		// then getPositionX() == getPositionX()@pre+getSpeed()@pre
		if (positionXPre <= enginePre.getWidth() - getCharBox().getWidth() - speedPre && !collision) {
			for (int i = 1; i < 3; i++) {
				if ((!(notManipulablePre || isBlockingPre)) && ((enginePre.getChar(i) == this)
						|| (!hitPost.collidesWith(enginePre.getChar(i).getCharBox())))) {
					if (getPositionX() != positionXPre + speedPre)
						throw new PostconditionError("erreur de positionnement (moveRight)");
				}
			}
		}
		// \post: if ( !(notManipulable()@pre || isBlocking()@pre)
		// &&(getPositionX()@pre > getEngine()@pre.getWidth()-getSpeed()@pre
		// -getCharBox().getWidth()) &&
		// (\exists i: int { getEngine()@pre.getChar(i) == self)
		// ||
		// !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		// then getPositionX() == getEngine()@pre.getWidth() -
		// getCharBox().getWith()
		else if (positionXPre > enginePre.getWidth() - getCharBox().getWidth() - speedPre && !collision) {
			for (int i = 1; i < 3; i++) {
				if ((!(notManipulablePre || isBlockingPre)) && ((enginePre.getChar(i) == this)
						|| (!hitPost.collidesWith(enginePre.getChar(i).getCharBox())))) {
					if (getPositionX() != (enginePre.getWidth() - getCharBox().getWidth()))
						throw new PostconditionError("erreur de positionement (moveRight)");
				}
			}
		}
		// \post: faceRight() == faceRight()@pre && getLife() == getLife()@pre
		if (!(faceRight() == faceRightPre && getLife() == lifePre))
			throw new PostconditionError("L'orientation ou la vie erronée");
		// \post: getPositionY() == getPositionY()@pre
		if (!(getPositionY() == positionYPre))
			throw new PostconditionError("La position Y erronée");
	}

	@Override
	public void switchSide() {

		// preInvariants
		checkInvariant();

		// captures
		boolean faceRightPre = faceRight();
		int positionXPre = getPositionX();
		int positionYPre = getPositionY();
		boolean notManipulablePre = getDelegate().notManipulable();
		boolean isBlockingPre = getDelegate().isBlocking();

		// run
		getDelegate().switchSide();

		// postInvariants
		checkInvariant();

		// postConditions
		// post:(notManipulable()@pre || isBlocking()@pre) => faceRight() =
		// faceRight()@pre

		if (notManipulablePre || isBlockingPre)
			if (!(faceRight() == faceRightPre))
				throw new PostconditionError("L'orientation devra pas etre changé");
		if (!(notManipulablePre || isBlockingPre))
			if ((faceRight() == faceRightPre))
				throw new PostconditionError("L'orientation n'a pas changé");
		// \post: getPositionX() == getPositionX()@pre
		if (!(getPositionX() == positionXPre))
			throw new PostconditionError("La position X a changé");
		// \post: getPositionY() == getPositionY()@pre
		if (!(getPositionY() == positionYPre))
			throw new PostconditionError("La position Y a changé");
	}

	@Override
	public void setBlokstunned(boolean b) {
		// preCondition

		// preInvariants
		checkInvariant();

		// run
		getDelegate().setBlokstunned(b);

		// postInvariants
		checkInvariant();

		// postInvariants
		// post : getBlokstunned = b
		if (!(getDelegate().isBlockstunned() == b))
			throw new PostconditionError("erreur d'initialisation 'Blockstunned' ");

	}

	public void setHitstunned(boolean h) {
		// preCondition

		// preInvariants
		checkInvariant();

		// run
		getDelegate().setHitstunned(h);

		// postInvariants
		checkInvariant();

		// postInvariants
		// post : getHitstunned() = h
		if (!(getDelegate().isHitstunned() == h))
			throw new PostconditionError("erreur d'initialisation 'Histstunned' ");
	}

	public void updateLife(int damage) {
		// precondition
		// pre : damage > 0
		if (!(damage > 0))
			throw new PreconditionError("le damage doit etre positif ");

		// preInvariant
		checkInvariant();

		// captures
		int lifePre = getLife();

		// run
		getDelegate().updateLife(damage);

		// postInvariants
		checkInvariant();

		// postConditions
		// post : getLife()@pre = > dammage -> getLife() = getLife()@pre -
		// damage
		if ((lifePre >= damage)) {
			if (!(getLife() == lifePre - damage))
				throw new PostconditionError("la vie doit etre etre == lifePre - damage");
		}
		// post : getLife()@pre < damage -> getLife() =0
		else if (lifePre < damage) {
			if (!(getLife() == 0))
				throw new PostconditionError("la vie doit etre etre == 0");

		}
	}
	
	@Override
	public void startJump() {
		// precondition
		// pre : !isJump()
		// post: isJump() = true
		if(isJump())
			throw new PreconditionError("le personage est dejas en mode jump");
		//pre :!isHitstunned()
		if(isHitstunned())
			throw new PreconditionError("le personnage est etourdie");
		// preInvariant
		checkInvariant();
		
		getDelegate().startJump();
		
		// postInvariant
		checkInvariant();
		
		// postCondition
		if(!(isJump()))
			throw new PostconditionError("le personnage devra etre en mode jump");
	}

	@Override
	public void step(COMMAND c) {
		// super.step(c);
		// \post : c == JUMP => (step(c) == jump())
		// \post : c == CROUCH => (step(c) == crouch())
		// \post : c == LEFT => (step(c) == moveLeft(c))
		// \post : c == RIGHT => (step(c) == moveRIGHT(c))
		// \post : c==NEUTRAL => (step(c) == step(c)@pre)
		getDelegate().step(c);
	}

	@Override
	public FightCharContract clone() {
		return new FightCharContract(getDelegate().clone());
	}

	@Override
	public RectangleHitboxService getCharBox() {
		return getDelegate().getCharBox();
	}
}
