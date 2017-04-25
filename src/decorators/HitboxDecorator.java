package decorators;

import services.HitboxService;

public class HitboxDecorator implements HitboxService {
	private final HitboxService delegate;
	
	public HitboxDecorator(HitboxService delegate) {
		this.delegate = delegate;
	}
	
	public HitboxService getDelegate() {
		return delegate;
	}
	
	@Override
	public int getPositionX() {
		return delegate.getPositionX();
	}

	@Override
	public int getPositionY() {
		return delegate.getPositionY();
	}

	

	@Override
	public boolean belongsTo(int x, int y) {
		return delegate.belongsTo(x, y);
	}

	@Override
	public boolean collidesWith(HitboxService h) {
		return delegate.collidesWith(h);
	}

	@Override
	public boolean equalsTo(HitboxService h) {
		return delegate.equalsTo(h);
	}

	@Override
	public void init(int x, int y) {
		delegate.init(x, y);

	}

	@Override
	public void moveTo(int x, int y) {
		delegate.moveTo(x, y);

	}

}
