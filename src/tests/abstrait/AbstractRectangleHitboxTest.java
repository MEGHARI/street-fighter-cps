package tests.abstrait;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contracts.RectangleHitboxContract;


public abstract class AbstractRectangleHitboxTest {

	private RectangleHitboxContract rhitbox; 
	
	public RectangleHitboxContract getRhitbox() {
		return rhitbox;
	}

	public void setRhitbox(RectangleHitboxContract rhitbox) {
		this.rhitbox = rhitbox;
	}

	@Before
	public abstract void beforeTests();
	
	
	/**** TESTS ****/
	
	@Test
	public void testInit1(){
		rhitbox.init(100, 0, 10, 150);
		Assert.assertEquals(rhitbox.getPositionX(), 100);
		Assert.assertEquals(rhitbox.getPositionY(), 0);
		Assert.assertEquals(rhitbox.getWidth(), 10);
		Assert.assertEquals(rhitbox.getHeight(), 150);
	}
	
	@Test
	public void testInit2(){
		rhitbox.init(15, -10, 10, 15);
		Assert.assertEquals(rhitbox.getPositionX(), 15);
		Assert.assertEquals(rhitbox.getPositionY(), -10);
		Assert.assertEquals(rhitbox.getWidth(), 10);
		Assert.assertEquals(rhitbox.getHeight(), 15);
	}
}
