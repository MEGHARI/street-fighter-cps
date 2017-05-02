package tests.abstrait;

import impl.FightCharImpl;
import impl.PlayerImpl;
import impl.RectangleHitboxImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.EngineService;
import contracts.FightCharContract;
import contracts.RectangleHitboxContract;
import enums.COMMAND;
import enums.NAME;
import errors.PreconditionError;

public abstract class AbstractEngineTest {

	private EngineService engine;

	public EngineService getCharacter() {
		return engine;
	}

	public void setEngine(EngineService engine) {
		this.engine = engine;
	}

	@Before
	public abstract void beforeTests();

	/**** TESTS ****/

	@Test
	public void testInit(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		RectangleHitboxContract r1 = new RectangleHitboxContract(new RectangleHitboxImpl());
		RectangleHitboxContract r2 = new RectangleHitboxContract(new RectangleHitboxImpl());
		r1.init(0, 0, 10, 30);
		r2.init(0, 0, 10, 30);
		FightCharContract c1 = new FightCharContract(new FightCharImpl());
		c1.init(NAME.RYU, 15,5,true,engine, r1);
		FightCharContract c2 = new FightCharContract(new FightCharImpl());
		c2.init(NAME.KEN,15,10,false,engine, r2);
		p1.setCharacter(c1);
		p2.setCharacter(c2);
		engine.init(500,250,50,p1,p2);


		Assert.assertEquals(engine.getWidth(), 500);
		Assert.assertEquals(engine.getHeight(), 250);
		Assert.assertEquals(engine.getPlayer(1), p1);
		Assert.assertEquals(engine.getPlayer(2), p2);
		Assert.assertEquals(engine.getChar(1).getPositionX(), 500/2 - 50/2);
		Assert.assertEquals(engine.getChar(2).getPositionX(), 500/2 + 50/2);
		Assert.assertEquals(engine.getChar(1).getPositionY(), 0);
		Assert.assertEquals(engine.getChar(2).getPositionY(), 0);
		Assert.assertTrue(engine.getChar(1).faceRight());
		Assert.assertTrue(!engine.getChar(2).faceRight());
	}
	@Test
	public void testInitFail1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		RectangleHitboxContract r1 = new RectangleHitboxContract(new RectangleHitboxImpl());
		RectangleHitboxContract r2 = new RectangleHitboxContract(new RectangleHitboxImpl());
		r1.init(0, 0, 10, 30);
		r2.init(0, 0, 10, 30);
		FightCharContract c1 = new FightCharContract(new FightCharImpl());
		c1.init(NAME.RYU, 15,5,true,engine, r1);
		FightCharContract c2 = new FightCharContract(new FightCharImpl());
		c2.init(NAME.KEN,15,10,false,engine, r2);
		p1.setCharacter(c1);
		p2.setCharacter(c2);
		try{
			engine.init(500,250,50,p1,p1);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInitFail2(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(0);
		p2.init(1);
		RectangleHitboxContract r1 = new RectangleHitboxContract(new RectangleHitboxImpl());
		RectangleHitboxContract r2 = new RectangleHitboxContract(new RectangleHitboxImpl());
		r1.init(0, 0, 10, 30);
		r2.init(0, 0, 10, 30);
		FightCharContract c1 = new FightCharContract(new FightCharImpl());
		c1.init(NAME.RYU, 15,5,true,engine, r1);
		FightCharContract c2 = new FightCharContract(new FightCharImpl());
		c2.init(NAME.KEN,15,10,false,engine, r2);
		p1.setCharacter(c1);
		p2.setCharacter(c2);
		try{
			engine.init(500,-250,50,p1,p2);
			Assert.fail();
		}catch(PreconditionError e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testStep(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(1);
		p2.init(2);
		RectangleHitboxContract r1 = new RectangleHitboxContract(new RectangleHitboxImpl());
		RectangleHitboxContract r2 = new RectangleHitboxContract(new RectangleHitboxImpl());
		r1.init(0, 0, 10, 30);
		r2.init(0, 0, 10, 30);
		FightCharContract c1 = new FightCharContract(new FightCharImpl());
		c1.init(NAME.RYU, 15,5,true,engine, r1);
		FightCharContract c2 = new FightCharContract(new FightCharImpl());
		c2.init(NAME.KEN,15,10,false,engine, r2);
		p1.setCharacter(c1);
		p2.setCharacter(c2);
		FightCharContract cc1 = c1.clone();
		FightCharContract cc2 = c2.clone();
		engine.init(500,250,50,p1,p2);
		engine.step(COMMAND.LEFT,COMMAND.RIGHT);
		cc1.moveLeft();
		cc2.moveRight();

		Assert.assertEquals(engine.getChar(1).getPositionX(), cc1.getPositionX());
		Assert.assertEquals(engine.getHeight(), 250);
		Assert.assertEquals(engine.getPlayer(1), p1);
		Assert.assertEquals(engine.getPlayer(2), p2);
		Assert.assertEquals(engine.getChar(1).getPositionX(), 500/2 - 50/2);
		Assert.assertEquals(engine.getChar(2).getPositionX(), 500/2 + 50/2);
		Assert.assertEquals(engine.getChar(1).getPositionY(), 0);
		Assert.assertEquals(engine.getChar(2).getPositionY(), 0);
		Assert.assertTrue(engine.getChar(1).faceRight());
		Assert.assertTrue(!engine.getChar(2).faceRight());
	}
/*
	@Test
	public void testFail2(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(1);
		p2.init(2);
		p1.setCharacter(engine);
		try{
			engine.init(NAME.RYU,50,-5,true,null);
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
		p1.setCharacter(engine);
		EngineContract e = new EngineContract(new EngineImpl());
		engine.init(NAME.RYU, 15,5,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,10,false,e);

		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		engine.setPositions(245, 0);
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
		p1.setCharacter(engine);
		EngineContract e = new EngineContract(new EngineImpl());
		engine.init(NAME.RYU, 15,5,true,e);
		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.KEN,15,5,false,e);
		p2.setCharacter(cc);
		e.init(500,250,10,p1,p2);
		engine.setPositions(245, 0);
		cc.setPositions(255, 0);
		CharacterContract ccopy = (CharacterContract) engine.clone();
		engine.moveLeft();


		Assert.assertEquals(engine.getPositionX(), ccopy.getPositionX()-ccopy.getSpeed());
		Assert.assertEquals(engine.faceRight(), ccopy.faceRight());
		Assert.assertEquals(engine.getLife(), ccopy.getLife());
		Assert.assertEquals(engine.getPositionY(), ccopy.getPositionY());
	}*/
}
