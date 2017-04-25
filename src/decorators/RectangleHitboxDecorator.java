package decorators;

import services.RectangleHitboxService;

public class RectangleHitboxDecorator extends HitboxDecorator implements RectangleHitboxService {
	private RectangleHitboxService delegate;
	
	public RectangleHitboxDecorator(RectangleHitboxService delegate) {
		super(delegate);
	}
	
	@Override
	public RectangleHitboxService getDelegate() {
		return (RectangleHitboxService) super.getDelegate();
	}
	
	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public void init(int x, int y, int w, int h) {
		delegate.init(x, y);

	}
	
	@Override
	public void resize(int w, int h) {
		delegate.resize(w, h);

	}

}
