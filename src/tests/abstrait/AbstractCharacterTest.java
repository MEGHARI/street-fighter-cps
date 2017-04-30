package tests.abstrait;

import impl.CharacterImpl;
import impl.EngineImpl;
import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contracts.CharacterContract;
import contracts.EngineContract;
import enums.COMMAND;
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
		EngineImpl e = new EngineImpl();
		character.init(NAME.RYU, 50,5,true,e);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,15,false,e);
		p2.setCharacter(cc);
		e.init(250,400,20,p1,p2);
		
		
		Assert.assertEquals(character.getName(), NAME.RYU);
		Assert.assertEquals(character.getLife(), 50);
		Assert.assertEquals(character.getSpeed(), 5);
		Assert.assertEquals(character.faceRight(), true);
		Assert.assertEquals(character.getEngine(), e);
		Assert.assertTrue(character.getCharBox() != null);
	}

	@Test
	public void testFail1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(1);
		p2.init(2);
		p1.setCharacter(character);
		try{
			character.init(NAME.RYU,-50,5,true,null);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testFail2(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(1);
		p2.init(2);
		p1.setCharacter(character);
		try{
			character.init(NAME.RYU,50,-5,true,null);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testMoveLeft1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(character);
		EngineContract e = new EngineContract(new EngineImpl());
		character.init(NAME.RYU, 15,5,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,10,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		character.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract cccopy = cc.clone();
		cc.moveLeft();

		Assert.assertEquals(cc.getPositionX(), cccopy.getPositionX());
		Assert.assertEquals(cc.faceRight(), cccopy.faceRight());
		Assert.assertEquals(cc.getLife(), cccopy.getLife());
		Assert.assertEquals(cc.getPositionY(), cccopy.getPositionY());

	}

	@Test
	public void testMoveLeft2(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(character);
		EngineContract e = new EngineContract(new EngineImpl());
		character.init(NAME.RYU, 15,5,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,5,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		character.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) character.clone();
		character.moveLeft();
		Assert.assertEquals(character.getPositionX(), ccopy.getPositionX()-ccopy.getSpeed());
		Assert.assertEquals(character.faceRight(), ccopy.faceRight());
		Assert.assertEquals(character.getLife(), ccopy.getLife());
		Assert.assertEquals(character.getPositionY(), ccopy.getPositionY());

	}

	@Test
	public void testMoveLeft3(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(character);
		EngineContract e = new EngineContract(new EngineImpl());
		character.init(NAME.RYU, 15,15,true,e);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,15,false,e);
		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		character.setPositions(5, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) character.clone();
		character.moveLeft();
		Assert.assertEquals(character.getPositionX(), 0);
		Assert.assertEquals(character.faceRight(), ccopy.faceRight());
		Assert.assertEquals(character.getLife(), ccopy.getLife());
		Assert.assertEquals(character.getPositionY(), ccopy.getPositionY());
	}

	@Test
	public void testMoveRight1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(character);
		EngineContract e = new EngineContract(new EngineImpl());
		character.init(NAME.RYU, 15,10,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,5,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		character.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) character.clone();
		character.moveRight();

		Assert.assertEquals(character.getPositionX(), ccopy.getPositionX());
		Assert.assertEquals(character.faceRight(), ccopy.faceRight());
		Assert.assertEquals(character.getLife(), ccopy.getLife());
		Assert.assertEquals(character.getPositionY(), ccopy.getPositionY());
	}

	@Test
	public void testMoveRight2(){

		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(character);
		EngineContract e = new EngineContract(new EngineImpl());
		character.init(NAME.RYU, 15,5,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,5,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		character.setPositions(100, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) character.clone();
		character.moveRight();

		Assert.assertEquals(character.getPositionX(), ccopy.getPositionX()+ccopy.getSpeed());
		Assert.assertEquals(character.faceRight(), ccopy.faceRight());
		Assert.assertEquals(character.getLife(), ccopy.getLife());
		Assert.assertEquals(character.getPositionY(), ccopy.getPositionY());
	}

	@Test
	public void testMoveRight3(){		
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(character);
		EngineContract e = new EngineContract(new EngineImpl());
		character.init(NAME.RYU, 15,5,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,5,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		character.setPositions(255, 0);
		cc.setPositions(495, 0);
		CharacterContract cccopy = (CharacterContract) cc.clone();
		cc.moveRight();

		Assert.assertEquals(cc.getPositionX(), e.getWidth());
		Assert.assertEquals(cc.faceRight(), cccopy.faceRight());
		Assert.assertEquals(cc.getLife(), cccopy.getLife());
		Assert.assertEquals(cc.getPositionY(), cccopy.getPositionY());

	}

	@Test
	public void testSwitchSide(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(character);
		EngineContract e = new EngineContract(new EngineImpl());
		character.init(NAME.RYU, 15,15,true,e);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,15,false,e);
		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		CharacterContract ccopy = (CharacterContract) character.clone();
		character.switchSide();
		Assert.assertEquals(character.faceRight(), !ccopy.faceRight());
		Assert.assertEquals(character.getPositionX(), ccopy.getPositionX());
		Assert.assertEquals(character.getPositionY(), ccopy.getPositionY());

	}

	@Test
	public void testStep1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(character);
		EngineContract e = new EngineContract(new EngineImpl());
		character.init(NAME.RYU, 15,5,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,10,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		character.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) character.clone();
		character.step(COMMAND.LEFT);
		Assert.assertEquals(character.faceRight(), ccopy.faceRight());
		Assert.assertEquals(character.getPositionX(), ccopy.getPositionX()-ccopy.getSpeed());
		Assert.assertEquals(character.getPositionY(), ccopy.getPositionY());
	}
	
	@Test
	public void testStep2(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(character);
		EngineContract e = new EngineContract(new EngineImpl());
		character.init(NAME.RYU, 15,5,true,e);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,10,false,e);
		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		character.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) character.clone();
		character.step(COMMAND.RIGHT);
		Assert.assertEquals(character.faceRight(), ccopy.faceRight());
		Assert.assertEquals(character.getPositionX(), ccopy.getPositionX()+ccopy.getSpeed());
		Assert.assertEquals(character.getPositionY(), ccopy.getPositionY());
	}
}
