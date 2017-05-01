package decorators;

import contracts.FightCharContract;
import services.CharacterService;
import services.FightCharService;
import services.PlayerService;

public class PlayerDecorator implements PlayerService {
	
	public PlayerService delegate;
	public PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}

	@Override
	public FightCharService getCharacter() {
		return delegate.getCharacter();
	}

	@Override
	public int getNum() {
		return delegate.getNum();
	}

	@Override
	public void init(int n) {
		delegate.init(n);

	}

	@Override
	public void setCharacter(FightCharService ch) {
		delegate.setCharacter(ch);

	}

}
