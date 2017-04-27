package contracts;

import decorators.HitboxDecorator;
import errors.PostconditionError;
import services.HitboxService;

public class HitboxContract extends HitboxDecorator {

	public HitboxContract(HitboxService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// \inv: collidesWith(h) == \exists x, y: int x int { belongsTo(x, y) &&
		// h.belongsTo(x, y) }

		// \inv: equalsTo(h) == \forall x: int x int { belongsTo(x, y) ==
		// h.belongsTo(x, y) }

	}

	@Override
	public int getPositionX() {
		return super.getPositionX();
	}

	@Override
	public int getPositionY() {
		return super.getPositionY();
	}

	@Override
	public boolean belongsTo(int x, int y) {
		return super.belongsTo(x, y);
	}

	@Override
	public boolean collidesWith(HitboxService h) {
		return super.collidesWith(h);
	}

	@Override
	public boolean equalsTo(HitboxService h) {
		return super.equalsTo(h);
	}

	@Override
	public void init(int x, int y) {
		// run
		super.init(x, y);
		// postInvariants

		checkInvariant();

		// postConditions

		// \post: getPositionX() == x
		if (!(getPositionX() == x))
			throw new PostconditionError("erreur au niveau de la position (x)");
		// \post: getPositionY() == y
		if (!(getPositionY() == y))
			throw new PostconditionError("erreur au niveau de la postion (y)");
	}

	@Override
	public void moveTo(int x, int y) {
		// preInvariants
		checkInvariant();

		// captures
		boolean belongsToCentrePre = belongsTo(getPositionX(), getPositionY());
		boolean belongsToCentre100Pre = belongsTo(getPositionX() + 100, getPositionY() + 100);
		int getPositionXPre = getPositionX();
		int getPositionYPre = getPositionY();
		boolean belongsToAbsPre = belongsTo(300, 0);

		// run
		super.moveTo(x, y);

		// postInvariants
		checkInvariant();

		// postConditions
		// \post: getPositionX() == x
		if (!(getPositionX() == x))
			throw new PostconditionError("La position en X est incorrect");
		// \post: getPositionY() == y
		if (!(getPositionY() == y))
			throw new PostconditionError("La position en Y est incorrect");
		// \post: \forall u, v: int x int { belongsTo(u, v) ==
		// belongsTo(u-(x-getPositionX()@pre), v-(y-getPositionY()@pre))@pre }

		// test du centre
		if (!belongsTo(getPositionX(), getPositionY()) == belongsToCentrePre)
			throw new PostconditionError("Position incorrecte");
		// centre + 100
		if (!belongsTo(getPositionX() + 100, getPositionY() + 100) == belongsToCentre100Pre)
			throw new PostconditionError("Position incorrecte");
		// point absolu
		if (!belongsTo(300 + (x - getPositionXPre), 0 + (y - getPositionYPre)) == belongsToAbsPre)
			throw new PostconditionError("Position incorrecte");
	}
	
	@Override
	public HitboxContract clone(){
		return new HitboxContract(getDelegate().clone());
	}
	
}
