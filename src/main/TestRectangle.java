package main;

import contracts.RectangleHitboxContract;
import impl.RectangleHitboxImpl;

public class TestRectangle {
	
	public static void main(String... argv) {
		System.out.println("Sans contrat");
		RectangleHitboxImpl rhi = new RectangleHitboxImpl();
		rhi.init(0,0, 10, 10);
		System.out.println("BelongsTo 17, 11 = "+rhi.belongsTo(17, 11));
		System.out.println("PositionX = "+rhi.getPositionX()+ " PositionY = "+rhi.getPositionY());
		System.out.println("width = "+rhi.getWidth()+ " Height = "+rhi.getHeight());

		rhi.resize(5, 5);
		System.out.println("BelongsTo 17, 11 = "+rhi.belongsTo(17, 11));
		System.out.println("PositionX = "+rhi.getPositionX()+ " PositionY = "+rhi.getPositionY());
		System.out.println("width = "+rhi.getWidth()+ " Height = "+rhi.getHeight());

		rhi.resize(10,10);
		//rhi.moveTo(3, 1);
		System.out.println("BelongsTo 17, 11 = "+rhi.belongsTo(17, 11));
		System.out.println("PositionX = "+rhi.getPositionX()+ " PositionY = "+rhi.getPositionY());
		System.out.println("width = "+rhi.getWidth()+ " Height = "+rhi.getHeight());
		System.out.println(rhi.collidesWith(rhi));
		
		RectangleHitboxImpl rhii = new RectangleHitboxImpl();
		rhii.init(11, 0, 9, 9);
		System.out.println(rhi.collidesWith(rhii));
		
		
		
		
		
		System.out.println("Avec contrat");
		RectangleHitboxImpl rhi2 = new RectangleHitboxImpl();
		RectangleHitboxContract rhc = new RectangleHitboxContract(rhi2);
		rhc.init(0, 0, 10, 10);
		System.out.println("BelongsTo 17, 11 = "+rhc.belongsTo(17, 11));
		System.out.println("PositionX = "+rhc.getPositionX()+ " PositionY = "+rhc.getPositionY());
		System.out.println("width = "+rhc.getWidth()+ " Height = "+rhc.getHeight());

		rhc.resize(5, 5);
		System.out.println("BelongsTo 17, 11 = "+rhc.belongsTo(17, 11));
		System.out.println("PositionX = "+rhc.getPositionX()+ " PositionY = "+rhc.getPositionY());
		System.out.println("width = "+rhc.getWidth()+ " Height = "+rhc.getHeight());

		rhc.resize(10,10);
		//rhi.moveTo(3, 1);
		System.out.println("BelongsTo 17, 11 = "+rhc.belongsTo(17, 11));
		System.out.println("PositionX = "+rhc.getPositionX()+ " PositionY = "+rhc.getPositionY());
		System.out.println("width = "+rhc.getWidth()+ " Height = "+rhc.getHeight());
		System.out.println(rhc.collidesWith(rhc));
		
		RectangleHitboxImpl rhic2 = new RectangleHitboxImpl();
		RectangleHitboxContract rhcc = new RectangleHitboxContract(rhic2);
		rhcc.init(11, 0, 9, 9);
		System.out.println(rhc.collidesWith(rhcc));
		System.out.println(rhc.getPositionX());
	}

}
