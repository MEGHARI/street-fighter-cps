package tests.abstrait;

import impl.EngineImpl;
import impl.FightCharImpl;
import impl.PlayerImpl;
import impl.RectangleHitboxImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.FightCharService;
import contracts.CharacterContract;
import contracts.EngineContract;
import contracts.FightCharContract;
import contracts.RectangleHitboxContract;
import enums.COMMAND;
import enums.NAME;
import errors.PreconditionError;

public abstract class AbstractFightCharTest {

	private FightCharService fightchar; 
	
	
	public FightCharService getFightChar() {
		return fightchar;
	}

	public void setFightChar(FightCharService fightchar) {
		this.fightchar = fightchar;
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
		p1.setCharacter(fightchar);
		EngineImpl e = new EngineImpl();
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 20, 30);
		fightchar.init(NAME.KEN, 50,5,true,e,rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.RYU,15,15,false,e,rhc2);
		p2.setCharacter(cc);
		e.init(400,250,20,p1,p2);


		Assert.assertEquals(fightchar.getName(), NAME.KEN);
		Assert.assertEquals(fightchar.getLife(), 50);
		Assert.assertEquals(fightchar.getSpeed(), 5);
		Assert.assertEquals(fightchar.faceRight(), true);
		Assert.assertEquals(fightchar.getEngine(), e);
		Assert.assertEquals(fightchar.getCharBox(), rhc1);
	}
	
	@Test
	public void testFail1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(1);
		p2.init(2);
		p1.setCharacter(fightchar);
		try{
			fightchar.init(NAME.KEN,-50,5,true,null);
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
		p1.setCharacter(fightchar);
		try{
			fightchar.init(NAME.KEN,50,-5,true,null);
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
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		fightchar.init(NAME.RYU, 15,5,true,e,rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 10, 30);
		cc.init(NAME.KEN,15,5,false,e, rhc2);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		fightchar.setPositions(245, 0);
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
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 10, 30);
		fightchar.init(NAME.RYU, 15,5,true,e, rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.KEN,15,5,false,e, rhc2);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		fightchar.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) fightchar.clone();
		fightchar.moveLeft();
		
		
		Assert.assertEquals(fightchar.getPositionX(), ccopy.getPositionX()-ccopy.getSpeed());
		Assert.assertEquals(fightchar.faceRight(), ccopy.faceRight());
		Assert.assertEquals(fightchar.getLife(), ccopy.getLife());
		Assert.assertEquals(fightchar.getPositionY(), ccopy.getPositionY());

	}

	@Test
	public void testMoveLeft3(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 10, 30);
		fightchar.init(NAME.RYU, 15,15,true,e, rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.KEN,15,15,false,e, rhc2);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		fightchar.setPositions(5, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) fightchar.clone();
		fightchar.moveLeft();
		
		
		Assert.assertEquals(fightchar.getPositionX(), 0);
		Assert.assertEquals(fightchar.faceRight(), ccopy.faceRight());
		Assert.assertEquals(fightchar.getLife(), ccopy.getLife());
		Assert.assertEquals(fightchar.getPositionY(), ccopy.getPositionY());
	}

	@Test
	public void testMoveRight1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 10, 30);
		fightchar.init(NAME.RYU, 15,15,true,e, rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.KEN,15,5,false,e, rhc2);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		fightchar.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) fightchar.clone();
		fightchar.moveRight();

		
		Assert.assertEquals(fightchar.getPositionX(), ccopy.getPositionX());
		Assert.assertEquals(fightchar.faceRight(), ccopy.faceRight());
		Assert.assertEquals(fightchar.getLife(), ccopy.getLife());
		Assert.assertEquals(fightchar.getPositionY(), ccopy.getPositionY());
	}
	
	@Test
	public void testMoveRight2(){
		
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 10, 30);
		fightchar.init(NAME.RYU, 15,5,true,e, rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.KEN,15,5,false,e, rhc2);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		fightchar.setPositions(220, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) fightchar.clone();
		fightchar.moveRight();
		
		
		Assert.assertEquals(fightchar.getPositionX(), ccopy.getPositionX()+ccopy.getSpeed());
		Assert.assertEquals(fightchar.faceRight(), ccopy.faceRight());
		Assert.assertEquals(fightchar.getLife(), ccopy.getLife());
		Assert.assertEquals(fightchar.getPositionY(), ccopy.getPositionY());
	}

	@Test
	public void testMoveRight3(){		
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 10, 30);
		fightchar.init(NAME.RYU, 15,5,true,e, rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.KEN,15,5,false,e, rhc2);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		fightchar.setPositions(245, 0);
		cc.setPositions(486, 0);
		FightCharContract cccopy = (FightCharContract) cc.clone();
		cc.moveRight();
		
		
		Assert.assertEquals(cc.getPositionX(), e.getWidth()-cccopy.getCharBox().getWidth());
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
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 10, 30);
		fightchar.init(NAME.RYU, 15,15,true,e, rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.KEN,15,15,false,e, rhc2);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		CharacterContract ccopy = (CharacterContract) fightchar.clone();
		fightchar.switchSide();
		Assert.assertEquals(fightchar.faceRight(), !ccopy.faceRight());
		Assert.assertEquals(fightchar.getPositionX(), ccopy.getPositionX());
		Assert.assertEquals(fightchar.getPositionY(), ccopy.getPositionY());

	}

	@Test
	public void testStep1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 10, 30);
		fightchar.init(NAME.RYU, 15,5,true,e, rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.KEN,15,10,false,e, rhc2);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		fightchar.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) fightchar.clone();
		fightchar.step(COMMAND.LEFT);
		
		
		Assert.assertEquals(fightchar.faceRight(), ccopy.faceRight());
		Assert.assertEquals(fightchar.getPositionX(), ccopy.getPositionX()-ccopy.getSpeed());
		Assert.assertEquals(fightchar.getPositionY(), ccopy.getPositionY());
	}
	
	@Test
	public void testStep2(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		RectangleHitboxContract rhc1 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc1.init(0, 0, 10, 30);
		RectangleHitboxContract rhc2 = new RectangleHitboxContract(
				new RectangleHitboxImpl());
		rhc2.init(0, 0, 10, 30);
		fightchar.init(NAME.RYU, 15,5,true,e, rhc1);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.KEN,15,10,false,e, rhc2);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		fightchar.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) fightchar.clone();
		fightchar.step(COMMAND.RIGHT);
		
		
		Assert.assertEquals(fightchar.faceRight(), ccopy.faceRight());
		Assert.assertEquals(fightchar.getPositionX(), ccopy.getPositionX());
		Assert.assertEquals(fightchar.getPositionY(), ccopy.getPositionY());
	}

}
