package tests;

import impl.RectangleHitboxImpl;
import contracts.RectangleHitboxContract;
import tests.abstrait.AbstractRectangleHitboxTest;

public class TestRectangleHitbox extends AbstractRectangleHitboxTest {
	
	@Override
	public void beforeTests() {
		setRhitbox(new RectangleHitboxContract(new RectangleHitboxImpl()));
	}

}
