package impl;

import javafx.scene.shape.Rectangle;
import services.HitboxService;
import services.RectangleHitboxService;

public class RectangleHitboxImpl extends HitboxImpl implements RectangleHitboxService {

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
		return x >= positionX && x <= positionX + width && y >= positionY && y <= positionY + height;
	}

	@Override
	public void init(int x, int y, int w, int h) {
		init(x, y);
		this.positionX = x;
		this.positionY = y;

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
		if (h instanceof RectangleHitboxService){
			for (int i = getPositionX(); i < width+getPositionX(); i++)
				for (int j = getPositionY(); j < height+getPositionY(); j++)
					if (h.belongsTo(i,j))
						return true;
			
			
		/*	System.out.println("coucou");
			return positionX < ((RectangleHitboxService) h).getPositionX() + ((RectangleHitboxService) h).getWidth()
					&& positionX > ((RectangleHitboxService) h).getPositionX()
					&& positionY < ((RectangleHitboxService) h).getPositionY()
							+ ((RectangleHitboxService) h).getHeight()
					&& height + positionY > ((RectangleHitboxService) h).getPositionY();
		*/
		}

		else
			for (int i = 0; i < width; i++)
				for (int j = 0; j < height; j++)
					if (h.belongsTo(i + positionX, j + positionY))
						return true;

		return false;
	}

	@Override
	public boolean equalsTo(HitboxService h) {
		if (h instanceof RectangleHitboxService)
			return ((RectangleHitboxService) h).getPositionX() == positionX
					&& ((RectangleHitboxService) h).getPositionY() == positionY
					&& ((RectangleHitboxService) h).getHeight() == height
					&& ((RectangleHitboxService) h).getWidth() == width;
		else
			for (int i = 0; i < width; i++)
				for (int j = 0; j < height; j++)
					if (!h.belongsTo(i + positionX, j + positionY))
						return false;
		return true;
	}

	@Override
	public void moveTo(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	@Override
	public RectangleHitboxImpl clone(){
		RectangleHitboxImpl rh = new RectangleHitboxImpl();
		rh.init(positionX,positionY, width, height);
		return rh;
	}
}
