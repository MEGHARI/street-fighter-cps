package tests.abstrait;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.FightCharService;
import contracts.HitboxContract;

public abstract class AbstractFightCharTest {

	private FightCharService fightchar; 
	
	
	public FightCharService getFightchar() {
		return fightchar;
	}

	public void setFightchar(FightCharService fightchar) {
		this.fightchar = fightchar;
	}

	@Before
	public abstract void beforeTests();
	
	/**** TESTS ****/

	@Test
	public void testInit1(){
	}

}
