package tests.abstrait;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contracts.HitboxContract;

public abstract class AbstractHitboxTest {

	private HitboxContract hitbox; 

	public HitboxContract getHitbox() {
		return hitbox;
	}

	public void setHitbox(HitboxContract hitbox) {
		this.hitbox = hitbox;
	}
	
	@Before
	public abstract void beforeTests();
	
	/**** TESTS ****/

	@Test
	public void testInit1(){
		hitbox.init(100, 0);
		Assert.assertEquals(hitbox.getPositionX(), 100);
		Assert.assertEquals(hitbox.getPositionY(), 0);
	}

	@Test
	public void testInit2(){
		hitbox.init(15, -10);
		Assert.assertEquals(hitbox.getPositionX(), 15);
		Assert.assertEquals(hitbox.getPositionY(), -10);
	}

	@Test
	public void testMoveTo1(){
		hitbox.init(10, 0);
		HitboxContract rpre = hitbox.clone();
		hitbox.moveTo(20, 5);

		Assert.assertEquals(hitbox.getPositionX(), 20);
		Assert.assertEquals(hitbox.getPositionY(), 5);

		for(int i=0;i<256;i++){
			for(int j=0;j<256;j++){
				Assert.assertEquals(hitbox.belongsTo(i, j), 
						rpre.belongsTo(i-(20-rpre.getPositionX()), j-(5-rpre.getPositionY())));
			}
		}
	}

	@Test
	public void testMoveTo2(){
		hitbox.init(10, 0);
		HitboxContract rpre = hitbox.clone();
		hitbox.moveTo(-20, 5);

		Assert.assertEquals(hitbox.getPositionX(), -20);
		Assert.assertEquals(hitbox.getPositionY(), 5);

		for(int i=-256;i<1;i++){
			for(int j=0;j<256;j++){
				Assert.assertEquals(hitbox.belongsTo(i, j), 
						rpre.belongsTo(i-(-20-rpre.getPositionX()), j-(5-rpre.getPositionY())));
			}
		}
	}

}
