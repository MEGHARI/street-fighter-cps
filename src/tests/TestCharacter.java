package tests;

import tests.abstrait.AbstractCharacterTest;
import impl.CharacterImpl;
import impl.FightCharImpl;
import contracts.CharacterContract;
import contracts.FightCharContract;

public class TestCharacter extends AbstractCharacterTest{
	
	@Override
	public void beforeTests() {
		setCharacter(new FightCharContract(new FightCharImpl()));
	}

}
