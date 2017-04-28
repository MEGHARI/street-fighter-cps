package tests.abstrait;

import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
	}
	
	@Test
	public void testFail1(){
	}
	
	@Test
	public void testFail2(){
	}
	
	@Test
	public void testMoveLeft1(){
	}
	
	@Test
	public void testMoveLeft2(){
	}
	
	@Test
	public void testMoveLeft3(){
	}
	
	@Test
	public void testMoveRight1(){
	}
	
	@Test
	public void testMoveRight2(){
	}
	
	@Test
	public void testMoveRight3(){
	}
	
	@Test
	public void testSwitchSide(){
	}
	
	@Test
	public void testStep(){
	}
}
