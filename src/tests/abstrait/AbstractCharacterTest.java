package tests.abstrait;

import impl.CharacterImpl;
import impl.EngineImpl;
import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contracts.CharacterContract;
import enums.NAME;
import errors.PreconditionError;
import services.CharacterService;

public abstract class AbstractCharacterTest {

	private CharacterService character; 


	public CharacterService getCharacter() {
		return character;
	}

	public void setCharacter(CharacterService character) {
		this.character = character;
	}

	@Before
	public abstract void beforeTests();

	/**** TESTS ****/

	@Test
	public void testInit(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(1);
		p2.init(2);
		p1.setCharacter(character);
		character.init(NAME.BISON, 50,5,true,null);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.CHN,15,15,false,null);
		p2.setCharacter(cc);
		EngineImpl e = new EngineImpl();
		e.init(250,400,20,p1,p2);
		
		
		Assert.assertEquals(character.getName(), NAME.BISON);
		Assert.assertEquals(character.getLife(), 50);
		Assert.assertEquals(character.getSpeed(), 5);
		Assert.assertEquals(character.faceRight(), true);
		Assert.assertEquals(character.getEngine(), e);
		Assert.assertTrue(character.getCharBox() == null);
	}

	@Test
	public void testFail1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,400,20,p1,p2);
		/*
		try{
			c.init("Ryu",-50,5,true,e);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
		 */
	}

	@Test
	public void testFail2(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,400,20,p1,p2);
		/*
		try{
			c.init("Ryu",50,-5,true,e);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
		 */
	}

	@Test
	public void testMoveLeft1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,500,10,p1,p2);
		//c.init("Ken",15,5,true,e);
		//c.setPositions(F,245,0);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		//cc.init("Ryu",15,15,false,e);
		//cc.setPositions(F,255,0);
		//e.setChar(c,1);
		//e.setChar(cc,2);
		//CharacterContract cccopy = cc.clone();
		//cc.moveLeft();
		/*
		 	Assert.assertEquals(cc.getPositionX(), cccopy.getPositionX());
		 	Assert.assertEquals(cc.faceRight(), cccopy.faceRight());
		 	Assert.assertEquals(cc.life(), cccopy.life());
		 	Assert.assertEquals(cc.getPositionY(), cccopy.getPositionY());
		 */
	}

	@Test
	public void testMoveLeft2(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,500,10,p1,p2);
		//c.init("Ken",15,5,true,e);
		//c.setPositions(F,245,0);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		//cc.init("Ryu",15,5,false,e);
		//cc.setPositions(F,255,0);
		//e.setChar(c,1);
		//e.setChar(cc,2);
		//CharacterContract ccopy = c.clone();
		//c.moveLeft();
		/*
		 	Assert.assertEquals(c.getPositionX(), ccopy.getPositionX()-ccopy.speed());
		 	Assert.assertEquals(c.faceRight(), ccopy.faceRight());
		 	Assert.assertEquals(c.life(), ccopy.life());
		 	Assert.assertEquals(c.getPositionY(), ccopy.getPositionY());
		 */
	}

	@Test
	public void testMoveLeft3(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,500,10,p1,p2);
		//c.init("Ken",15,15,true,e);
		//c.setPositions(F,5,0);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		//cc.init("Ryu",15,5,false,e);
		//cc.setPositions(F,255,0);
		//e.setChar(c,1);
		//e.setChar(cc,2);
		//CharacterContract ccopy = c.clone();
		//c.moveLeft();
		/*
		 	Assert.assertEquals(c.getPositionX(), 0);
		 	Assert.assertEquals(c.faceRight(), ccopy.faceRight());
		 	Assert.assertEquals(c.life(), ccopy.life());
		 	Assert.assertEquals(c.getPositionY(), ccopy.getPositionY());
		 */
	}

	@Test
	public void testMoveRight1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,500,10,p1,p2);
		//c.init("Ken",15,15,true,e);
		//c.setPositions(F,245,0);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		//cc.init("Ryu",15,5,false,e);
		//cc.setPositions(F,255,0);
		//e.setChar(c,1);
		//e.setChar(cc,2);
		//CharacterContract ccopy = c.clone();
		//c.moveRight();
		/*
		 	Assert.assertEquals(c.getPositionX(), ccopy.getPositionX());
		 	Assert.assertEquals(c.faceRight(), ccopy.faceRight());
		 	Assert.assertEquals(c.life(), ccopy.life());
		 	Assert.assertEquals(c.getPositionY(), ccopy.getPositionY());
		 */
	}

	@Test
	public void testMoveRight2(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,500,10,p1,p2);
		//c.init("Ken",15,5,true,e);
		//c.setPositions(F,245,0);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		//cc.init("Ryu",15,5,false,e);
		//cc.setPositions(F,255,0);
		//e.setChar(c,1);
		//e.setChar(cc,2);
		//CharacterContract ccopy = c.clone();
		//c.moveRight();
		/*
		 	Assert.assertEquals(c.getPositionX(), ccopy.getPositionX() + ccopy.speed());
		 	Assert.assertEquals(c.faceRight(), ccopy.faceRight());
		 	Assert.assertEquals(c.life(), ccopy.life());
		 	Assert.assertEquals(c.getPositionY(), ccopy.getPositionY());
		 */
	}

	@Test
	public void testMoveRight3(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,500,10,p1,p2);
		//c.init("Ken",15,15,true,e);
		//c.setPositions(F,245,0);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		//cc.init("Ryu",15,15,false,e);
		//cc.setPositions(F,495,0);
		//e.setChar(c,1);
		//e.setChar(cc,2);
		//CharacterContract ccopy = c.clone();
		//c.moveRight();
		/*
		 	Assert.assertEquals(c.getPositionX(), e.getWidth());
		 	Assert.assertEquals(c.faceRight(), ccopy.faceRight());
		 	Assert.assertEquals(c.life(), ccopy.life());
		 	Assert.assertEquals(c.getPositionY(), ccopy.getPositionY());
		 */
	}

	@Test
	public void testSwitchSide(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,500,10,p1,p2);
		//c.init("Ken",15,5,true,e);
		//CharacterContract cc = c.clone();
		//c.switchSide();
		/*
		 	Assert.assertEquals(c.faceRight(), !cc.faceRight);
		 	Assert.assertEquals(c.getPositionX(), cc.getPositionX());
		 	Assert.assertEquals(c.getPositionY(), cc.getPositionY());
		 */
	}

	@Test
	public void testStep(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		//p1.init(1);
		//p2.init(2);
		EngineImpl e = new EngineImpl();
		//e.init(250,500,10,p1,p2);
		//c.init("Ken",15,5,true,e);
		//CharacterContract cc = c.clone();
		//c.step(LEFT);
		/*
		 	Assert.assertEquals(c.faceRight(), !cc.faceRight);
		 	Assert.assertEquals(c.getPositionX(), cc.getPositionX());
		 	Assert.assertEquals(c.getPositionY(), cc.getPositionY());
		 */
	}
}
