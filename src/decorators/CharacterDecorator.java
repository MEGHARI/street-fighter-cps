package decorators;

import enums.COMMAND;
import enums.NAME;
import services.CharacterService;
import services.EngineService;
import services.HitboxService;

public class CharacterDecorator implements CharacterService {
	private final CharacterService delegate;
	
	public CharacterDecorator(CharacterService delegate) {
		this.delegate = delegate;
	}
	public CharacterService getDelegate() {
		return delegate;
	}

	@Override
	public int getPositionX() {
		return delegate.getPositionX();
	}
	@Override
	public NAME getName() {
		return delegate.getName();
	}

	@Override
	public int getPositionY() {
		return delegate.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		return delegate.getEngine();
	}

	@Override
	public HitboxService getCharBox() {
		return delegate.getCharBox();
	}

	@Override
	public int getLife() {
		return delegate.getLife();
	}

	@Override
	public int getSpeed() {
		return delegate.getSpeed();
	}

	@Override
	public boolean faceRight() {
		return delegate.faceRight();
	}

	@Override
	public boolean isDead() {
		return delegate.isDead();
	}

	@Override
	public void init(NAME name,int l, int s, boolean f, EngineService e) {
		delegate.init(name,l, s, f, e);

	}
	
	@Override
	public void setPositions(int x, int y) {
		delegate.setPositions(x, y);	
	}
	
	@Override
	public void initFace(boolean face) {
		delegate.initFace(face);
	}
	
	@Override
	public void moveLeft() {
		delegate.moveLeft();

	}

	@Override
	public void moveRight() {
		delegate.moveRight();

	}
	

	@Override
	public void switchSide() {
		delegate.switchSide();

	}

	@Override
	public void step(COMMAND c) {
		delegate.step(c);

	}
	
	@Override
	public CharacterDecorator clone(){
		return new CharacterDecorator(delegate.clone());
	}

}
