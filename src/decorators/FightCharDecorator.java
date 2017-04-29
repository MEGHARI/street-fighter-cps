package decorators;

import data.Tech;
import services.FightCharService;

public class FightCharDecorator extends CharacterDecorator implements FightCharService {
	private FightCharService delegate;

	public FightCharDecorator(FightCharService delegate) {
		super(delegate);

	}

	public FightCharService getDelegate() {
		return (FightCharService) super.getDelegate();
	}
	@Override
	public boolean notManipulable() {
		return delegate.notManipulable();
	}
	@Override
	public boolean isBlocking() {
		return delegate.isBlocking();
	}

	public boolean isBlockstunned() {
		return delegate.isBlockstunned();
	}

	public boolean isHitstunned() {
		return delegate.isBlockstunned();
	}

	public boolean isTeching() {
		return delegate.isTeching();
	}

	public Tech getTech() {
		return delegate.getTech();
	}

	public int getTechFrame() {
		return delegate.getTechFrame();
	}

	public boolean isTechHasAlreadyHit() {
		return delegate.isTechHasAlreadyHit();
	}

	public void startTech(Tech tech) {
		delegate.startTech(tech);
	}

	public void jump() {
		delegate.jump();
	}

	public void crouch() {
		delegate.crouch();
	}

	public void startBlock() {
		delegate.startBlock();
	}
	
	@Override 
	public void jumpRight() {
		delegate.jumpRight();
	}
	
	@Override
	public void jumpLeft() {
		delegate.jumpLeft();
	}
    @Override
	public FightCharDecorator clone(){
    	return new FightCharDecorator(delegate.clone());
    }

}
