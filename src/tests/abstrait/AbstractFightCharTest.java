package tests.abstrait;

import impl.CharacterImpl;
import impl.EngineImpl;
import impl.FightCharImpl;
import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.FightCharService;
import contracts.CharacterContract;
import contracts.EngineContract;
import contracts.FightCharContract;
import contracts.HitboxContract;
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
		fightchar.init(NAME.BISON, 50,5,true,e);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.CHN,15,15,false,e);
		p2.setCharacter(cc);
		
		e.init(250,400,20,p1,p2);


		Assert.assertEquals(fightchar.getName(), NAME.BISON);
		Assert.assertEquals(fightchar.getLife(), 50);
		Assert.assertEquals(fightchar.getSpeed(), 5);
		Assert.assertEquals(fightchar.faceRight(), true);
		Assert.assertEquals(fightchar.getEngine(), e);
		Assert.assertTrue(fightchar.getCharBox() != null);
	}
	
	@Test
	public void testFail1(){
		PlayerImpl p1 = new PlayerImpl();
		PlayerImpl p2 = new PlayerImpl();
		p1.init(1);
		p2.init(2);
		p1.setCharacter(fightchar);
		try{
			fightchar.init(NAME.BISON,-50,5,true,null);
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
			fightchar.init(NAME.BISON,50,-5,true,null);
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
		fightchar.init(NAME.BISON, 15,5,true,e);

		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.CHN,15,15,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
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
		fightchar.init(NAME.BISON, 15,5,true,e);

		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.CHN,15,5,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		fightchar.setPositions(100, 0);
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
		fightchar.init(NAME.BISON, 15,15,true,e);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.CHN,15,15,false,e);
		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
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
		fightchar.init(NAME.BISON, 15,15,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.CHN,15,5,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
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
		fightchar.init(NAME.BISON, 15,5,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.CHN,15,5,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		fightchar.setPositions(100, 0);
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
		fightchar.init(NAME.BISON, 15,5,true,e);

		CharacterContract cc = new CharacterContract(new CharacterImpl());
		cc.init(NAME.CHN,15,5,false,e);

		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		fightchar.setPositions(255, 0);
		cc.setPositions(495, 0);
		CharacterContract cccopy = (CharacterContract) cc.clone();
		cc.moveRight();

		Assert.assertEquals(cc.getPositionX(), e.getWidth());
		Assert.assertEquals(cc.faceRight(), cccopy.faceRight());
		Assert.assertEquals(cc.getLife(), cccopy.getLife());
		Assert.assertEquals(cc.getPositionY(), cccopy.getPositionY());
		
		//e.init(250,500,10,p1,p2);
		//c.init("Ken",15,15,true,e);
		//c.setPositions(F,245,0);
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
		p1.init(0);
		p2.init(1);
		p1.setCharacter(fightchar);
		EngineContract e = new EngineContract(new EngineImpl());
		fightchar.init(NAME.BISON, 15,15,true,e);
		FightCharContract cc = new FightCharContract(new FightCharImpl());
		cc.init(NAME.CHN,15,15,false,e);
		p2.setCharacter(cc);
		e.init(250,500,10,p1,p2);
		CharacterContract ccopy = (CharacterContract) fightchar.clone();
		fightchar.switchSide();
		Assert.assertEquals(fightchar.faceRight(), !ccopy.faceRight());
		Assert.assertEquals(fightchar.getPositionX(), ccopy.getPositionX());
		Assert.assertEquals(fightchar.getPositionY(), ccopy.getPositionY());

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
