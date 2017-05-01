package impl;

import services.CharacterService;
import services.FightCharService;
import services.PlayerService;

public class PlayerBugImpl implements PlayerService {

	private FightCharService ch;
	private int num;
	
	@Override
	public FightCharService getCharacter() {
		return ch;
	}
	@Override
	public int getNum() {
		return num;
	}
	
	@Override
	public void init(int n) {
		this.num =n;
	}
	@Override
	public void setCharacter(FightCharService ch) {
		this.ch=ch;
	}
	
}
