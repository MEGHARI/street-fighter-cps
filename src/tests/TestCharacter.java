package tests;

import tests.abstrait.AbstractCharacterTest;
import impl.CharacterImpl;
import contracts.CharacterContract;

public class TestCharacter extends AbstractCharacterTest{
	
	@Override
	public void beforeTests() {
		setCharacter(new CharacterContract(new CharacterImpl()));
	}

}
