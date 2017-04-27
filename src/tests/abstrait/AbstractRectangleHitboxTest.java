package tests.abstrait;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contracts.RectangleHitboxContract;
import errors.PreconditionError;


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

	@Test
	public void testInitFail1(){
		try{
			rhitbox.init(10,0,-15,10);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail2(){
		try{
			rhitbox.init(10,0,15,-10);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testMoveTo1(){
		rhitbox.init(10, 0, 10, 15);
		RectangleHitboxContract rpre = rhitbox.clone();
		rhitbox.moveTo(20, 5);

		Assert.assertEquals(rhitbox.getPositionX(), 20);
		Assert.assertEquals(rhitbox.getPositionY(), 5);

		for(int i=0;i<256;i++){
			for(int j=0;j<256;j++){
				Assert.assertEquals(rhitbox.belongsTo(i, j), 
						rpre.belongsTo(i-(20-rpre.getPositionX()), j-(5-rpre.getPositionY())));
			}
		}
	}

	@Test
	public void testMoveTo2(){
		rhitbox.init(10, 0, 10, 15);
		RectangleHitboxContract rpre = rhitbox.clone();
		rhitbox.moveTo(-20, 5);

		Assert.assertEquals(rhitbox.getPositionX(), -20);
		Assert.assertEquals(rhitbox.getPositionY(), 5);

		for(int i=-256;i<1;i++){
			for(int j=0;j<256;j++){
				Assert.assertEquals(rhitbox.belongsTo(i, j), 
						rpre.belongsTo(i-(-20-rpre.getPositionX()), j-(5-rpre.getPositionY())));
			}
		}
	}

	@Test
	public void testResize(){
		rhitbox.init(10, 0, 10, 15);
		rhitbox.resize(5, 20);
		Assert.assertEquals(rhitbox.getWidth(), 5);
		Assert.assertEquals(rhitbox.getHeight(), 20);
	}

	@Test
	public void testResizeFail1(){
		rhitbox.init(10, 0, 10, 15);
		try{
			rhitbox.resize(5, -20);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testResizeFail2(){
		rhitbox.init(10, 0, 10, 15);
		try{
			rhitbox.resize(-5, 20);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
	}
}
