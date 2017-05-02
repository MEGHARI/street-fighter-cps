package contracts;

import decorators.PlayerDecorator;
import services.CharacterService;
import services.PlayerService;

public class PlayerContract extends PlayerDecorator {
	
	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	@Override
	public CharacterService getCharacter() {
		return super.getCharacter();
	}

	@Override
	public int getNum() {
		return super.getNum();
	}

	@Override
	public void init(int n) {
		super.init(n);

	}

	@Override
	public void setCharacter(CharacterService ch) {
		super.setCharacter(ch);

	}

}
