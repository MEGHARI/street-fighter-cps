package impl;

import services.HitboxService;

public class HitboxBugImpl implements HitboxService {

	private int positionX;
	private int positionY;

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
		return this.positionX == x && this.positionY == y;
	}

	@Override
	public boolean collidesWith(HitboxService h) {
		return belongsTo(h.getPositionX(), h.getPositionY());
	}

	@Override
	public boolean equalsTo(HitboxService h) {
		return belongsTo(h.getPositionX(), h.getPositionY());
	}

	@Override
	public void moveTo(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	@Override
	public HitboxImpl clone(){
		HitboxImpl h = new HitboxImpl();
		h.init(positionX,positionY);
		return h;
	}
}
