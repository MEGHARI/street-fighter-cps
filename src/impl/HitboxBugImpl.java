package impl;

import services.HitboxService;

public class HitboxBugImpl implements HitboxService {

	protected int positionX;
	protected int positionY;

	@Override
	public void init(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}

	@Override
	public int getPositionX() {
		return positionX;
	}

	@Override
	public int getPositionY() {
		return positionY;
	}

	@Override
	public boolean belongsTo(int x, int y) {
		// bug
		return this.positionX == x || this.positionY == y;
	}

	@Override
	public boolean collidesWith(HitboxService h) {
		return belongsTo(h.getPositionX(), h.getPositionY());
	}

	@Override
	public boolean equalsTo(HitboxService h) {
		return this ==h;
	}

	@Override
	public void moveTo(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	@Override
	public HitboxBugImpl clone(){
		HitboxBugImpl h = new HitboxBugImpl();
		h.init(positionX,positionY);
		return h;
	}
}
