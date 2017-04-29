package tests;

import tests.abstrait.AbstractCharacterTest;
import impl.CharacterImpl;
import impl.EngineImpl;
import contracts.CharacterContract;
import contracts.EngineContract;

public class TestCharacter extends AbstractCharacterTest{
	
	@Override
	public void beforeTests() {
		setCharacter(new CharacterContract(new CharacterImpl()));
	}

}
