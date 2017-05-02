package decorators;

import services.CharacterService;
import services.PlayerService;

public class PlayerDecorator implements PlayerService {
	
	public PlayerService delegate;
	public PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}

	@Override
	public CharacterService getCharacter() {
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
	public void setCharacter(CharacterService ch) {
		delegate.setCharacter(ch);

	}

}
