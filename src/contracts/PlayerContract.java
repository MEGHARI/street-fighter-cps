package contracts;

import decorators.PlayerDecorator;
import services.CharacterService;
import services.FightCharService;
import services.PlayerService;

public class PlayerContract extends PlayerDecorator {
	
	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	@Override
	public FightCharService getCharacter() {
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
	public void setCharacter(FightCharService ch) {
		super.setCharacter(ch);

	}

}
