package contracts;

import decorators.RectangleHitboxDecorator;
import errors.PostconditionError;
import errors.PreconditionError;
import services.RectangleHitboxService;

public class RectangleHitboxContract extends HitboxContract implements RectangleHitboxService {

	public RectangleHitboxContract(RectangleHitboxService delegate) {
		super(delegate);
	}

	public RectangleHitboxService getDelegate() {
		return (RectangleHitboxService) super.getDelegate();
	}

	// Invariants
	public void checkInvariant() {
		
		super.checkInvariant();

	}

	@Override
	public int getWidth() {
		return getDelegate().getWidth();
	}

	@Override
	public int getHeight() {
		return getDelegate().getHeight();
	}

	@Override
	public void init(int x, int y, int w, int h) {
		// preConditions
		// pre: w > 0
		if (!(w > 0))
			throw new PreconditionError("la largeur du rectangle doit etre strictement positive");
		// pre: h > 0
		if (!(h > 0))
			throw new PreconditionError("la hauteur du rectangle doit etre strictement positive");
		// preInvariants

		// run
		getDelegate().init(x, y, w, h);

		// postInvariants
		checkInvariant();

		// post: getPositionX() == x
		if (!(getPositionX() == x))
			throw new PostconditionError("La position en X est incorrect");
		// post: getPositionY() == y
		if (!(getPositionY() == y))
			throw new PostconditionError("La position en Y est incorrect");
		// post: getWidth() == w
		if (!(getWidth() == w))
			throw new PostconditionError("La largeur est incorrect");
		// post: getHeight() == h
		if (!(getHeight() == h))
			throw new PostconditionError("La hauteur est incorrect");
	}
	
	@Override
	public void resize(int w, int h) {
		
		// precondition
		// \pre: w > 0
		if(!(w>0))
			throw new PreconditionError("largeur négative");
	    // \pre: h > 0
		if(!(h>0))
			throw new PreconditionError("longeur négative");
		
		// preInvariant
		checkInvariant();
		
		// run
		getDelegate().resize(w, h);
		
		// postInvariant
		checkInvariant();
		
		// postCondition
		// \post: getHeight(w,h) == w
		if(!(getWidth() == w))
			throw new PostconditionError(" largeur incohérente");
		if(!(getHeight() == h))
			throw new PostconditionError(" hauteur incohérente");
	    // \post: getWidth(w,h) == w
		

	}
}
