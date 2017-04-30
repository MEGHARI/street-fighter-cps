package tests;

import tests.abstrait.AbstractFightCharTest;
import impl.FightCharImpl;
import contracts.FightCharContract;

public class TestFightChar extends AbstractFightCharTest{
	
	@Override
	public void beforeTests() {
		setFightChar(new FightCharContract(new FightCharImpl()));
	}

}