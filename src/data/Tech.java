package data;

import contracts.RectangleHitboxContract;
import impl.RectangleHitboxImpl;
import services.HitboxService;
import services.RectangleHitboxService;

public class Tech {
	private int damage;
	private int hstun;
	private int bstun;
	private int sframe;
	private int hframe;
	private int rframe;
	private RectangleHitboxService hitbox;

	public Tech(int damage, int hstun, int bstun, int sframe, int hframe, int rframe) {
		this.damage = damage;
		this.hstun = hstun;
		this.bstun = bstun;
		this.sframe = sframe;
		this.hframe = hframe;
		this.rframe = rframe;
		
	}

	public int getDamage() {
		return damage;
	}

	public int getHstun() {
		return hstun;
	}

	public int getBstun() {
		return bstun;
	}

	public int getSframe() {
		return sframe;
	}

	public int getHframe() {
		return hframe;
	}

	public int getRframe() {
		return rframe;
	}

	public RectangleHitboxService hitbox(int x, int y,int w,int h) {
		hitbox = new RectangleHitboxContract(new RectangleHitboxImpl());
		hitbox.init(x, y, w, h);
		return hitbox;
	}
	
	public RectangleHitboxService getHitBox() {
		return hitbox;
	}
}
