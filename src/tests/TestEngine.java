package tests;

import tests.abstrait.AbstractEngineTest;
import impl.EngineImpl;
import contracts.EngineContract;

public class TestEngine extends AbstractEngineTest{
	
	@Override
	public void beforeTests() {
		setEngine(new EngineContract(new EngineImpl()));
	}

}
