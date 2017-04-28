package contracts;

import data.Tech;
import decorators.FightCharDecorator;
import enums.COMMAND;
import enums.NAME;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.EngineService;
import services.FightCharService;

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

	// Constructors
	@Override
	public void init(NAME name, int l, int s, boolean f, EngineService e) {
		// pre
		// run
		super.init(name, l, s, f, e);
		// postCondition
		// \post: notManipulable(C) = false
		if (notManipulable())
			throw new PostconditionError("le figghter doit etre manipulable a linitiialisation");
		if (isBlocking())
			throw new PostconditionError("le fighter doit etre sans protection a linitialisation");

	}
	// Operators:

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

	@Override
	public void crouch() {
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
		getDelegate().crouch();

		// preInvariants
		checkInvariant();

		// postCondition
		// \post : getPositionX()@pre=getPositionX()
		if (!(positionxPre == getPositionY())) {
			throw new PostconditionError("erreur de positionnement lors du crouch 'X'");
		}

		// \post : getPositionY()@pre=getPositionY()
		if (!(positionyPre == getPositionY())) {
			throw new PostconditionError("erreur de positionnement lors du crouch 'Y'");
		}
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

		for (int i = 0; i < 2; i++) {
			if ((!(notManipulablePre || isBlockingPre)) && enginePre.getChar(i) != this
					&& getCharBox().collidesWith(enginePre.getChar(i).getCharBox())) {
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
		for (int i = 0; i < 2; i++) {
			if ((!(notManipulablePre || isBlockingPre)) && (positionXPre <= speedPre) && (enginePre.getChar(i) == this)
					|| (!getCharBox().collidesWith(enginePre.getChar(i).getCharBox()))) {
				if (getPositionX() != 0) {
					throw new PostconditionError("erreur de positions");
				}
			}
		}
		// \post: if ( !(notManipulable()@pre || isBlocking()@pre) &&
		// getPositionX()@pre > getSpeed()) &&
		// (\exists i: int { getEngine()@pre.getChar(i) == self)
		// ||
		// !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		// then getPositionX() == getPositionX()@pre -getSpeed()@pre
		for (int i = 0; i < 2; i++) {
			if ((!(notManipulablePre || isBlockingPre)) && (positionXPre > speedPre) && (enginePre.getChar(i) == this)
					|| (!getCharBox().collidesWith(enginePre.getChar(i).getCharBox())))
				if (getPositionX() != positionXPre - speedPre)
					throw new PostconditionError("erreur de position");
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

		// \post: \exists i:int if ( !(notManipulable()@pre || isBlocking()@pre)
		// && getEngine()@pre.getChar(i) != self &&
		// getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox()) )
		// then getPositionX() == getPositionX()@pre
		for (int i = 0; i < 2; i++) {
			if ((!(notManipulablePre || isBlockingPre)) && enginePre.getChar(i) != this
					&& getCharBox().collidesWith(enginePre.getChar(i).getCharBox())) {
				if (getPositionX() != positionXPre)
					throw new PostconditionError("erreur de position de x");
			}
		}
		// \post: if (!(notManipulable()@pre || isBlocking()@pre) &&
		// (getPositionX()@pre <= getEngine()@pre.getWidth()-getSpeed()@pre) &&
		// (\exists i: int { getEngine()@pre.getChar(i) == self)
		// ||
		// !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		// then getPositionX() == getPositionX()@pre+getSpeed()@pre
		for (int i = 0; i < 2; i++) {
			if ((!(notManipulablePre || isBlockingPre)) && (positionXPre <= enginePre.getWidth() - speedPre)
					&& (enginePre.getChar(i) == this)
					|| (!getCharBox().collidesWith(enginePre.getChar(i).getCharBox()))) {
				if (getPositionX() != positionXPre - speedPre)
					throw new PostconditionError("erreur de positionnement (moveRight)");
			}
		}
		// \post: if ( !(notManipulable()@pre || isBlocking()@pre)
		// &&(getPositionX()@pre > getEngine()@pre.getWidth()-getSpeed()@pre) &&
		// (\exists i: int { getEngine()@pre.getChar(i) == self)
		// ||
		// !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		// then getPositionX() == getEngine()@pre.getWidth()
		for (int i = 0; i < 2; i++) {
			if ((!(notManipulablePre || isBlockingPre)) && (positionXPre > enginePre.getWidth() - speedPre)
					&& (enginePre.getChar(i) == this)
					|| (!getCharBox().collidesWith(enginePre.getChar(i).getCharBox()))) {
				if (getPositionX() != enginePre.getWidth())
					throw new PostconditionError("erreur de positionement (moveRight)");
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
		switchSide();
		
		// postInvariants
		checkInvariant();
		
		// postConditions
		// post:(notManipulable()@pre || isBlocking()@pre) => faceRight() = faceRight()@pre
		
		if(notManipulablePre || isBlockingPre)
			if (!(faceRight() == faceRightPre))
			    throw new PostconditionError("L'orientation devra pas etre changé");
		if(!(notManipulablePre || isBlockingPre))
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
	public void step(COMMAND c) {
		super.step(c);
		// \post : c == JUMP => (step(c) == jump())
		// \post : c == CROUCH => (step(c) == crouch())
	}
	
	@Override
	public FightCharContract clone(){
		return new FightCharContract(getDelegate().clone());
	}
}
