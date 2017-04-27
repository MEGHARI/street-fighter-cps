package tests;

import tests.abstrait.AbstractHitboxTest;
import impl.HitboxImpl;
import impl.RectangleHitboxImpl;
import contracts.HitboxContract;
import contracts.RectangleHitboxContract;

public class TestHitbox extends AbstractHitboxTest{
	
	@Override
	public void beforeTests() {
		setHitbox(new HitboxContract(new HitboxImpl()));
	}

}
