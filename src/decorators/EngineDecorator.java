package decorators;

import enums.COMMAND;
import services.CharacterService;
import services.EngineService;
import services.FightCharService;
import services.PlayerService;

public class EngineDecorator implements EngineService {
	private final EngineService delegate;
	public  EngineDecorator(EngineService delegate) {
		this.delegate=delegate;
	}
	@Override
	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public FightCharService getChar(int i) {
		return delegate.getChar(i);
	}
	@Override
	public PlayerService getPlayer(int i) {
		return delegate.getPlayer(i);
	}

	@Override
	public boolean isGameOver() {
		return delegate.isGameOver();
	}
	
	@Override
	public void init(int w, int h, int s, PlayerService p1, PlayerService p2) {
		delegate.init(w, h, s, p1, p2);
	}
	
	@Override
	public void step(COMMAND c1, COMMAND c2) {
		delegate.step(c1, c2);	
	}

}
