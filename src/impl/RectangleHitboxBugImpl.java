package impl;

import services.HitboxService;
import services.RectangleHitboxService;

public class RectangleHitboxBugImpl extends HitboxImpl implements RectangleHitboxService {

	private int width;
	private int height;


	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean belongsTo(int x, int y) {
		// bug
		return x >= positionX && x <= positionX  && y >= positionY && y <= positionY + height;
	}

	@Override
	public void init(int x, int y, int w, int h) {
		init(x, y);
		this.width = w;
		this.height = h;

	}

	@Override
	public void init(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}

	@Override
	public void resize(int w, int h) {
		this.height = h;
		this.width = w;

	}

	@Override
	public boolean collidesWith(HitboxService h) {
		// bug
		if (h instanceof RectangleHitboxService){
			for (int i = getPositionX(); i < width+getPositionX(); i++)
				for (int j = getPositionY(); j < height+getPositionY(); j++)
					if (h.belongsTo(i,j))
						return true;
		}

		return false;
	}

	@Override
	public boolean equalsTo(HitboxService h) {
		// bug
		if (h instanceof RectangleHitboxService)
			return ((RectangleHitboxService) h).getPositionX() == positionX
					&& ((RectangleHitboxService) h).getPositionY() == positionY
					&& ((RectangleHitboxService) h).getHeight() == height
					&& ((RectangleHitboxService) h).getWidth() == width;
		return false;
	}

	@Override
	public void moveTo(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	@Override
	public RectangleHitboxBugImpl clone(){
		RectangleHitboxBugImpl rh = new RectangleHitboxBugImpl();
		rh.init(positionX,positionY, width, height);
		return rh;
	}
}
