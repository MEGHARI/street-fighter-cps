package contracts;

import decorators.EngineDecorator;
import enums.COMMAND;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.CharacterService;
import services.EngineService;
import services.PlayerService;

public class EngineContract extends EngineDecorator {
	public EngineContract(EngineService delegate) {
		super(delegate);
	}

	/* Invariants */
	public void checkInvariant() {
		// \inv: isGameOver() == getChar(1).isDead() || getChar(2).isDead()
		if (getChar(1) != null && getChar(2) != null && !(isGameOver() == getChar(1).isDead() || getChar(2).isDead()))
			throw new InvariantError("Incohérence au niveau de la fin de partie");
	}

	@Override
	public int getHeight() {
		// run
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		// run
		return super.getWidth();
	}

	@Override
	public CharacterService getChar(int i) {
		// run
		return super.getChar(i);
	}

	@Override
	public PlayerService getPlayer(int i) {
		// run
		return super.getPlayer(i);
	}

	@Override
	public boolean isGameOver() {
		// run
		return super.isGameOver();
	}

	@Override
	public void init(int w, int h, int s, PlayerService p1, PlayerService p2) {

		// preconditions
		// \pre: h > 0
		if (!(h > 0))
			throw new PreconditionError("La hauteur est négative ou nulle");
		// \pre: s > 0
		if (!(s > 0))
			throw new PreconditionError("la distances entre  des deux joueurs est négative ou nulle");
		// \pre: w > s
		if (!(w > 0))
			throw new PreconditionError("La largeur est négative ou nulle");
		// \pre: p1 != p2
		if (!(p1 != p2))
			throw new PreconditionError("Les joueurs sont identiques");

		// run
		super.init(w, h, s, p1, p2);

		// postInvariants
		checkInvariant();

		// postConditions
		// \post: getHeight() == h
		if (!(getHeight() == h))
			throw new PostconditionError("La hauteur n'est pas correctement initialisée");
		// \post: getWidth() == w
		if (!(getWidth() == w))
			throw new PostconditionError("La largeur n'est pas correctement initialisée");
		// \post: getPlayer(1) == p1
		if (!(getPlayer(1) == p1))
			throw new PostconditionError("Le joueur 1 n'est pas correctement initialisé");
		// \post: getPlayer(2) == p2
		if (!(getPlayer(2) == p2))
			throw new PostconditionError("Le joueur 2 n'est pas correctement initialisé");
		// \post: getChar(1).getPositionX() == w/2 - s/2
		if (!(getChar(1).getPositionX() == w / 2 - s / 2))
			throw new PostconditionError("La position X du joueur 1 n'est pas correctement initialisée");
		// \post: getChar(2).getPositionX() == w/2 + s/2
		if (!(getChar(2).getPositionX() == w / 2 + s / 2))
			throw new PostconditionError("La position X du joueur 2 n'est pas correctement initialisée");
		// \post: getChar(1).getPositionY() == 0
		if (!(getChar(1).getPositionY() == 0))
			throw new PostconditionError("La position Y du joueur 1 n'est pas correctement initialisée");
		// \post: getChar(2).getPositionY() == 0
		if (!(getChar(2).getPositionY() == 0))
			throw new PostconditionError("La position Y du joueur 2 n'est pas correctement initialisée");
		// \post: getChar(1).faceRight()
		if (!(getChar(1).faceRight()))
			throw new PostconditionError("L'orientation du joueur 1 n'est pas correctement initialisée");
		// \post: !(getChar(2).faceRight())
		if ((getChar(2).faceRight()))
			throw new PostconditionError("L'orientation du joueur 2 n'est pas correctement initialisée");
	}

	@Override
	public void step(COMMAND c1, COMMAND c2) {

		// preConditions
		// pre: !isGameOver()
		if (!(!isGameOver()))
			throw new PostconditionError("La partie est terminée !!");

		// preInvariants
		checkInvariant();
		// capture

		// run
		super.step(c1, c2);

		// postInvariants
		checkInvariant();

		// postConditions
		// \post: getChar(1) = getChar(1)@pre.step(C1)

		// \post: getChar(2) = getChar(2)@pre.step(C2)

		// \post: if getChar(1).getPositionX() < getChar(2).getPositionX()
		// then !getChar(1).faceRight() && getChar(2).faceRight()
		if (getChar(1).getPositionX() < getChar(2).getPositionX()) {
			if (!(getChar(1).faceRight() && !getChar(2).faceRight())) {
				System.out.println(getChar(1).faceRight() +" | "+ getChar(2).faceRight());
				throw new PostconditionError("erreur au niveau du face a face");
			}
		}
	}

}
