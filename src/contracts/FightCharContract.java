package contracts;

import data.Tech;
import decorators.FightCharDecorator;
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
		// \pre: startTech(tech) requires ¬notManipulable()
		if (notManipulable()) {
			throw new PreconditionError("le personnage n'est pas sous controle");
		}
		
		// preInvariant
		checkInvariant();
		
		// run
		getDelegate().startBlock();
		
		// postCondition
		// post : isBlocking()=false
		if(isBlocking()) {
			throw new PreconditionError("le personnage est tjr en mode protection");
		}
	}
}
