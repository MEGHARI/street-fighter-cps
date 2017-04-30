package tests;

import tests.abstrait.AbstractHitboxTest;
import impl.HitboxImpl;
import contracts.HitboxContract;

public class TestHitbox extends AbstractHitboxTest{
	
	@Override
	public void beforeTests() {
		setHitbox(new HitboxContract(new HitboxImpl()));
	}

}
